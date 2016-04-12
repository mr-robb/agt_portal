package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.dao.facbanco.MEmpresaMapper;
import mx.com.ebs.inter.data.model.facbanco.MEmpresa;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by robb on 12/08/2015.
 */
@Transactional(value = Variables.TXM_FACBANCO,readOnly = true)
@Service
public class MEmpresaServiceImpl implements MEmpresaService {

    @Autowired
    private MEmpresaMapper mEmpresaMapper;

    @Override
    public MEmpresa getByPrimaryKey(Long pk) {
        if( pk == null ){
            return null;
        }
        return mEmpresaMapper.selectByPrimaryKey(pk);
    }

    @Override
    public MEmpresa getByRfc(String rfc) {
        if(Validator.isEmptyString(rfc) ){
            return null;
        }
        return mEmpresaMapper.selectByRFC(rfc);
    }

    public MEmpresaMapper getmEmpresaMapper() {
        return mEmpresaMapper;
    }

    public void setmEmpresaMapper(MEmpresaMapper mEmpresaMapper) {
        this.mEmpresaMapper = mEmpresaMapper;
    }
}
