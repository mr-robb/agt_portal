package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.ProcAgtFelEnvioSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.RecFelogger;
import mx.com.ebs.inter.service.ProcAgtFelEnvioService;
import mx.com.ebs.inter.service.RecInvoiceAgtService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
//import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by robb on 28/05/2015.
 */
//@ManagedBean
//@ViewScoped
//@Component
public class ProcAgtFelEnvioBean extends AbstractBean<ProcAgtFelEnvio> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ProcAgtFelEnvioBean.class);

    @Autowired
    private ProcAgtFelEnvioService procAgtFelEnvioService;
    @Autowired
    private RecInvoiceAgtService recInvoiceAgtService;

    private ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo;

    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        procAgtFelEnvioSearchBo = new ProcAgtFelEnvioSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled && StringUtils.isNumeric(userDataBo.getNumAgt())){
           procAgtFelEnvioSearchBo.setNumAgt( Integer.parseInt(userDataBo.getNumAgt()));
        }
        createModel();
    }

    public void executeSearch(ActionEvent actionEvent){
        LOGGER.debug("Executing search, aniomes:" + procAgtFelEnvioSearchBo.getAnioMes());
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(procAgtFelEnvioSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object procAgtFelEnvioSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object procAgtFelEnvioSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object procAgtFelEnvioSearchBo");
        }
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        if( !isNumAgenteEnabled && StringUtils.isNumeric(userDataBo.getNumAgt())){
            procAgtFelEnvioSearchBo.setNumAgt( Integer.parseInt(userDataBo.getNumAgt()));
        }
        LOGGER.debug("Executing search, aniomes:" + procAgtFelEnvioSearchBo.getAnioMes());
        createModel();
    }
    private void createModel(){
        model = new LazyDataModel<ProcAgtFelEnvio>() {
            @Override
            public List<ProcAgtFelEnvio> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                LOGGER.info("sortField:" + sortField+"  SortOrder:" + sortOrder.name() );
                model.setRowCount(procAgtFelEnvioService.countRowsUsingFilter(procAgtFelEnvioSearchBo));
                List<ProcAgtFelEnvio> result = procAgtFelEnvioService.getListUsingFilter(procAgtFelEnvioSearchBo,first,pageSize,sortField,sortOrder);
                recInvoiceAgtService.fillNumerofactura(result);
                return result;
            }
        };
    }

    public void cleanForm(){
        procAgtFelEnvioSearchBo = new ProcAgtFelEnvioSearchBo();
    }
    public ProcAgtFelEnvioService getProcAgtFelEnvioService() {
        return procAgtFelEnvioService;
    }

    public void setProcAgtFelEnvioService(ProcAgtFelEnvioService procAgtFelEnvioService) {
        this.procAgtFelEnvioService = procAgtFelEnvioService;
    }

    public ProcAgtFelEnvioSearchBo getProcAgtFelEnvioSearchBo() {
        return procAgtFelEnvioSearchBo;
    }

    public void setProcAgtFelEnvioSearchBo(ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo) {
        this.procAgtFelEnvioSearchBo = procAgtFelEnvioSearchBo;
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
