package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.ProcessBo;
import mx.com.ebs.inter.service.DaemonManagerService;
import mx.com.ebs.inter.service.DaemonManagerServiceImpl;
import mx.com.ebs.inter.util.FacesMessageUtil;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robb on 12/06/2015.
 */
//@ManagedBean
//@Component
public class ProcessManagerBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(ProcessManagerBean.class);
    private static final String STARTED = "INICIADO";
    private static final String STOPPED = "DETENIDO";
    private static final String OS_NAME = System.getProperty("os.name").toUpperCase();

    @Autowired
    private DaemonManagerService daemonManagerService;

    private List<ProcessBo> processBoList;

    private Boolean operationsAvailable;

    public ProcessManagerBean(){
        super();
        // Process management only available for LINUX OS
        operationsAvailable = !OS_NAME.contains("WINDOWS");
        processBoList = new ArrayList<ProcessBo>();
        processBoList.add( new ProcessBo());
    }

    public void init(){
        if( operationsAvailable ) {
            processBoList.get(0).setProcessNumber(daemonManagerService.getProcessNumber(DaemonManagerServiceImpl.PROCESS_NAME));
            processBoList.get(0).setProcessStatus( processBoList.get(0).getProcessNumber() == null ? STOPPED : STARTED );
        }else{
            FacesMessageUtil.showFacesMessage("Administraci" + UnicodeCommonWords.OACUTE_LOWER +"n de procesos no disponible",
                    UnicodeCommonWords.UACUTE_UPPER +"nicamente para sistema operativo Linux  ", "error" );
        }
    }

    public void startProcess(){
        try{
            if( processBoList.get(0).getProcessNumber() == null ){
                daemonManagerService.startProcess();
            }else{
                FacesMessageUtil.showFacesMessage("No es posible levantar el proceso","El proceso est"+ UnicodeCommonWords.AACUTE_LOWER+" iniciado","error");
            }
        }catch (Exception e){
            LOGGER.error("Error while starting daemon",e);
        }finally {
            init();
        }
    }

    public void stopProcess(){
        try{
            if( processBoList.get(0).getProcessNumber() != null ){
                daemonManagerService.killProcess(processBoList.get(0).getProcessNumber());
            }else{
                FacesMessageUtil.showFacesMessage("No es posible detener el proceso","El proceso ya se encuentra detenido","error");
            }
        }catch (Exception e){
            LOGGER.error("Error while killing daemon",e);
        }finally {
            init();
        }
    }

    public DaemonManagerService getDaemonManagerService() {
        return daemonManagerService;
    }

    public void setDaemonManagerService(DaemonManagerService daemonManagerService) {
        this.daemonManagerService = daemonManagerService;
    }

    public Boolean getOperationsAvailable() {
        return operationsAvailable;
    }


    public void setOperationsAvailable(Boolean operationsAvailable) {
        this.operationsAvailable = operationsAvailable;
    }

    public List<ProcessBo> getProcessBoList() {
        return processBoList;
    }

    public void setProcessBoList(List<ProcessBo> processBoList) {
        this.processBoList = processBoList;
    }
}
