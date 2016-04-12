package mx.com.ebs.inter.service;

import static mx.com.ebs.inter.util.Validator.isEmptyString;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.dao.agt.RecInvoiceMapper;
import mx.com.ebs.inter.data.model.RecInvoice;
import mx.com.ebs.inter.data.model.RecInvoiceExample;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robb on 24/04/2015.
 */
@Service
@Transactional(Variables.TXM_PORTAL)
public class InvoicesManagerServiceImpl implements InvoicesManagerService {

    @Autowired
    private RecInvoiceMapper recInvoiceMapper;

    @Override
    public List<RecInvoice> getAll() {
        RecInvoiceExample recInvoiceExample = new RecInvoiceExample();
        recInvoiceExample.setOrderByClause("FECHA desc");
        return recInvoiceMapper.selectByExample( recInvoiceExample);
    }

    @Override
    public List<RecInvoice> getUsingFilter(final RecInvoiceSearchBo recInvoiceSearchBo){
        if( recInvoiceSearchBo == null ){
            return getAll();
        }
        RecInvoiceExample recInvoiceExample = new RecInvoiceExample();
        recInvoiceExample.setOrderByClause("FECHA desc");
        RecInvoiceExample.Criteria criteria = recInvoiceExample.createCriteria();
        if( !isEmptyString(recInvoiceSearchBo.getNumFactura())){
            criteria.andNUMERO_FACTURAEqualTo(recInvoiceSearchBo.getNumFactura());
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumPoliza()) ){
            criteria.andPOLIZAEqualTo(recInvoiceSearchBo.getNumPoliza());
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumAgente()) ){
            criteria.andAGENTEEqualTo( recInvoiceSearchBo.getNumAgente() );
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
            criteria.andSIT_COMPROBANTEEqualTo(recInvoiceSearchBo.getEstatus());
        }
        return recInvoiceMapper.selectByExample(recInvoiceExample);
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = false)
    @Override
    public boolean save(RecInvoice recInvoice) {
        if( recInvoice == null ){
            return false;
        }
        return recInvoiceMapper.insert(recInvoice)>0;
    }
}
