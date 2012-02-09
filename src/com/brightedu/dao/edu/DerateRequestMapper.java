package com.brightedu.dao.edu;

import com.brightedu.model.edu.DerateRequest;
import com.brightedu.model.edu.DerateRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DerateRequestMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int countByExample(DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int deleteByExample(DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Delete({ "delete from edu.derate_request",
			"where derate_id = #{derate_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer derate_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Insert({
			"insert into edu.derate_request (derate_id, student_id, ",
			"derate_fee, derate_content, ",
			"approve, approve_by, ",
			"user_id, register_date, ",
			"update_date, attachment)",
			"values (#{derate_id,jdbcType=NUMERIC}, #{student_id,jdbcType=NUMERIC}, ",
			"#{derate_fee,jdbcType=NUMERIC}, #{derate_content,jdbcType=VARCHAR}, ",
			"#{approve,jdbcType=BIT}, #{approve_by,jdbcType=VARCHAR}, ",
			"#{user_id,jdbcType=NUMERIC}, #{register_date,jdbcType=TIMESTAMP}, ",
			"#{update_date,jdbcType=TIMESTAMP}, #{attachment,jdbcType=BINARY})" })
	int insert(DerateRequest record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int insertSelective(DerateRequest record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	List<DerateRequest> selectByExampleWithBLOBs(DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	List<DerateRequest> selectByExample(DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Select({
			"select",
			"derate_id, student_id, derate_fee, derate_content, approve, approve_by, user_id, ",
			"register_date, update_date, attachment",
			"from edu.derate_request",
			"where derate_id = #{derate_id,jdbcType=NUMERIC}" })
	@ResultMap("ResultMapWithBLOBs")
	DerateRequest selectByPrimaryKey(Integer derate_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExampleSelective(@Param("record") DerateRequest record,
			@Param("example") DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExampleWithBLOBs(@Param("record") DerateRequest record,
			@Param("example") DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExample(@Param("record") DerateRequest record,
			@Param("example") DerateRequestExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByPrimaryKeySelective(DerateRequest record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Update({ "update edu.derate_request",
			"set student_id = #{student_id,jdbcType=NUMERIC},",
			"derate_fee = #{derate_fee,jdbcType=NUMERIC},",
			"derate_content = #{derate_content,jdbcType=VARCHAR},",
			"approve = #{approve,jdbcType=BIT},",
			"approve_by = #{approve_by,jdbcType=VARCHAR},",
			"user_id = #{user_id,jdbcType=NUMERIC},",
			"register_date = #{register_date,jdbcType=TIMESTAMP},",
			"update_date = #{update_date,jdbcType=TIMESTAMP},",
			"attachment = #{attachment,jdbcType=BINARY}",
			"where derate_id = #{derate_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKeyWithBLOBs(DerateRequest record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.derate_request
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Update({ "update edu.derate_request",
			"set student_id = #{student_id,jdbcType=NUMERIC},",
			"derate_fee = #{derate_fee,jdbcType=NUMERIC},",
			"derate_content = #{derate_content,jdbcType=VARCHAR},",
			"approve = #{approve,jdbcType=BIT},",
			"approve_by = #{approve_by,jdbcType=VARCHAR},",
			"user_id = #{user_id,jdbcType=NUMERIC},",
			"register_date = #{register_date,jdbcType=TIMESTAMP},",
			"update_date = #{update_date,jdbcType=TIMESTAMP}",
			"where derate_id = #{derate_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(DerateRequest record);
}