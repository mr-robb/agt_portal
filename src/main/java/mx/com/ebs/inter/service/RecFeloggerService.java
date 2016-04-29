package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.model.RecFelogger;
import org.primefaces.model.SortOrder;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public interface RecFeloggerService {

    List<RecFelogger> getAll();
    List<RecFelogger> getListUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo,int first, int pageSize, String sortField, SortOrder sortOrder);
    Integer countRowsUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo);
    boolean save( RecFelogger recFelogger );
}
