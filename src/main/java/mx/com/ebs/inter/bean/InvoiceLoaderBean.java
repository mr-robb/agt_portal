package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.RecFelogger;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.service.*;
import mx.com.ebs.inter.util.*;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by robb on 25/06/2015.
 */

//@ManagedBean
//@Component
public class InvoiceLoaderBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(InvoiceLoaderBean.class);

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

    public RecFeloggerService getRecFeloggerService() {
        return recFeloggerService;
    }

    public void setRecFeloggerService(RecFeloggerService recFeloggerService) {
        this.recFeloggerService = recFeloggerService;
    }

    private String agente;

    public void upload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if(file != null) {
            processFile( file );
        }else{
            FacesMessageUtil.showFacesMessage("Selecciona un archivo", "Error de validaci"+ UnicodeCommonWords.OACUTE_LOWER+"n","error");
        }
    }

    private void processFile(UploadedFile file) {
        LOGGER.debug("Procesando el archivo " + file.getFileName());
        try {
            UserDataBo userDataBo = SessionReader.getUserDataBo();
            String idAgt = agente == null ? userDataBo.getNumAgt(): agente;
            String responseValidator = xmlValidatorService.validate(file.getContents());
            if( responseValidator == null ){
                RecInvoice recInvoice = RecInvoiceBuilder.buildRecInvoice( file.getContents() , idAgt );
                ProcAgtFelEnvio procAgtFelEnvio = procAgtFelEnvioService.getLastRecordByAgente( idAgt );
                String anioMes = procAgtFelEnvio.getANO_MES().toString();
                recInvoice.setANO( anioMes.substring(0,4) );
                recInvoice.setMES( anioMes.substring(4) );
                if(invoicesManagerService.save(recInvoice)){
                    procAgtFelEnvio.setFH_RESPUESTA(new Timestamp(System.currentTimeMillis()));
                    if(procAgtFelEnvioService.update( procAgtFelEnvio )){
                        dbxmlManagerService.saveXMLDocument(recInvoice.getNUMERO_FACTURA(),
                                Variables.COLLECTION_NAME_RECEPCION,new String(file.getContents()));
                        FacesMessageUtil.showFacesMessage("Operaci" + UnicodeCommonWords.OACUTE_LOWER+"n Exitosa" ,
                                "Validaci" + UnicodeCommonWords.OACUTE_LOWER +"n del archivo correcta, el documento " + recInvoice.getNUMERO_FACTURA()+" se ha cargado correctamente ",null);

                    }
                }
            }else{
                RecFelogger recFelogger = RecFeloggerBuilder.buildRecFelogger(file.getContents(),idAgt,responseValidator);
                recFeloggerService.save( recFelogger );
                FacesMessageUtil.showFacesMessage("Error de validaci"+UnicodeCommonWords.OACUTE_LOWER+"n" ,
                        responseValidator,"error");
            }
        } catch (Exception e) {
            LOGGER.error("Algun error al validar el XML",e);
            FacesMessageUtil.showFacesMessage("Error" ,
                    "Alg" + UnicodeCommonWords.UACUTE_LOWER +"n error al procesar el archivo","error");
        }

    }

    public XmlValidatorService getXmlValidatorService() {
        return xmlValidatorService;
    }

    public void setXmlValidatorService(XmlValidatorService xmlValidatorService) {
        this.xmlValidatorService = xmlValidatorService;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
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

}