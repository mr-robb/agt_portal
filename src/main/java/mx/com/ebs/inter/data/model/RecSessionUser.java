package mx.com.ebs.inter.data.model;

import java.sql.Timestamp;

/**
 * Created by rfonseca on 6/9/16.
 */
public class RecSessionUser {

    private String EBS_USER_ID;
    private Integer STATUS;
    private Timestamp CREATED_TS,DESTROYED_TS;

    public String getEBS_USER_ID() {
        return EBS_USER_ID;
    }

    public void setEBS_USER_ID(String EBS_USER_ID) {
        this.EBS_USER_ID = EBS_USER_ID;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public Timestamp getCREATED_TS() {
        return CREATED_TS;
    }

    public void setCREATED_TS(Timestamp CREATED_TS) {
        this.CREATED_TS = CREATED_TS;
    }

    public Timestamp getDESTROYED_TS() {
        return DESTROYED_TS;
    }

    public void setDESTROYED_TS(Timestamp DESTROYED_TS) {
        this.DESTROYED_TS = DESTROYED_TS;
    }

}