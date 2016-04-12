package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.dao.facbanco.MCfdMapper;
import mx.com.ebs.inter.data.model.facbanco.MCfd;
import mx.com.ebs.inter.data.model.facbanco.MCfdExample;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static mx.com.ebs.inter.util.Validator.isEmptyString;

/**
 * Created by robb on 15/06/2015.
 */
@Transactional(value = Variables.TXM_FACBANCO,readOnly = true)
@Service
public class MCfdServiceImpl implements MCfdService {

    @Autowired
    private MCfdMapper mCfdMapper;

    @Override
    public List<MCfd> getAll() {
        MCfdExample mCfdExample = new MCfdExample();
        mCfdExample.setOrderByClause("FECHA desc");
        return mCfdMapper.selectByExample(mCfdExample);
    }

    @Override
    public List<MCfd> getUsingFilter(RecInvoiceSearchBo recInvoiceSearchBo) {
        if( recInvoiceSearchBo == null ){
            return getAll();
        }
        MCfdExample mCfdExample = new MCfdExample();
        mCfdExample.setOrderByClause("FECHA desc");
        MCfdExample.Criteria criteria = mCfdExample.createCriteria();
        if( !isEmptyString(recInvoiceSearchBo.getNumFactura())){
            criteria.andNUMERO_FACTURAEqualTo(recInvoiceSearchBo.getNumFactura());
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumAgente()) ){
            criteria.andNUM_AGENTE_EQUALS(recInvoiceSearchBo.getNumAgente());
        }
        if( !isEmptyString(recInvoiceSearchBo.getRazonSocial()) ){
            criteria.andRAZON_SOCIALEqualTo(recInvoiceSearchBo.getRazonSocial());
        }
        if( !isEmptyString(recInvoiceSearchBo.getRfc()) ){
            criteria.andRFCEqualTo( recInvoiceSearchBo.getRfc() );
        }
        if( !isEmptyString(recInvoiceSearchBo.getNumPoliza()) ){
            criteria.andNUM_POLIZA_EQUALS(recInvoiceSearchBo.getNumPoliza());
        }
        if( recInvoiceSearchBo.getImporteTotal() != null && recInvoiceSearchBo.getImporteTotal() > 0D ){
            criteria.andTOTALGreaterThanOrEqualTo(recInvoiceSearchBo.getImporteTotal());
        }
        if( recInvoiceSearchBo.getFecha1() != null && recInvoiceSearchBo.getFecha2() != null ){
            criteria.andFECHABetween(recInvoiceSearchBo.getFecha1(), recInvoiceSearchBo.getFecha2());
        }
        if( recInvoiceSearchBo.getEstatus() != null ){
            criteria.andESTADO_DOCUMENTOEqualTo(recInvoiceSearchBo.getEstatus().longValue());
        }

        return mCfdMapper.selectByExample( mCfdExample );
    }

    @Override
    public MCfd findById(Long id) {
        return mCfdMapper.selectByPrimaryKey(id);
    }

    public MCfdMapper getmCfdMapper() {
        return mCfdMapper;
    }

    public void setmCfdMapper(MCfdMapper mCfdMapper) {
        this.mCfdMapper = mCfdMapper;
    }
}
