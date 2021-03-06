package mx.com.ebs.inter.data.dao.facbanco;

import java.util.List;
import mx.com.ebs.inter.data.model.facbanco.MOtro;
import mx.com.ebs.inter.data.model.facbanco.MOtroExample;
import org.apache.ibatis.annotations.Param;

public interface MOtroMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int countByExample(MOtroExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int deleteByExample(MOtroExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int deleteByPrimaryKey(Long ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int insert(MOtro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int insertSelective(MOtro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    List<MOtro> selectByExample(MOtroExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    MOtro selectByPrimaryKey(Long ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int updateByExampleSelective(@Param("record") MOtro record, @Param("example") MOtroExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int updateByExample(@Param("record") MOtro record, @Param("example") MOtroExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int updateByPrimaryKeySelective(MOtro record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_OTRO
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    int updateByPrimaryKey(MOtro record);
}