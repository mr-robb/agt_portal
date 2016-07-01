package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.InvoiceRequestedBo;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.data.model.RecInvoiceAgt;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.data.model.facbanco.MCfd;
import mx.com.ebs.inter.data.model.facbanco.MCfdXmlWithBLOBs;
import mx.com.ebs.inter.data.model.facbanco.MEmpresa;
import mx.com.ebs.inter.exception.ApplicationException;
import mx.com.ebs.inter.service.DbxmlManagerService;
import mx.com.ebs.inter.service.MCfdXmlService;
import mx.com.ebs.inter.service.MEmpresaService;
import mx.com.ebs.inter.util.QRImagen;
import mx.com.ebs.inter.util.Validator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by robb on 25/05/2015.
 */
//@ManagedBean
//@ViewScoped
//@Component
public class ServletCallerBean {

    private static final Logger LOGGER = Logger.getLogger(ServletCallerBean.class);
    public static final String XML = "xml";
    public static final String PDF = "pdf";
    public static final String XML_DB = "xmldb";
    public static final String PDF_DB = "pdfdb";

    private static final String RECORD_PATH="/Comprobante/Conceptos/Concepto";

    @Autowired
    private DbxmlManagerService dbxmlManagerService;

    @Autowired
    private MCfdXmlService mCfdXmlService;

    @Autowired
    private MEmpresaService mEmpresaService;

    public void downloadPDF(){
        callServlet(PDF);
    }

    public void downloadXML(){
        callServlet(XML);
    }

    public void downloadXmlDb(){
        callServlet(XML_DB);
    }

    public void downloadPdfDb(){
        callServlet(PDF_DB);
    }

    private void callServlet(final String downloadType ){
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

        String resourceUri = request.getRequestURI();
        String resourceName = resourceUri.substring(resourceUri.lastIndexOf("/")+1);
        LOGGER.debug("Calling from :" + resourceName );
        String collname = getCollectionName(resourceName);
        try {
            LOGGER.debug(" method  downloadPDF() of backing Bean ServletCallerBean has been executed!");
            request.setAttribute("type", downloadType);
            request.setAttribute("collection",collname);
            request.setAttribute("resource",resourceName);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            doPost(request,response,context);
            LOGGER.debug("context.responseComplete() has been executed");
        }
        catch (Exception e) {
            LOGGER.error("Some error occurred while trying to retrieve content to download", e);
        }
        finally{
            context.responseComplete();
        }
    }

    private String getCollectionName(String resouceView){
        String collname = null;
        if( "index.xhtml".equals(resouceView)
                || "facturasAutomaticas.xhtml".equals(resouceView)
                ){
            collname = "/recepcion";
        }else if( "facturasAgtCfd.xhtml".equals(resouceView) ){
            collname = "/fe";
        }else if( "onlineInvoices.xhtml".equals(resouceView) ){
            collname = "/fe";
        }
        return collname;

    }

