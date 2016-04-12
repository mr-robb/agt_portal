package mx.com.ebs.inter.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by robb on 12/06/2015.
 */
@Service
public class DaemonManagerServiceImpl implements DaemonManagerService {

    private static final Logger LOGGER = Logger.getLogger(DaemonManagerServiceImpl.class);

    private final String START_PROCESS_COMMAND = "/etc/init.d/ftpfed start &";
    private final String KILL_PROCESS_COMMAND = "kill -9 :0 :1";
    public static final String PROCESS_NAME = "/bin/sh /opt/fe/FE_Process/lib/processFTP.sh";
    private final String SEARCH_PROCESSES_COMMAND = "ps -fea";

    @Override
    public Integer getProcessNumber(String processName) {
        Process process = null;
        Integer processNumber = null;
        try {
            process = Runtime.getRuntime().exec(SEARCH_PROCESSES_COMMAND);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String demonio = "";
            while((demonio = br.readLine()) != null){
                if(demonio.contains(processName)){
                    StringTokenizer p1 = new StringTokenizer(demonio);
                    p1.nextToken();
                    String number = p1.nextToken();
                    processNumber = Integer.parseInt(number);
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error("Some error occurred while LOOKING for daemon",e);
        }
        return processNumber;

    }

    @Override
    public void startProcess() {
        try {
            Runtime.getRuntime().exec(START_PROCESS_COMMAND);
        } catch (IOException e) {
            LOGGER.error("Some error occurred while STARTING for daemon", e);
        }
    }

    @Override
    public void killProcess(int processNumber) {
        String command = KILL_PROCESS_COMMAND.replaceAll(":0",( String.valueOf(processNumber+1) )).replaceAll(":1",String.valueOf(processNumber));
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            LOGGER.error("Some error occurred while KILLING daemon", e);
        }
    }
}
