package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.ExporterSearchBo;
import mx.com.ebs.inter.exception.ApplicationException;
import mx.com.ebs.inter.service.InvoicesExporterService;
import mx.com.ebs.inter.util.FacesMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by robb on 03/12/2015.
 */
public class ExporterBean implements Serializable {

    @Autowired
    private InvoicesExporterService invoicesExporterService;
    private int documentCounter;
    private ExporterSearchBo exporterSearchBo;

    @PostConstruct
    public void init(){
        exporterSearchBo = new ExporterSearchBo();
    }

    public void searchInvoices(){
        documentCounter = invoicesExporterService.getDocumentsCounter(exporterSearchBo);
        if( documentCounter == 0 ){
            FacesMessageUtil.showFacesMessage("Sin resultados","No hay documentos para exportar" , null);
        }else{
            FacesMessageUtil.showFacesMessage( "Resultado de búsqueda" , documentCounter+" documentos serán exportados", null);
        }
    }

    public void doExportDocuments(){
        try{
            invoicesExporterService.exportDocuments(exporterSearchBo);
            FacesMessageUtil.showFacesMessage("Los documentos han sido exportados de forma correcta","Operación Exitosa",null);
        }catch(ApplicationException ape){
            FacesMessageUtil.showFacesMessage("Algun error al exportar los documentos:" + ape.getMessage(),"Error","error");
        }
    }

    public void resetForm(){
        documentCounter = 0;
        exporterSearchBo = new ExporterSearchBo();
    }

    public InvoicesExporterService getInvoicesExporterService() {
        return invoicesExporterService;
    }

    public void setInvoicesExporterService(InvoicesExporterService invoicesExporterService) {
        this.invoicesExporterService = invoicesExporterService;
    }

    public int getDocumentCounter() {
        return documentCounter;
    }

    public void setDocumentCounter(int documentCounter) {
        this.documentCounter = documentCounter;
    }

    public ExporterSearchBo getExporterSearchBo() {
        return exporterSearchBo;
    }

    public void setExporterSearchBo(ExporterSearchBo exporterSearchBo) {
        this.exporterSearchBo = exporterSearchBo;
    }
}
