package mx.com.ebs.inter.data.model.param;

/**
 * Created by robb on 30/05/2015.
 */
public class ParamNumeroFacturaEnviado {

    private String numAgt;
    private String anio;
    private String mes;

    public ParamNumeroFacturaEnviado() {
    }

    public ParamNumeroFacturaEnviado(String numAgt, String anio, String mes) {
        this.numAgt = numAgt;
        this.anio = anio;
        this.mes = mes;
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
}
