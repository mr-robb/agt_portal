package mx.com.ebs.inter.controller;

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.RecFelogger;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.service.*;
import mx.com.ebs.inter.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;
import java.sql.Timestamp;

/**
 * Created by robb on 01/07/2015.
 */
public class CargaMasivaController extends AbstractAutowirableServlet {

    private static final Logger LOGGER = Logger.getLogger(CargaMasivaController.class);

    @Autowired
    private XmlValidatorService xmlValidatorService;
    @Autowired
    private InvoicesManagerService invoicesManagerService;

    @Autowired
    private ProcAgtFelEnvioService procAgtFelEnvioService;

    @Autowired
    private DbxmlManagerService dbxmlManagerService;

    @Autowired
    private RecFeloggerService recFeloggerService;

    @Override
    public void service(ServletRequest httpservletrequest, ServletResponse httpservletresponse) throws ServletException, IOException {
        ObjectInputStream objectinputstream = new ObjectInputStream(httpservletrequest.getInputStream());
        httpservletresponse.setContentType("application/octet-stream");
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();

        ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        try
        {
            ObjectInputStream objectinputstream1 = objectinputstream;
            ObjectOutputStream objectoutputstream1 = objectoutputstream;

            byte[][] dats = (byte[][])objectinputstream1.readObject();

            String resp = "ERROR";

                try {
                    UserDataBo userDataBo = SessionReader.getUserDataBo();
                    String responseValidator = xmlValidatorService.validate(dats[0]);
                    if( responseValidator == null ){
                        RecInvoice recInvoice = RecInvoiceBuilder.buildRecInvoice(dats[0], null);
                        ProcAgtFelEnvio procAgtFelEnvio = procAgtFelEnvioService.getLastRecordByAgente( recInvoice.getAGENTE() );
                        String anioMes = procAgtFelEnvio.getANO_MES().toString();
                        recInvoice.setANO( anioMes.substring(0,4) );
                        recInvoice.setMES( anioMes.substring(4) );
                        if(invoicesManagerService.save(recInvoice)){
                            procAgtFelEnvio.setFH_RESPUESTA(new Timestamp(System.currentTimeMillis()));
                            if(procAgtFelEnvioService.update( procAgtFelEnvio )){
                                dbxmlManagerService.saveXMLDocument(recInvoice.getNUMERO_FACTURA(),
                                        Variables.COLLECTION_NAME_RECEPCION,new String(dats[0]));
                                resp = "OK";
                            }
                        }
                    }else{
                        RecFelogger recFelogger = RecFeloggerBuilder.buildRecFelogger(dats[0],null,responseValidator);
                        resp = responseValidator;
                        recFeloggerService.save( recFelogger );
                    }
                } catch (Exception e) {
                    LOGGER.error("Algun error al validar el XML",e);
                }

            objectoutputstream1.writeObject(resp);
        }
        catch (Exception exception)
        {
            LOGGER.error("Algun error al procesar el XML de Carga Masiva", exception);
        }
        objectoutputstream.flush();
        byte[] abyte0 = bytearrayoutputstream.toByteArray();
        httpservletresponse.setContentLength(abyte0.length);
        ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        servletoutputstream.write(abyte0);
        servletoutputstream.close();
    }

    public XmlValidatorService getXmlValidatorService() {
        return xmlValidatorService;
    }

    public void setXmlValidatorService(XmlValidatorService xmlValidatorService) {
        this.xmlValidatorService = xmlValidatorService;
    }

    public InvoicesManagerService getInvoicesManagerService() {
        return invoicesManagerService;
    }

    public void setInvoicesManagerService(InvoicesManagerService invoicesManagerService) {
        this.invoicesManagerService = invoicesManagerService;
    }

    public ProcAgtFelEnvioService getProcAgtFelEnvioService() {
        return procAgtFelEnvioService;
    }

    public void setProcAgtFelEnvioService(ProcAgtFelEnvioService procAgtFelEnvioService) {
        this.procAgtFelEnvioService = procAgtFelEnvioService;
    }

    public DbxmlManagerService getDbxmlManagerService() {
        return dbxmlManagerService;
    }

    public void setDbxmlManagerService(DbxmlManagerService dbxmlManagerService) {
        this.dbxmlManagerService = dbxmlManagerService;
    }

    public RecFeloggerService getRecFeloggerService() {
        return recFeloggerService;
    }

    public void setRecFeloggerService(RecFeloggerService recFeloggerService) {
        this.recFeloggerService = recFeloggerService;
    }
}
