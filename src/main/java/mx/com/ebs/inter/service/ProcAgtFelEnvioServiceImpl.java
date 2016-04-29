package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.ProcAgtFelEnvioSearchBo;
import mx.com.ebs.inter.data.dao.proc.ProcAgtFelEnvioMapper;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvioExample;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvioKey;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
@Service
@Transactional(value = Variables.TXM_PROCESO ,  readOnly = true)
public class ProcAgtFelEnvioServiceImpl implements ProcAgtFelEnvioService {

    @Autowired
    private ProcAgtFelEnvioMapper procAgtFelEnvioMapper;

    @Override
    public List<ProcAgtFelEnvio> getAll() {
        return procAgtFelEnvioMapper.selectByExample(new ProcAgtFelEnvioExample());
    }

    @Override
    public List<ProcAgtFelEnvio> getListUsingFilter(final ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo,int first, int pageSize, String sortField, SortOrder sortOrder) {
        if( procAgtFelEnvioSearchBo == null ){
            return getAll();
        }
        ProcAgtFelEnvioExample procAgtFelEnvioExample = createExample(procAgtFelEnvioSearchBo);
        if( sortField == null ){
            procAgtFelEnvioExample.setOrderByClause(" FH_CARGA desc");
        }else{
            procAgtFelEnvioExample.setOrderByClause( sortField + (SortOrder.ASCENDING.equals(sortOrder) ? " asc" : " desc")  );
        }

        procAgtFelEnvioExample.setPageIndex(first);
        procAgtFelEnvioExample.setPageSize(pageSize);
        return procAgtFelEnvioMapper.selectByExample(procAgtFelEnvioExample);

    }

    @Override
    public int countRowsUsingFilter(ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo) {
        return procAgtFelEnvioMapper.countByExample(createExample(procAgtFelEnvioSearchBo));
    }

    private ProcAgtFelEnvioExample createExample(final ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo){
        ProcAgtFelEnvioExample procAgtFelEnvioExample = new ProcAgtFelEnvioExample();
        ProcAgtFelEnvioExample.Criteria criteria = procAgtFelEnvioExample.createCriteria();

        if( procAgtFelEnvioSearchBo.getAnioMes() != null ){
            criteria.andANO_MESEqualTo(procAgtFelEnvioSearchBo.getAnioMes());
        }
        if( procAgtFelEnvioSearchBo.getNumAgt() != null ){
            criteria.andID_AGENTEEqualTo(procAgtFelEnvioSearchBo.getNumAgt());
        }
        if(!Validator.isEmptyString(procAgtFelEnvioSearchBo.getRfc()) ){
            criteria.andRFCEqualTo(procAgtFelEnvioSearchBo.getRfc());
        }
        if( procAgtFelEnvioSearchBo.getFechaCarga1() != null && procAgtFelEnvioSearchBo.getFechaCarga2()!= null ){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(procAgtFelEnvioSearchBo.getFechaCarga1());
            calendar.set(Calendar.SECOND,1);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.HOUR,0);
            Date desde = calendar.getTime();
            calendar.setTime(procAgtFelEnvioSearchBo.getFechaCarga2());
            calendar.set(Calendar.SECOND,59);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.HOUR_OF_DAY,23);
            criteria.andFH_CARGABetween( new Timestamp(desde.getTime()), new Timestamp(calendar.getTimeInMillis()));
        }
        if( !Validator.isEmptyString(procAgtFelEnvioSearchBo.getEnviada()) ){
            if( Variables.YES_VALUE_OPTION.equals(procAgtFelEnvioSearchBo.getEnviada()) ){
                criteria.andFH_ENVIOIsNotNull();
            }else if(Variables.NO_VALUE_OPTION.equals(procAgtFelEnvioSearchBo.getEnviada()) ){
                criteria.andFH_ENVIOIsNull();
            }
        }
        if(!Validator.isEmptyString(procAgtFelEnvioSearchBo.getRecibida())){
            if( Variables.YES_VALUE_OPTION.equals(procAgtFelEnvioSearchBo.getRecibida()) ){
                criteria.andFH_RESPUESTAIsNotNull();
            }else if( Variables.NO_VALUE_OPTION.equals(procAgtFelEnvioSearchBo.getRecibida())){
                criteria.andFH_RESPUESTAIsNull();
            }
        }
        return procAgtFelEnvioExample;
    }

    @Override
    public ProcAgtFelEnvio getLastRecordByAgente(String idAgente) {
        ProcAgtFelEnvioExample example = new ProcAgtFelEnvioExample();
        ProcAgtFelEnvioExample.Criteria criteria = example.createCriteria();
        criteria.andID_AGENTEEqualTo(Integer.valueOf(idAgente));
        criteria.andFH_RESPUESTAIsNull();
        example.setOrderByClause("ANO_MES desc");
        List<ProcAgtFelEnvio> list = procAgtFelEnvioMapper.selectByExample(example);
        if( list != null && !list.isEmpty() ){
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional( value = Variables.TXM_PROCESO,readOnly = false)
    public boolean update(ProcAgtFelEnvio procAgtFelEnvio) {
        if( procAgtFelEnvio == null ) {
            return false;
        }
        return procAgtFelEnvioMapper.updateByPrimaryKey(procAgtFelEnvio) > 0;
    }

    public ProcAgtFelEnvioMapper getProcAgtFelEnvioMapper() {
        return procAgtFelEnvioMapper;
    }

    public void setProcAgtFelEnvioMapper(ProcAgtFelEnvioMapper procAgtFelEnvioMapper) {
        this.procAgtFelEnvioMapper = procAgtFelEnvioMapper;
    }
}