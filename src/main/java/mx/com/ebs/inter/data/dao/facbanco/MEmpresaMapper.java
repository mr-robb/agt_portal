package mx.com.ebs.inter.data.dao.facbanco;

import java.util.List;
import mx.com.ebs.inter.data.model.facbanco.MEmpresa;
import mx.com.ebs.inter.data.model.facbanco.MEmpresaExample;
import org.apache.ibatis.annotations.Param;

public interface MEmpresaMapper {
    List<MEmpresa> selectByExampleWithBLOBs(MEmpresaExample example);
    List<MEmpresa> selectByExample(MEmpresaExample example);
    MEmpresa selectByPrimaryKey(Long ID);
    MEmpresa selectByRFC(String RFC);
}