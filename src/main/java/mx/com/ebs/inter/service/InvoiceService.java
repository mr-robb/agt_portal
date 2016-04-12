package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.facbanco.Invoice;

import java.util.List;

/**
 * Created by robb on 24/06/2015.
 */
public interface InvoiceService {

    List<Invoice> getAll();
    List<Invoice> getUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);

}