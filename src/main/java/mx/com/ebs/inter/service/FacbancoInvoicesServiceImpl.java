package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.dao.agt.InvoiceMapper;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.data.model.facbanco.InvoiceExample;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robb on 15/06/2015.
 */
@Transactional(value = Variables.TXM_FACBANCO, readOnly = true)
@Service
public class FacbancoInvoicesServiceImpl implements FacbancoInvoicesService {

    @Autowired
    private  InvoiceMapper invoiceMapper;

    public InvoiceMapper getInvoiceMapper() {
        return invoiceMapper;
    }

    public void setInvoiceMapper(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public List<Invoice> getAll() {
        InvoiceExample invoiceExample = new InvoiceExample();
        invoiceExample.setOrderByClause("FECHA_EMISION desc");
        return invoiceMapper.selectByExample(invoiceExample);
    }

    @Override
    public List<Invoice> getUsingFilter() {
        // TODO implement filter param
        return getAll();
    }
}
