package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.dao.agt.RecPerfilesMapper;
import mx.com.ebs.inter.data.model.RecPerfiles;
import mx.com.ebs.inter.data.model.RecPerfilesExample;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robb on 31/05/2015.
 */
@Service
public class RecPerfilesServiceImpl implements RecPerfilesService {

    @Autowired
    private RecPerfilesMapper recPerfilesMapper;

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public List<RecPerfiles> getAll() {
        return recPerfilesMapper.selectByExample(new RecPerfilesExample());
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public RecPerfiles getByName(final String name) {
        if( Validator.isEmptyString(name) ){
            return null;
        }
        RecPerfilesExample recPerfilesExample = new RecPerfilesExample();
        RecPerfilesExample.Criteria criteria = recPerfilesExample.createCriteria();
        criteria.andPERFILEqualTo(name);
        List<RecPerfiles> resultList = recPerfilesMapper.selectByExample(recPerfilesExample);
        if( resultList != null && !resultList.isEmpty() ){
            return resultList.get(0);
        }
        return null;

    }

    public RecPerfilesMapper getRecPerfilesMapper() {
        return recPerfilesMapper;
    }

    public void setRecPerfilesMapper(RecPerfilesMapper recPerfilesMapper) {
        this.recPerfilesMapper = recPerfilesMapper;
    }
}
