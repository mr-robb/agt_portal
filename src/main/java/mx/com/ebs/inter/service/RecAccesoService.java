package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecAccesoSearchBo;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.exception.ValidationException;

import java.util.List;

/**
 * Created by robb on 30/05/2015.
 */
public interface RecAccesoService {

    List<RecAcceso> getAll();
    List<RecAcceso> getUsingFilter(RecAccesoSearchBo recAccesoSearchBo);
    RecAcceso findByEbsUserId(String ebsUserId) throws ValidationException;
    boolean update( RecAcceso recAcceso ) throws ValidationException;
    boolean delete( String ebsUserId );
    boolean save(RecAcceso recAcceso) throws ValidationException;
    boolean isEmailAvailableForUpdate(String ebsUserId, String email) throws ValidationException;
    int updateStatus( String ebsUserId );
}