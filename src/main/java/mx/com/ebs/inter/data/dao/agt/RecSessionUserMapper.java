package mx.com.ebs.inter.data.dao.agt;

import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.data.model.RecSessionUser;
import org.apache.ibatis.annotations.Param;

/**
 * Created by rfonseca on 6/9/16.
 */
public interface RecSessionUserMapper {

    RecSessionUser find(String ebsUserId);
    int insert(RecSessionUser record);
    int updateLogin(@Param("record") RecSessionUser record);
    int updateLogout(@Param("record") RecSessionUser record);
}
