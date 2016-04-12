package mx.com.ebs.inter.data.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by robb on 20/05/2015.
 * Business Object to execute searches with this attributes as conditionals
 */
public class RecInvoiceSearchBo {

    private String numFactura;
    private String numAgente;
    private String numPoliza;
    private String razonSocial;
    private String rfc;
    private Date fecha1, fecha2;
    private Double importeTotal;
    private BigDecimal estatus;

    public RecInvoiceSearchBo(){
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getNumAgente() {
        return numAgente;
    }

    public void setNumAgente(String numAgente) {
        this.numAgente = numAgente;
    }

    public String getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
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
    @Override
    public String toString(){
        return new StringBuilder("RecInvoiceSearchBo[")
        .append("\nnumFactura:").append(numFactura)
                .append("\nnumAgente:").append(numAgente)
                .append("\nnumPoliza:").append(numPoliza)
                .append("\nrazonSocial:").append(razonSocial)
                .append("\nrfc:").append(rfc)
                .append("\nfecha1:").append(fecha1)
                .append("\nfecha2:").append(fecha2)
                .append("\nimporteTotal:").append(importeTotal)
                .append("\nestatus:").append(estatus).append("\n]").toString();
    }

}