package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.data.model.RecInvoiceExample;
import org.primefaces.model.SortOrder;

import java.util.List;

/**
 * Created by robb on 24/04/2015.
 */
public interface InvoicesManagerService {

    List<RecInvoice> getAll();
    List<RecInvoice> getUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);
    List<RecInvoice> getUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo,int index,int pageSize,String sortField,SortOrder sortOrder);
    Integer countRowsUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);
    boolean save(RecInvoice recInvoice);
}