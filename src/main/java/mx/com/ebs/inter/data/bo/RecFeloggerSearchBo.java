package mx.com.ebs.inter.data.bo;

import java.util.Date;

/**
 * Created by robb on 28/05/2015.
 */
public class RecFeloggerSearchBo {

    private String id;
    private String msg;
    private Date fecha1, fecha2;
    private String numAgt;
    private String anio;
    private String mes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNumAgt() {
        return numAgt;
    }

    public void setNumAgt(String numAgt) {
        this.numAgt = numAgt;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }
}
