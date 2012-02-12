package com.brightedu.dao.edu;

import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.PictureTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PictureTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int countByExample(PictureTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int deleteByExample(PictureTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    @Delete({
        "delete from edu.pic_type",
        "where pic_type_id = #{pic_type_id,jdbcType=NUMERIC}"
    })
    int deleteByPrimaryKey(Integer pic_type_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    @Insert({
        "insert into edu.pic_type (pic_type_id, pic_type_name)",
        "values (#{pic_type_id,jdbcType=NUMERIC}, #{pic_type_name,jdbcType=VARCHAR})"
    })
    int insert(PictureType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int insertSelective(PictureType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    List<PictureType> selectByExample(PictureTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    @Select({
        "select",
        "pic_type_id, pic_type_name",
        "from edu.pic_type",
        "where pic_type_id = #{pic_type_id,jdbcType=NUMERIC}"
    })
    @ResultMap("BaseResultMap")
    PictureType selectByPrimaryKey(Integer pic_type_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int updateByExampleSelective(@Param("record") PictureType record, @Param("example") PictureTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int updateByExample(@Param("record") PictureType record, @Param("example") PictureTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    int updateByPrimaryKeySelective(PictureType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.pic_type
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    @Update({
        "update edu.pic_type",
        "set pic_type_name = #{pic_type_name,jdbcType=VARCHAR}",
        "where pic_type_id = #{pic_type_id,jdbcType=NUMERIC}"
    })
    int updateByPrimaryKey(PictureType record);
}