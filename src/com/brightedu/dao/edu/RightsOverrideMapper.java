package com.brightedu.dao.edu;

import com.brightedu.model.edu.RightsOverride;
import com.brightedu.model.edu.RightsOverrideExample;
import com.brightedu.model.edu.RightsOverrideKey;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RightsOverrideMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int countByExample(RightsOverrideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int deleteByExample(RightsOverrideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Delete({
        "delete from edu.rights_override",
        "where user_id = #{user_id,jdbcType=NUMERIC}",
          "and rights_category_id = #{rights_category_id,jdbcType=VARCHAR}",
          "and rights_function_id = #{rights_function_id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(RightsOverrideKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Insert({
        "insert into edu.rights_override (user_id, rights_category_id, ",
        "rights_function_id, override)",
        "values (#{user_id,jdbcType=NUMERIC}, #{rights_category_id,jdbcType=VARCHAR}, ",
        "#{rights_function_id,jdbcType=VARCHAR}, #{override,jdbcType=BIT})"
    })
    int insert(RightsOverride record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int insertSelective(RightsOverride record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    List<RightsOverride> selectByExample(RightsOverrideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Select({
        "select",
        "user_id, rights_category_id, rights_function_id, override",
        "from edu.rights_override",
        "where user_id = #{user_id,jdbcType=NUMERIC}",
          "and rights_category_id = #{rights_category_id,jdbcType=VARCHAR}",
          "and rights_function_id = #{rights_function_id,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    RightsOverride selectByPrimaryKey(RightsOverrideKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByExampleSelective(@Param("record") RightsOverride record, @Param("example") RightsOverrideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByExample(@Param("record") RightsOverride record, @Param("example") RightsOverrideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    int updateByPrimaryKeySelective(RightsOverride record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_override
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    @Update({
        "update edu.rights_override",
        "set override = #{override,jdbcType=BIT}",
        "where user_id = #{user_id,jdbcType=NUMERIC}",
          "and rights_category_id = #{rights_category_id,jdbcType=VARCHAR}",
          "and rights_function_id = #{rights_function_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(RightsOverride record);
}