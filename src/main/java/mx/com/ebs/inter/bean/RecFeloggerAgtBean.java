package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecFeloggerAgt;
import mx.com.ebs.inter.service.RecFeloggerAgtService;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.SessionReader;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
//import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public class RecFeloggerAgtBean extends AbstractBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(RecFeloggerAgtBean.class);

    @Autowired
    private RecFeloggerAgtService recFeloggerAgtService;
    private RecFeloggerSearchBo recFeloggerSearchBo;
    private List<RecFeloggerAgt> recFeloggerAgtList;
    private boolean isNumAgenteEnabled;

    @PostConstruct
    public void init(){
        recFeloggerSearchBo = new RecFeloggerSearchBo();
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        isNumAgenteEnabled = Validator.isEmptyString(userDataBo.getNumAgt());
        if( !isNumAgenteEnabled ){
            recFeloggerSearchBo.setNumAgt(userDataBo.getNumAgt());
        }
        recFeloggerAgtList = recFeloggerAgtService.getUsingFilter(recFeloggerSearchBo);
    }

    public void executeSearch(ActionEvent actionEvent){
        LOGGER.debug("Executing search");
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recFeloggerSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object recFeloggerSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object recFeloggerSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object recFeloggerSearchBo");
        }
        if( !isNumAgenteEnabled ){
            recFeloggerSearchBo.setNumAgt(SessionReader.getUserDataBo().getNumAgt());
        }
        recFeloggerAgtList = recFeloggerAgtService.getUsingFilter(recFeloggerSearchBo);
    }

    public void cleanForm(){
        recFeloggerSearchBo = new RecFeloggerSearchBo();
        recFeloggerAgtList.clear();
    }

    public RecFeloggerAgtService getRecFeloggerAgtService() {
        return recFeloggerAgtService;
    }

    public void setRecFeloggerAgtService(RecFeloggerAgtService recFeloggerAgtService) {
        this.recFeloggerAgtService = recFeloggerAgtService;
    }

    public RecFeloggerSearchBo getRecFeloggerSearchBo() {
        return recFeloggerSearchBo;
    }

    public void setRecFeloggerSearchBo(RecFeloggerSearchBo recFeloggerSearchBo) {
        this.recFeloggerSearchBo = recFeloggerSearchBo;
    }

    public List<RecFeloggerAgt> getRecFeloggerAgtList() {
        return recFeloggerAgtList;
    }

    public void setRecFeloggerAgtList(List<RecFeloggerAgt> recFeloggerAgtList) {
        this.recFeloggerAgtList = recFeloggerAgtList;
    }

    public boolean isNumAgenteEnabled() {
        return isNumAgenteEnabled;
    }

    public void setNumAgenteEnabled(boolean isNumAgenteEnabled) {
        this.isNumAgenteEnabled = isNumAgenteEnabled;
    }
}
