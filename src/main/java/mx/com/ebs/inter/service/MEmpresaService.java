package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.model.facbanco.MEmpresa;

/**
 * Created by robb on 12/08/2015.
 */
public interface MEmpresaService {

    MEmpresa getByPrimaryKey(Long pk);
    MEmpresa getByRfc( String rfc );

}
