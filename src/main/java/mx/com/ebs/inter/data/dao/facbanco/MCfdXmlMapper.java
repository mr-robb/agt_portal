package mx.com.ebs.inter.data.dao.facbanco;

import java.util.List;
import mx.com.ebs.inter.data.model.facbanco.MCfdXml;
import mx.com.ebs.inter.data.model.facbanco.MCfdXmlExample;
import mx.com.ebs.inter.data.model.facbanco.MCfdXmlWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface MCfdXmlMapper {

    List<MCfdXmlWithBLOBs> selectByExampleWithBLOBs(MCfdXmlExample example);
    List<MCfdXml> selectByExample(MCfdXmlExample example);
    MCfdXmlWithBLOBs selectByPrimaryKey(Long ID);
    MCfdXmlWithBLOBs selectByForeignKey(Long ID);
}