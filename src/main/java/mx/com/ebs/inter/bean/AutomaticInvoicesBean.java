package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecInvoiceAgt;
import mx.com.ebs.inter.service.RecInvoiceAgtService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by robb on 27/05/2015.
 */
public class AutomaticInvoicesBean extends AbstractBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(AutomaticInvoicesBean.class);

    @Autowired
    private RecInvoiceAgtService recInvoiceAgtService;

    private RecInvoiceAgtSearchBo recInvoiceAgtSearchBo;
    private List<RecInvoiceAgt> recInvoiceAgtList;

    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        recInvoiceAgtSearchBo = new RecInvoiceAgtSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recInvoiceAgtSearchBo.setNumAgt(userDataBo.getNumAgt());
        }
        recInvoiceAgtList = recInvoiceAgtService.getListUsingFilter(recInvoiceAgtSearchBo);
    }

    public void executeSearch(ActionEvent actionEvent){
        LOGGER.debug("Executing search");
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recInvoiceAgtSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object recInvoiceAgtSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object recInvoiceAgtSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object recInvoiceAgtSearchBo");
        }
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recInvoiceAgtSearchBo.setNumAgt( userDataBo.getNumAgt() );
        }
        recInvoiceAgtList = recInvoiceAgtService.getListUsingFilter(recInvoiceAgtSearchBo);
    }

    public void cleanForm(){
        recInvoiceAgtSearchBo = new RecInvoiceAgtSearchBo();
        recInvoiceAgtList.clear();
    }

    public RecInvoiceAgtSearchBo getRecInvoiceAgtSearchBo() {
        return recInvoiceAgtSearchBo;
    }

    public void setRecInvoiceAgtSearchBo(RecInvoiceAgtSearchBo recInvoiceAgtSearchBo) {
        this.recInvoiceAgtSearchBo = recInvoiceAgtSearchBo;
    }

    public List<RecInvoiceAgt> getRecInvoiceAgtList() {
        return recInvoiceAgtList;
    }

    public void setRecInvoiceAgtList(List<RecInvoiceAgt> recInvoiceAgtList) {
        this.recInvoiceAgtList = recInvoiceAgtList;
    }

    public RecInvoiceAgtService getRecInvoiceAgtService() {
        return recInvoiceAgtService;
    }

    public void setRecInvoiceAgtService(RecInvoiceAgtService recInvoiceAgtService) {
        this.recInvoiceAgtService = recInvoiceAgtService;
    }

    public boolean isNumAgenteEnabled() {
        return isNumAgenteEnabled;
    }

    public void setNumAgenteEnabled(boolean isNumAgenteEnabled) {
        this.isNumAgenteEnabled = isNumAgenteEnabled;
    }
}
