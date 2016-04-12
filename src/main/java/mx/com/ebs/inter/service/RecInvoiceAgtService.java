package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.RecInvoiceAgt;

import java.util.List;

/**
 * Created by robb on 27/05/2015.
 */
public interface RecInvoiceAgtService {

    List<RecInvoiceAgt> getAll();
    List<RecInvoiceAgt> getListUsingFilter(RecInvoiceAgtSearchBo recInvoiceAgtSearchBo);
    String getNumeroFacturaEnviados(String numAgt,String anio, String mes);
    void fillNumerofactura(List<ProcAgtFelEnvio> recInvoiceAgtList);
}