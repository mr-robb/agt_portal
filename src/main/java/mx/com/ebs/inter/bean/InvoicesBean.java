package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.service.InvoiceService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
//import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by robb on 25/06/2015.
 */
public class InvoicesBean extends AbstractBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(InvoicesBean.class);

    @Autowired
    private InvoiceService invoiceService;
    private List<Invoice> invoiceList;
    private RecInvoiceSearchBo recInvoiceSearchBo;

    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        recInvoiceSearchBo = new RecInvoiceSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recInvoiceSearchBo.setNumAgente( userDataBo.getNumAgt() );
        }
        invoiceList = invoiceService.getUsingFilter(recInvoiceSearchBo);
    }
    public void executeSearch(){
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recInvoiceSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object recInvoiceSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object recInvoiceSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object recInvoiceSearchBo");
        }
        if( !isNumAgenteEnabled ){
            recInvoiceSearchBo.setNumAgente( SessionReader.getUserDataBo().getNumAgt() );
        }
        invoiceList = invoiceService.getUsingFilter(recInvoiceSearchBo);
    }

    public void cleanForm(){
        recInvoiceSearchBo = new RecInvoiceSearchBo();
        invoiceList.clear();
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public RecInvoiceSearchBo getRecInvoiceSearchBo() {
        return recInvoiceSearchBo;
    }

    public void setRecInvoiceSearchBo(RecInvoiceSearchBo recInvoiceSearchBo) {
        this.recInvoiceSearchBo = recInvoiceSearchBo;
    }

    public boolean isNumAgenteEnabled() {
        return isNumAgenteEnabled;
    }

    public void setNumAgenteEnabled(boolean isNumAgenteEnabled) {
        this.isNumAgenteEnabled = isNumAgenteEnabled;
    }

}
