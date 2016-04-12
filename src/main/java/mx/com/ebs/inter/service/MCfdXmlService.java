package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.model.facbanco.MCfdXmlWithBLOBs;

/**
 * Created by robb on 12/08/2015.
 */
public interface MCfdXmlService {

    MCfdXmlWithBLOBs getByPrimaryKey(Long pk);
    MCfdXmlWithBLOBs getByForeignKey( Long fk );
}
