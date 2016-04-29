package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import org.primefaces.model.SortOrder;

import java.util.List;

/**
 * Created by robb on 24/06/2015.
 */
public interface InvoiceService {

    List<Invoice> getAll();
    List<Invoice> getListUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo,int first, int pageSize, String sortField, SortOrder sortOrder);
    int countRowsUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);

}