    private void updateFechaConsulta(){
        // TODO actualizar la fecha de la consulta en la tabla de invoice
        // "UPDATE INVOICE SET FECHA_CONSULTA = SYSDATE WHERE NUMERO_FACTURA='"+ invNumber+"'";

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp,FacesContext context) throws ServletException, IOException {
        String collname = (String) req.getAttribute("collection");
        String resourceName = (String) req.getAttribute("resource");
        String rutaAbsoluta =  context.getExternalContext().getRealPath("/WEB-INF"); //getServletContext().getRealPath("/WEB-INF");
        LOGGER.debug("rutaAbsoluta:" + rutaAbsoluta);
        if( req.getAttribute("invoice") == null ){
            throw new ServletException("El identificador enviado no es válido");
        }
        InvoiceRequestedBo invoiceRequestedBo = getInvoiceRequestedBo(req.getAttribute("invoice"));

        if( ServletCallerBean.PDF.equals(req.getAttribute("type")) ){
            try {
                boolean printDocument = usePrintDocument(resourceName);
                byte[] content = dbxmlManagerService.getXMLDocument(invoiceRequestedBo.getNumeroFactura()+( printDocument ? "_P" : ""), collname,true);
                if (content == null) {
                    resp.setContentType("text/xml");
                    resp.setHeader("Content-disposition", "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".xml\"");
                    resp.getOutputStream().print("<message>Document "+ invoiceRequestedBo.getNumeroFactura() +" not found in collection"+collname+"</message>");
                    return;
                }
                String reportFileName = getTemplateName(resourceName,true,invoiceRequestedBo.getNumeroFactura(),invoiceRequestedBo.getPoliza(),context);

                JRXmlDataSource jrxmlds = new JRXmlDataSource( new ByteArrayInputStream( content ),RECORD_PATH );
                HashMap hm = new HashMap();
                jrxmlds.setLocale(new Locale("sp", "MX"));
                jrxmlds.setNumberPattern("##0.00");
                hm.put("IMAGES", rutaAbsoluta + "/images/");
                LOGGER.debug("Estado:documento: " + String.valueOf(invoiceRequestedBo.getEstadoDocumento()));
                hm.put("STATUS", String.valueOf(invoiceRequestedBo.getEstadoDocumento()));
                hm.put("MONEDA", invoiceRequestedBo.getMoneda());

                byte[] bytes = JasperRunManager.runReportToPdf(reportFileName, hm, jrxmlds);
                String header = "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".pdf\"";
                printPDF(bytes,resp,header);
            }catch (ApplicationException e){
                throw new ServletException(e);
            } catch (JRException e) {
                throw new ServletException(e);
            }

        }
        else if( ServletCallerBean.XML.equals(req.getAttribute("type")) ){
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("UTF-8");
            //resp.setHeader("Content-Disposition","filename="+invoiceRequestedBo.getNumeroFactura());
            resp.setHeader("Content-disposition", "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".xml\"");
            try {
                byte[] content = dbxmlManagerService.getXMLDocument(invoiceRequestedBo.getNumeroFactura(),collname,true);
                if( content == null ){
                    resp.getOutputStream().print("<message>Document "+ invoiceRequestedBo.getNumeroFactura() +" not found in collection"+collname+"</message>");
                    return;
                }else{
                    resp.setContentLength(content.length);
                    resp.getOutputStream().write(content);
                }

            } catch (ApplicationException e) {
                throw new ServletException(e);
            }

        }else if( ServletCallerBean.XML_DB.equals(req.getAttribute("type")) ){
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("UTF-8");
            //resp.setHeader("Content-Disposition","filename="+invoiceRequestedBo.getNumeroFactura());
            resp.setHeader("Content-disposition", "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".xml\"");
            // Recuperar XML desde oracle
            MCfd mCfd = (MCfd) req.getAttribute("invoice");
            Long idComprobante = mCfd.getID();
            MCfdXmlWithBLOBs mCfdXmlWithBLOBs = mCfdXmlService.getByForeignKey(idComprobante);
            if( mCfdXmlWithBLOBs == null ){
                resp.getOutputStream().print("<message>Document "+ invoiceRequestedBo.getNumeroFactura() +" not found in DB</message>");
                return;
            }
            resp.setContentLength(mCfdXmlWithBLOBs.getXML().length);
            resp.getOutputStream().write(mCfdXmlWithBLOBs.getXML());

        }else if(ServletCallerBean.PDF_DB.equals(req.getAttribute("type"))){
            MCfd mCfd = (MCfd) req.getAttribute("invoice");
            Long idComprobante = mCfd.getID();
            String filename = getTemplateName(resourceName,false,null,null,context);
            MCfdXmlWithBLOBs mCfdXmlWithBLOBs = mCfdXmlService.getByForeignKey(idComprobante);
            if( mCfdXmlWithBLOBs == null ){
                resp.setContentType("text/xml");
                resp.setHeader("Content-disposition", "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".xml\"");
                resp.getOutputStream().print("<message>Document "+ invoiceRequestedBo.getNumeroFactura() +" not found in DB</message>");
                return;
            }
            MEmpresa mEmpresa = mEmpresaService.getByPrimaryKey(mCfd.getEMPRESA_ID());
            if( mEmpresa == null ){
                resp.setContentType("text/xml");
                resp.setHeader("Content-disposition", "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".xml\"");
                resp.getOutputStream().print("<message>Record Emisor not found in DB</message>");
                return;
            }

            HashMap hm = new HashMap();
            Image img = null;
            try {
                JRXmlDataSource jrxmlds = new JRXmlDataSource(new ByteArrayInputStream(mCfdXmlWithBLOBs.getXMLP()), RECORD_PATH);
                jrxmlds.setLocale(new Locale("sp", "MX"));
                jrxmlds.setNumberPattern("##0.00");
                hm.put("IMAGES", rutaAbsoluta +"/images/" );
                hm.put("STATUS", String.valueOf(invoiceRequestedBo.getEstadoDocumento()));
                img = new QRImagen().get2DBarCode(
                        mEmpresa.getRFC_ORIGEN(),
                        mCfd.getRFC()
                        , "" + mCfd.getTOTAL()
                        , mCfd.getUUID());

                hm.put("2DCODEBAR", img);
                byte[] bytes = JasperRunManager.runReportToPdf(filename, hm, jrxmlds);
                String header = "attachment; filename=\""+invoiceRequestedBo.getNumeroFactura()+".pdf\"";
                printPDF(bytes, resp,header);
            }catch(JRException e){
                LOGGER.error("Algun error al crear PDF", e);
                throw new ServletException(e);
            }catch(Exception e){
                LOGGER.error("Algun error al crear QRCode", e);
                throw new ServletException(e);
            }


        }


    }

