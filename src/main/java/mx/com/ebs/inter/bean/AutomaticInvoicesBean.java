package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.data.model.RecInvoiceAgt;
import mx.com.ebs.inter.service.RecInvoiceAgtService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by robb on 27/05/2015.
 */
public class AutomaticInvoicesBean extends AbstractBean<RecInvoiceAgt> implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(AutomaticInvoicesBean.class);

    @Autowired
    private RecInvoiceAgtService recInvoiceAgtService;
    private RecInvoiceAgtSearchBo recInvoiceAgtSearchBo;
    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        recInvoiceAgtSearchBo = new RecInvoiceAgtSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recInvoiceAgtSearchBo.setNumAgt(userDataBo.getNumAgt());
        }
        createModel();
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
        createModel();
    }
    private void createModel(){
        model = new LazyDataModel<RecInvoiceAgt>() {
            @Override
            public List<RecInvoiceAgt> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                model.setRowCount(recInvoiceAgtService.countRowsUsingFilter(recInvoiceAgtSearchBo));
                return recInvoiceAgtService.getListUsingFilter(recInvoiceAgtSearchBo,first,pageSize,sortField,sortOrder);
            }
        };
    }

    public void cleanForm(){
        recInvoiceAgtSearchBo = new RecInvoiceAgtSearchBo();
    }

    public RecInvoiceAgtSearchBo getRecInvoiceAgtSearchBo() {
        return recInvoiceAgtSearchBo;
    }

    public void setRecInvoiceAgtSearchBo(RecInvoiceAgtSearchBo recInvoiceAgtSearchBo) {
        this.recInvoiceAgtSearchBo = recInvoiceAgtSearchBo;
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
