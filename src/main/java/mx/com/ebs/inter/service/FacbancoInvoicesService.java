package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.model.facbanco.Invoice;

import java.util.List;

/**
 * Created by robb on 15/06/2015.
 */
public interface FacbancoInvoicesService {

    List<Invoice> getAll();
    List<Invoice> getUsingFilter();
}
