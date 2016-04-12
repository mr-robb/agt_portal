package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.dao.facbanco.MCfdXmlMapper;
import mx.com.ebs.inter.data.model.facbanco.MCfdXmlWithBLOBs;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by robb on 12/08/2015.
 */
@Transactional(value = Variables.TXM_FACBANCO,readOnly = true)
@Service
public class MCfdXmlServiceImpl implements MCfdXmlService{

    @Autowired
    private MCfdXmlMapper mCfdXmlMapper;

    @Override
    public MCfdXmlWithBLOBs getByPrimaryKey(Long pk) {
        if( pk == null ){
            return null;
        }
        return mCfdXmlMapper.selectByPrimaryKey(pk);
    }

    @Override
    public MCfdXmlWithBLOBs getByForeignKey(Long fk) {
        if( fk == null ){
            return null;
        }
        return mCfdXmlMapper.selectByForeignKey(fk);
    }

    public MCfdXmlMapper getmCfdXmlMapper() {
        return mCfdXmlMapper;
    }

    public void setmCfdXmlMapper(MCfdXmlMapper mCfdXmlMapper) {
        this.mCfdXmlMapper = mCfdXmlMapper;
    }
}
