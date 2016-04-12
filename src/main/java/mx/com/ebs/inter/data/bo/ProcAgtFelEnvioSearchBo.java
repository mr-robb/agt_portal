package mx.com.ebs.inter.data.bo;

import java.util.Date;

/**
 * Created by robb on 28/05/2015.
 */
public class ProcAgtFelEnvioSearchBo {

    private Integer numAgt;
    private Integer anioMes;
    private String rfc;
    private Date fechaCarga1;
    private Date fechaCarga2;
    private String enviada;
    private String recibida;

    public Integer getNumAgt() {
        return numAgt;
    }

    public void setNumAgt(Integer numAgt) {
        this.numAgt = numAgt;
    }

    public Integer getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(Integer anioMes) {
        this.anioMes = anioMes;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFechaCarga1() {
        return fechaCarga1;
    }

    public void setFechaCarga1(Date fechaCarga) {
        this.fechaCarga1 = fechaCarga;
    }

    public Date getFechaCarga2() {

    return fechaCarga2;
}
    public void setFechaCarga2(Date fechaCarga2) {
        this.fechaCarga2 = fechaCarga2;
    }

    public String getEnviada() {
        return enviada;
    }

    public void setEnviada(String enviada) {
        this.enviada = enviada;
    }

    public String getRecibida() {
        return recibida;
    }

    public void setRecibida(String recibida) {
        this.recibida = recibida;
    }
}