package com.brightedu.dao.edu;

import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RightsCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int countByExample(RightsCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int deleteByExample(RightsCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Delete({
        "delete from edu.rights_category",
        "where category_id = #{category_id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String category_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Insert({
        "insert into edu.rights_category (category_id, category_name)",
        "values (#{category_id,jdbcType=VARCHAR}, #{category_name,jdbcType=VARCHAR})"
    })
    int insert(RightsCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int insertSelective(RightsCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    List<RightsCategory> selectByExample(RightsCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Select({
        "select",
        "category_id, category_name",
        "from edu.rights_category",
        "where category_id = #{category_id,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    RightsCategory selectByPrimaryKey(String category_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByExampleSelective(@Param("record") RightsCategory record, @Param("example") RightsCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByExample(@Param("record") RightsCategory record, @Param("example") RightsCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByPrimaryKeySelective(RightsCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_category
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Update({
        "update edu.rights_category",
        "set category_name = #{category_name,jdbcType=VARCHAR}",
        "where category_id = #{category_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(RightsCategory record);
}