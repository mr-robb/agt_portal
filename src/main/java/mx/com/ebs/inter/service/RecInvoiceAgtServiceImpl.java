package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecInvoiceAgtSearchBo;
import mx.com.ebs.inter.data.dao.agt.RecInvoiceAgtMapper;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;
import mx.com.ebs.inter.data.model.RecInvoiceAgt;
import mx.com.ebs.inter.data.model.RecInvoiceAgtExample;
import mx.com.ebs.inter.data.model.param.ParamNumeroFacturaEnviado;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static mx.com.ebs.inter.util.Validator.isEmptyString;

/**
 * Created by robb on 27/05/2015.
 */
@Transactional(Variables.TXM_PORTAL)
@Service
public class RecInvoiceAgtServiceImpl implements RecInvoiceAgtService {

    @Autowired
    private RecInvoiceAgtMapper recInvoiceAgtMapper;

    @Override
    public List<RecInvoiceAgt> getAll() {
        RecInvoiceAgtExample recInvoiceAgtExample = new RecInvoiceAgtExample();
        recInvoiceAgtExample.setOrderByClause("FECHA desc");
        return recInvoiceAgtMapper.selectByExample( recInvoiceAgtExample);
    }

    @Override
    public List<RecInvoiceAgt> getListUsingFilter(RecInvoiceAgtSearchBo recInvoiceAgtSearchBo) {
        if( recInvoiceAgtSearchBo == null ){
           return getAll();
        }
        RecInvoiceAgtExample example = new RecInvoiceAgtExample();
        example.setOrderByClause("FECHA desc");
        RecInvoiceAgtExample.Criteria criteria = example.createCriteria();

        if( !isEmptyString(recInvoiceAgtSearchBo.getNumFactura())){
            criteria.andNUMERO_FACTURAEqualTo(recInvoiceAgtSearchBo.getNumFactura());
        }
        if( !isEmptyString(recInvoiceAgtSearchBo.getNumAgt()) ){
            criteria.andAGENTEEqualTo(recInvoiceAgtSearchBo.getNumAgt());
        }
        if( !isEmptyString(recInvoiceAgtSearchBo.getRazonSocial()) ){
            criteria.andRAZON_SOCIALLike("%" +recInvoiceAgtSearchBo.getRazonSocial());
        }
        if( !isEmptyString(recInvoiceAgtSearchBo.getRfc()) ){
            criteria.andRFCEqualTo( recInvoiceAgtSearchBo.getRfc() );
        }
        if( recInvoiceAgtSearchBo.getImporteTotal() != null && recInvoiceAgtSearchBo.getImporteTotal() > 0D ){
            criteria.andIMPORTE_TOTALGreaterThanOrEqualTo( recInvoiceAgtSearchBo.getImporteTotal() );
        }
        if( recInvoiceAgtSearchBo.getFecha1() != null && recInvoiceAgtSearchBo.getFecha2() != null ){
            criteria.andFECHABetween(recInvoiceAgtSearchBo.getFecha1(), recInvoiceAgtSearchBo.getFecha2());
        }
        if( recInvoiceAgtSearchBo.getEstatus() != null ){
            criteria.andSIT_COMPROBANTEEqualTo(recInvoiceAgtSearchBo.getEstatus());
        }

        return recInvoiceAgtMapper.selectByExample(example);
    }

    @Override
    public String getNumeroFacturaEnviados(String numAgt, String anio, String mes) {
        return recInvoiceAgtMapper.selectNumeroFacturaEnviados(new ParamNumeroFacturaEnviado(numAgt,anio,mes));
    }

    @Override
    public void fillNumerofactura(List<ProcAgtFelEnvio> procAgtFelEnvioList) {
        if( procAgtFelEnvioList != null && !procAgtFelEnvioList.isEmpty() ){
            for( ProcAgtFelEnvio procAgtFelEnvio : procAgtFelEnvioList ){
                ParamNumeroFacturaEnviado param = new ParamNumeroFacturaEnviado();
                param.setNumAgt(procAgtFelEnvio.getID_AGENTE().toString());
                param.setAnio(procAgtFelEnvio.getANO_MES().toString().substring(0, 4));
                param.setMes(procAgtFelEnvio.getANO_MES().toString().substring(4,6));
                procAgtFelEnvio.setB_CORRECTO(recInvoiceAgtMapper.selectNumeroFacturaEnviados(param));
            }
        }
    }

    public RecInvoiceAgtMapper getRecInvoiceAgtMapper() {
        return recInvoiceAgtMapper;
    }

    public void setRecInvoiceAgtMapper(RecInvoiceAgtMapper recInvoiceAgtMapper) {
        this.recInvoiceAgtMapper = recInvoiceAgtMapper;
    }
}
