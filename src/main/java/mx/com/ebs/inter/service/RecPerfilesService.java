package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.model.RecPerfiles;

import java.util.List;

/**
 * Created by robb on 31/05/2015.
 */
public interface RecPerfilesService {

    List<RecPerfiles> getAll();
    RecPerfiles getByName(String name);
}
