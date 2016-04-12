package mx.com.ebs.inter.data.bo;

import java.util.Date;

/**
 * Created by robb on 03/12/2015.
 */
public class ExporterSearchBo {

    private Date fechaDesde , fechaHasta;
    private String rfc;
    private String numAgente;

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNumAgente() {
        return numAgente;
    }

    public void setNumAgente(String numAgente) {
        this.numAgente = numAgente;
    }
}
