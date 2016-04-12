package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.model.RecFeloggerAgt;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public interface RecFeloggerAgtService {

    List<RecFeloggerAgt> getAll();
    List<RecFeloggerAgt> getUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo);

}
