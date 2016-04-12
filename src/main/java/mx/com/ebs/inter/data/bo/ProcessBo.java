package mx.com.ebs.inter.data.bo;

/**
 * Created by robb on 12/06/2015.
 */
public class ProcessBo {

    private Integer processNumber;
    private String processStatus;

    public ProcessBo(){}

    public ProcessBo(Integer processNumber, String processStatus) {
        this.processNumber = processNumber;
        this.processStatus = processStatus;
    }

    public Integer getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(Integer processNumber) {
        this.processNumber = processNumber;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
}
