package mx.com.ebs.inter.data.bo;

/**
 * Created by robb on 15/06/2015.
 */
public class InvoiceRequestedBo {

    private String numeroFactura;
    private int estadoDocumento = 0;
    private String poliza;
    private String moneda;

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(int estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }
}
