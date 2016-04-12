package mx.com.ebs.inter.data.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by robb on 27/05/2015.
 */
public class RecInvoiceAgtSearchBo {

    private String numFactura;
    private String razonSocial;
    private String numAgt;
    private String rfc;
    private Date fecha1, fecha2;
    private Double importeTotal;
    private BigDecimal estatus;
    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getEstatus() {
        return estatus;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public String getNumAgt() {
        return numAgt;
    }

    public void setNumAgt(String numAgt) {
        this.numAgt = numAgt;
    }
}
