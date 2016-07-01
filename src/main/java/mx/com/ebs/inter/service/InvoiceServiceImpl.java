package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.dao.agt.InvoiceMapper;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.data.model.facbanco.InvoiceExample;
import mx.com.ebs.inter.util.Variables;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static mx.com.ebs.inter.util.Validator.isEmptyString;

/**
 * Created by robb on 25/06/2015.
 */
@Service
@Transactional( value = Variables.TXM_PORTAL, readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public List<Invoice> getAll() {
        InvoiceExample invoiceExample = new InvoiceExample();
        invoiceExample.setOrderByClause("FECHA_EMISION desc");
        return invoiceMapper.selectByExample(invoiceExample);
    }

    @Override
    public List<Invoice> getListUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo,int first, int pageSize, String sortField, SortOrder sortOrder) {
        if( recInvoiceSearchBo == null ){
            return getAll();
        }
        InvoiceExample invoiceExample = createExample(recInvoiceSearchBo);
        if( sortField == null ) {
            invoiceExample.setOrderByClause("FECHA desc");
        }else{
            invoiceExample.setOrderByClause(sortField + (SortOrder.ASCENDING.equals(sortOrder) ? " asc" : " desc") );
        }
        invoiceExample.setPageIndex(first);
        invoiceExample.setPageSize(pageSize);
        return invoiceMapper.selectByExample(invoiceExample);
    }

    private InvoiceExample createExample(RecInvoiceSearchBo recInvoiceSearchBo){
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        if( !isEmptyString(recInvoiceSearchBo.getNumFactura())){
            criteria.andNUMERO_FACTURAEqualTo(recInvoiceSearchBo.getNumFactura());
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumPoliza()) ){
            criteria.andPOLIZAEqualTo(recInvoiceSearchBo.getNumPoliza());
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumAgente()) ){
            criteria.andCOMENTARIOSEqualTo(recInvoiceSearchBo.getNumAgente());
        }
        if( !isEmptyString(recInvoiceSearchBo.getRazonSocial()) ){
            criteria.andRAZON_SOCIALEqualTo(recInvoiceSearchBo.getRazonSocial());
        }
        if( !isEmptyString(recInvoiceSearchBo.getRfc()) ){
            criteria.andRFCEqualTo( recInvoiceSearchBo.getRfc() );
        }
        if( recInvoiceSearchBo.getImporteTotal() != null && recInvoiceSearchBo.getImporteTotal() > 0D ){
            criteria.andIMPORTE_TOTALGreaterThanOrEqualTo( recInvoiceSearchBo.getImporteTotal() );
        }
        if( recInvoiceSearchBo.getFecha1() != null && recInvoiceSearchBo.getFecha2() != null ){
            criteria.andFECHABetween(recInvoiceSearchBo.getFecha1(), recInvoiceSearchBo.getFecha2());
        }
        if( recInvoiceSearchBo.getEstatus() != null ){
            criteria.andESTADO_DOCUMENTOEqualTo(recInvoiceSearchBo.getEstatus().toString());
        }
        return invoiceExample;
    }

    @Override
    public int countRowsUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo) {
        return invoiceMapper.countByExample(createExample(recInvoiceSearchBo));
    }

    public InvoiceMapper getInvoiceMapper() {
        return invoiceMapper;
    }

    public void setInvoiceMapper(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }
}
