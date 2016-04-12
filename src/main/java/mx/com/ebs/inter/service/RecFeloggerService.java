package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.model.RecFelogger;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public interface RecFeloggerService {

    List<RecFelogger> getAll();
    List<RecFelogger> getUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo);
    boolean save( RecFelogger recFelogger );
}
