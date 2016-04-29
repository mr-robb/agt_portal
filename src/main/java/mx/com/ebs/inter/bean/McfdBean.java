package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.data.model.facbanco.MCfd;
import mx.com.ebs.inter.service.MCfdService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
//import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by robb on 15/06/2015.
 */
public class McfdBean extends AbstractBean<MCfd> implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(McfdBean.class);

    @Autowired
    private MCfdService mCfdService;

    private RecInvoiceSearchBo recInvoiceSearchBo;
    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        recInvoiceSearchBo= new RecInvoiceSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recInvoiceSearchBo.setNumAgente( userDataBo.getNumAgt() );
        }
        createModel();
    }
    private void createModel(){
        model = new LazyDataModel<MCfd>() {
            @Override
            public List<MCfd> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                LOGGER.info("sortField:" + sortField+"  SortOrder:" + sortOrder.name() );
                model.setRowCount(mCfdService.countRowsUsingFilter(recInvoiceSearchBo));
                return mCfdService.getListUsingFilter(recInvoiceSearchBo, first, pageSize,sortField,sortOrder);
            }
        };
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

        if( !isNumAgenteEnabled){
            recInvoiceSearchBo.setNumAgente(SessionReader.getUserDataBo().getNumAgt());
        }
        createModel();

    }

    public void cleanForm(){
        recInvoiceSearchBo = new RecInvoiceSearchBo();
    }

    public MCfdService getmCfdService() {
        return mCfdService;
    }

    public void setmCfdService(MCfdService mCfdService) {
        this.mCfdService = mCfdService;
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
