package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecAccesoSearchBo;
import mx.com.ebs.inter.data.dao.agt.RecAccesoMapper;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.data.model.RecAccesoExample;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by robb on 30/05/2015.
 */
@Service
public class RecAccesoServiceImpl implements RecAccesoService {
    @Autowired
    private RecAccesoMapper recAccesoMapper;

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public List<RecAcceso> getAll() {
        return recAccesoMapper.selectByExample(new RecAccesoExample());
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public List<RecAcceso> getListUsingFilter(final RecAccesoSearchBo recAccesoSearchBo,int first, int pageSize, String sortField, SortOrder sortOrder) {
        if( recAccesoSearchBo == null ){
            return getAll();
        }
        RecAccesoExample recAccesoExample = createExample(recAccesoSearchBo);
        recAccesoExample.setPageIndex(first);
        recAccesoExample.setPageSize(pageSize);
        if( sortField == null ) {
            recAccesoExample.setOrderByClause("EBS_USER_ID desc");
        }else{
            recAccesoExample.setOrderByClause(sortField + (SortOrder.ASCENDING.equals(sortOrder) ? " asc" : " desc") );
        }
        return recAccesoMapper.selectByExample( recAccesoExample );

    }

    private RecAccesoExample createExample(RecAccesoSearchBo recAccesoSearchBo){
        RecAccesoExample recAccesoExample = new RecAccesoExample();
        RecAccesoExample.Criteria criteria = recAccesoExample.createCriteria();

        if( !Validator.isEmptyString(recAccesoSearchBo.getUser())){
            criteria.andEBS_USER_IDEqualTo( recAccesoSearchBo.getUser() );
        }
        if( !Validator.isEmptyString(recAccesoSearchBo.getUsername()) ){
            criteria.andEBS_NOMBREEqualTo(recAccesoSearchBo.getUsername());
        }
        if( recAccesoSearchBo.getTipoUser() != null && !recAccesoSearchBo.getTipoUser().isEmpty() ){
            criteria.andEBS_TIPO_USERIn( recAccesoSearchBo.getTipoUser() );
        }
        if( recAccesoSearchBo.getStatus() != null ){
            criteria.andSTATUSEqualTo(recAccesoSearchBo.getStatus());
        }
        if( recAccesoSearchBo.getFechaModificacion1() !=null && recAccesoSearchBo.getFechaModificacion2()!=null ){
            criteria.andFECHA_MODIFICACIONBetween(recAccesoSearchBo.getFechaModificacion1(),recAccesoSearchBo.getFechaModificacion2());
        }
        if( !Validator.isEmptyString(recAccesoSearchBo.getEmail()) ){
            criteria.andEBS_EMAILEqualTo( recAccesoSearchBo.getEmail() );
        }
        if( !Validator.isEmptyString(recAccesoSearchBo.getNoCte()) ){
            criteria.andNUMERO_CLIENTEEqualTo(recAccesoSearchBo.getNoCte());
        }
        return recAccesoExample;
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public RecAcceso findByEbsUserId(String ebsUserId) throws ValidationException{
        if( ebsUserId == null ){
            throw new ValidationException("ebsUserId cannot be null or empty");
        }
        RecAccesoExample recAccesoExample = new RecAccesoExample();
        RecAccesoExample.Criteria criteria = recAccesoExample.createCriteria();
        criteria.andEBS_USER_IDEqualTo( ebsUserId );
        recAccesoExample.setOrderByClause("EBS_USER_ID desc");
        recAccesoExample.setPageIndex(0);
        recAccesoExample.setPageSize(1);
        List<RecAcceso> resultList = getRecAccesoMapper().selectByExample(recAccesoExample);
        if( resultList != null && !resultList.isEmpty() ){
            return resultList.get(0);
        }
        return null;
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = false)
    @Override
    public boolean update(RecAcceso recAcceso) throws ValidationException{
        validate(recAcceso);
        return recAccesoMapper.updateByExample(recAcceso,null) > 0;
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = false)
    @Override
    public boolean delete(String ebsUserId) {
        if( Validator.isEmptyString(ebsUserId) ){
            return false;
        }
        RecAccesoExample recAccesoExample = new RecAccesoExample();
        RecAccesoExample.Criteria criteria = recAccesoExample.createCriteria();
        criteria.andEBS_USER_IDEqualTo(ebsUserId.trim());
        return recAccesoMapper.deleteByExample( recAccesoExample ) > 0;
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = false)
    @Override
    public boolean save(final RecAcceso recAcceso) throws ValidationException {
        validate(recAcceso);
        recAcceso.setFECHA_MODIFICACION(Calendar.getInstance().getTime());
        recAcceso.setSTATUS(BigDecimal.ZERO);
        return recAccesoMapper.insertSelective(recAcceso) > 0;
    }

    @Override
    public boolean isEmailAvailableForUpdate(String ebsUserId, String email) throws ValidationException {
        if( Validator.isEmptyString(ebsUserId) || Validator.isEmptyString(email) ){
            throw new ValidationException("Alguno de los parametros enviados no es valido: ebsUserId|email");
        }
        RecAccesoExample recAccesoExample = new RecAccesoExample();
        RecAccesoExample.Criteria criteria = recAccesoExample.createCriteria();
        criteria.andEBS_USER_IDNotEqualTo(ebsUserId);
        criteria.andEBS_EMAILEqualTo( email );
        recAccesoExample.setOrderByClause("EBS_USER_ID desc");
        recAccesoExample.setPageIndex(0);
        recAccesoExample.setPageSize(1);
        List<RecAcceso> resultList = getRecAccesoMapper().selectByExample(recAccesoExample);
        return resultList == null || resultList.isEmpty();
    }

    public RecAccesoMapper getRecAccesoMapper() {
        return recAccesoMapper;
    }

    public void setRecAccesoMapper(RecAccesoMapper recAccesoMapper) {
        this.recAccesoMapper = recAccesoMapper;
    }

    private void validate(final RecAcceso recAcceso)  throws ValidationException{
        if( recAcceso == null ){
            throw new ValidationException("instance of RecAcceso expected, found null");
        }
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = false)
    @Override
    public int updateStatus(String ebsUserId) {
        return recAccesoMapper.updateStatus(ebsUserId);
    }

    @Transactional( value = Variables.TXM_PORTAL, readOnly = true)
    @Override
    public int countRowsUsingFilter(RecAccesoSearchBo recAccesoSearchBo) {
        return recAccesoMapper.countByExample(createExample(recAccesoSearchBo));
    }
}
