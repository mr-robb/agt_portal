package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.facbanco.MCfd;

import java.util.List;

/**
 * Created by robb on 15/06/2015.
 */
public interface MCfdService {

    List<MCfd> getAll();
    List<MCfd> getUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo);
    MCfd findById(Long id);
}
