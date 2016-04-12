package mx.com.ebs.inter.service;

/**
 * Created by robb on 12/06/2015.
 */
public interface DaemonManagerService {

    Integer getProcessNumber(String processName);
    void startProcess();
    void killProcess(int processNumber);
}