    public InvoiceRequestedBo getInvoiceRequestedBo(Object invoice){
        InvoiceRequestedBo invoiceRequestedBo = new InvoiceRequestedBo();
        if( invoice instanceof RecInvoice){
            RecInvoice recInvoice = (RecInvoice) invoice;
            invoiceRequestedBo.setNumeroFactura( recInvoice.getNUMERO_FACTURA() );
            invoiceRequestedBo.setPoliza(recInvoice.getPOLIZA());
            if( recInvoice.getSIT_COMPROBANTE() != null ){
                invoiceRequestedBo.setEstadoDocumento( recInvoice.getSIT_COMPROBANTE().intValue() );
            }
        }else if( invoice instanceof RecInvoiceAgt){
            RecInvoiceAgt recInvoiceAgt = (RecInvoiceAgt)invoice;
            invoiceRequestedBo.setNumeroFactura( recInvoiceAgt.getNUMERO_FACTURA() );
            invoiceRequestedBo.setPoliza(recInvoiceAgt.getPOLIZA());
            if( recInvoiceAgt.getSIT_COMPROBANTE() != null ){
                invoiceRequestedBo.setEstadoDocumento( recInvoiceAgt.getSIT_COMPROBANTE().intValue() );
            }
        }else if( invoice instanceof MCfd){
            MCfd mCfd = (MCfd) invoice;
            invoiceRequestedBo.setNumeroFactura( mCfd.getNUMERO_FACTURA() );
            invoiceRequestedBo.setMoneda( mCfd.getMONEDA() );
            if(mCfd.getESTADO_DOCUMENTO() != null){
                invoiceRequestedBo.setEstadoDocumento( mCfd.getESTADO_DOCUMENTO().intValue() );
            }
        }else if(invoice instanceof Invoice){
            Invoice invoiceTmp = (Invoice)invoice;
            invoiceRequestedBo.setNumeroFactura( invoiceTmp.getNUMERO_FACTURA() );
            invoiceRequestedBo.setPoliza(invoiceTmp.getPOLIZA());
            invoiceRequestedBo.setMoneda( invoiceTmp.getMONEDA_DOC() );
            if( invoiceTmp.getESTADO_DOCUMENTO() != null ){
                invoiceRequestedBo.setEstadoDocumento( Integer.parseInt(invoiceTmp.getESTADO_DOCUMENTO()) );
            } else
                invoiceRequestedBo.setEstadoDocumento(1);
        }
        return invoiceRequestedBo;
    }

    private String getTemplateName(String resourceName, boolean isCFD, String numeroFactura, String poliza,FacesContext context){
        String filename = context.getExternalContext().getRealPath("/WEB-INF/plantillas"); //getServletContext().getRealPath("/WEB-INF/plantillas");
        LOGGER.debug("********************************************************************************");
        LOGGER.debug("resourceName:"+resourceName);
        LOGGER.debug("jasperReport:" + filename);
        if( "index.xhtml".equals(resourceName)|| "facturasAutomaticas.xhtml".equals(resourceName)){
            filename += "/irecepcion.jasper";
        }else if( "facturasAgtCfd.xhtml".equals(resourceName) ){
            filename += "/Interacciones_fe_recepcion.jasper";
        }else if( "facturasAgtCfdi.xhtml".equals(resourceName) ){
            filename += "/Aseguradora_fe_recepcion.jasper";
        }
        else if( "onlineInvoices.xhtml".equals(resourceName) ){
            if( isCFD ){
                if( numeroFactura.startsWith("FV")||numeroFactura.startsWith("FS")||( "0".equals(poliza) && numeroFactura.startsWith("NCR") ) ){
                    filename += "/InterFV.jasper";
                }else{
                    filename += "/Interacciones_fe_interacciones.jasper";
                }
            }else{
                filename += "/Aseguradora_fe_interacciones.jasper";
            }
        }
        LOGGER.debug("jasperReport:" + filename);
        return filename;
    }

    private void printPDF(byte [] dataToWrite, HttpServletResponse resp,String header) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition",header);
        resp.setContentLength(dataToWrite.length);
        resp.getOutputStream().write(dataToWrite);
    }

    public MCfdXmlService getmCfdXmlService() {
        return mCfdXmlService;
    }

    public void setmCfdXmlService(MCfdXmlService mCfdXmlService) {
        this.mCfdXmlService = mCfdXmlService;
    }

    public MEmpresaService getmEmpresaService() {
        return mEmpresaService;
    }

    public void setmEmpresaService(MEmpresaService mEmpresaService) {
        this.mEmpresaService = mEmpresaService;
    }

    public DbxmlManagerService getDbxmlManagerService() {
        return dbxmlManagerService;
    }

    public void setDbxmlManagerService(DbxmlManagerService dbxmlManagerService) {
        this.dbxmlManagerService = dbxmlManagerService;
    }

    private boolean usePrintDocument(String resourceName) {
        if ("index.xhtml".equals(resourceName) || "facturasAutomaticas.xhtml".equals(resourceName)) {
            return false;
        }
        return true;
    }

}
