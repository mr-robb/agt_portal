package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.RecFeloggerSearchBo;
import mx.com.ebs.inter.data.model.RecFeloggerAgt;
import org.primefaces.model.SortOrder;

import java.util.List;

/**
 * Created by robb on 28/05/2015.
 */
public interface RecFeloggerAgtService {

    List<RecFeloggerAgt> getAll();
    List<RecFeloggerAgt> getListUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo, int index, int pageSize,String sortField, SortOrder sortOrder);
    int countRowsUsingFilter(RecFeloggerSearchBo recFeloggerSearchBo);

}
