package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.dao.agt.RecFeloggerAgtMapper;
import mx.com.ebs.inter.data.model.RecFeloggerAgt;
import mx.com.ebs.inter.data.model.RecFeloggerAgtExample;
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
public class RecFeloggerAgtServiceImpl implements RecFeloggerAgtService {

    @Autowired
    private RecFeloggerAgtMapper recFeloggerAgtMapper;

    @Transactional( value = Variables.TXM_PROCESO, readOnly = true)
    @Override
    public List<RecFeloggerAgt> getAll() {
        return recFeloggerAgtMapper.selectByExample(new RecFeloggerAgtExample());
    }

    @Transactional( value = Variables.TXM_PROCESO, readOnly = true)
    @Override
    public List<RecFeloggerAgt> getUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo) {
        if( recFeloggerSearchBo == null ){
            return getAll();
        }
        RecFeloggerAgtExample recFeloggerAgtExample = new RecFeloggerAgtExample();
        recFeloggerAgtExample.setOrderByClause(" FECHA desc");
        RecFeloggerAgtExample.Criteria criteria = recFeloggerAgtExample.createCriteria();

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

        return recFeloggerAgtMapper.selectByExample(recFeloggerAgtExample);
    }

    public RecFeloggerAgtMapper getRecFeloggerAgtMapper() {
        return recFeloggerAgtMapper;
    }

    public void setRecFeloggerAgtMapper(RecFeloggerAgtMapper recFeloggerAgtMapper) {
        this.recFeloggerAgtMapper = recFeloggerAgtMapper;
    }
}
