package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.dao.agt.RecFeloggerMapper;
import mx.com.ebs.inter.data.model.RecFelogger;
import mx.com.ebs.inter.data.model.RecFeloggerExample;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
@Service
public class RecFeloggerServiceImpl implements RecFeloggerService {

    @Autowired
    private RecFeloggerMapper recFeloggerMapper;

    @Transactional( value = Variables.TXM_PROCESO, readOnly = true)
    @Override
    public List<RecFelogger> getAll() {
        return recFeloggerMapper.selectByExample(new RecFeloggerExample());
    }

    @Transactional( value = Variables.TXM_PROCESO, readOnly = true)
    @Override
    public List<RecFelogger> getUsingFilter(final RecFeloggerSearchBo recFeloggerSearchBo) {
        if( recFeloggerSearchBo == null ){
            return getAll();
        }
        RecFeloggerExample recFeloggerExample = new RecFeloggerExample();
        recFeloggerExample.setOrderByClause(" FECHA desc");
        RecFeloggerExample.Criteria criteria = recFeloggerExample.createCriteria();

        if( !Validator.isEmptyString(recFeloggerSearchBo.getId()) ){
            criteria.andIDEqualTo(recFeloggerSearchBo.getId());
        }
        if( !Validator.isEmptyString(recFeloggerSearchBo.getNumAgt()) ){
            criteria.andAGENTEEqualTo(recFeloggerSearchBo.getNumAgt());
        }
        if( !Validator.isEmptyString(recFeloggerSearchBo.getMes()) ){
            criteria.andMESEqualTo(recFeloggerSearchBo.getMes());
        }
        if( !Validator.isEmptyString(recFeloggerSearchBo.getAnio()) ){
            criteria.andANOEqualTo(recFeloggerSearchBo.getAnio());
        }
        if( !Validator.isEmptyString(recFeloggerSearchBo.getMsg()) ){
            criteria.andMENSAJELike("%" + recFeloggerSearchBo.getMsg() + "%");
        }
        if( recFeloggerSearchBo.getFecha1() != null && recFeloggerSearchBo.getFecha2() != null ){
            criteria.andFECHABetween(recFeloggerSearchBo.getFecha1(),recFeloggerSearchBo.getFecha2());
        }
        return recFeloggerMapper.selectByExample(recFeloggerExample);
    }

    @Override
    @Transactional(value = Variables.TXM_PROCESO , readOnly = false)
    public boolean save(RecFelogger recFelogger) {
        if( recFelogger == null ){
            return false;
        }
        return recFeloggerMapper.insert(recFelogger) > 0;
    }

    public RecFeloggerMapper getRecFeloggerMapper() {
        return recFeloggerMapper;
    }

    public void setRecFeloggerMapper(RecFeloggerMapper recFeloggerMapper) {
        this.recFeloggerMapper = recFeloggerMapper;
    }
}
