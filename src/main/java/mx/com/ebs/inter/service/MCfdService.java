package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.facbanco.MCfd;
import org.primefaces.model.SortOrder;

import java.util.List;

/**
 * Created by robb on 15/06/2015.
 */
public interface MCfdService {

    List<MCfd> getAll();
    List<MCfd> getListUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo,int first,int pageSize,String sortField, SortOrder sortOrder);
    int countRowsUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);
    MCfd findById(Long id);
}
