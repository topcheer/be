package com.brightedu.dao.edu;

import com.brightedu.model.edu.UserType;
import com.brightedu.model.edu.UserTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserTypeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int countByExample(UserTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int deleteByExample(UserTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Delete({ "delete from edu.user_type",
			"where user_type_id = #{user_type_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer user_type_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Insert({ "insert into edu.user_type (user_type_id, user_type_name)",
			"values (#{user_type_id,jdbcType=NUMERIC}, #{user_type_name,jdbcType=VARCHAR})" })
	int insert(UserType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int insertSelective(UserType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	List<UserType> selectByExample(UserTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Select({ "select", "user_type_id, user_type_name", "from edu.user_type",
			"where user_type_id = #{user_type_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	UserType selectByPrimaryKey(Integer user_type_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExampleSelective(@Param("record") UserType record,
			@Param("example") UserTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExample(@Param("record") UserType record,
			@Param("example") UserTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByPrimaryKeySelective(UserType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Update({ "update edu.user_type",
			"set user_type_name = #{user_type_name,jdbcType=VARCHAR}",
			"where user_type_id = #{user_type_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(UserType record);
}