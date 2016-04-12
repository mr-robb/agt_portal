package mx.com.ebs.inter.bean;


import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.service.InvoicesManagerService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by robb on 29/04/2015.
 */

public class InvoicesReceivedBean extends AbstractBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(InvoicesReceivedBean.class);

    @Autowired
   private InvoicesManagerService invoicesManagerService;

    private List<RecInvoice> listRecInvoice;
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
        listRecInvoice = invoicesManagerService.getUsingFilter(recInvoiceSearchBo);
    }

    public void cleanForm(){
        recInvoiceSearchBo = new RecInvoiceSearchBo();
        listRecInvoice.clear();
        LOGGER.debug("cleanForm method has been called");
        LOGGER.debug(listRecInvoice.size());
    }

    public void executeSearch(ActionEvent actionEvent){
        LOGGER.debug("Dentro del metodo executeSearch!!");
        //logSearchBo();
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recInvoiceSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object recInvoiceSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object recInvoiceSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object recInvoiceSearchBo");
        }
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        if( !isNumAgenteEnabled ){
            recInvoiceSearchBo.setNumAgente( userDataBo.getNumAgt() );
        }
        listRecInvoice = invoicesManagerService.getUsingFilter(recInvoiceSearchBo);
    }

    public InvoicesManagerService getInvoicesManagerService() {
        return invoicesManagerService;
    }

    public void setInvoicesManagerService(InvoicesManagerService invoicesManagerService) {
        this.invoicesManagerService = invoicesManagerService;
    }

    public List<RecInvoice> getListRecInvoice() {
        return listRecInvoice;
    }

    public void setListRecInvoice(List<RecInvoice> listRecInvoice) {
        this.listRecInvoice = listRecInvoice;
    }

    public RecInvoiceSearchBo getRecInvoiceSearchBo() {
        return recInvoiceSearchBo;
    }

    public void setRecInvoiceSearchBo(RecInvoiceSearchBo recInvoiceSearchBo) {
        this.recInvoiceSearchBo = recInvoiceSearchBo;
    }

    private void logSearchBo(){
        LOGGER.debug(this.recInvoiceSearchBo.toString());
    }

    public boolean isNumAgenteEnabled() {
        return isNumAgenteEnabled;
    }

    public void setNumAgenteEnabled(boolean isNumAgenteEnabled) {
        this.isNumAgenteEnabled = isNumAgenteEnabled;
    }
}
