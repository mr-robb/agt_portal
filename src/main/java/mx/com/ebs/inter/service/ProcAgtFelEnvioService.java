package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.ProcAgtFelEnvioSearchBo;
import mx.com.ebs.inter.data.model.ProcAgtFelEnvio;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public interface ProcAgtFelEnvioService {

    List<ProcAgtFelEnvio> getAll();
    List<ProcAgtFelEnvio> getUsingFilter(ProcAgtFelEnvioSearchBo procAgtFelEnvioSearchBo);
    ProcAgtFelEnvio getLastRecordByAgente(String idAgente);
    boolean update( ProcAgtFelEnvio procAgtFelEnvio );
}
