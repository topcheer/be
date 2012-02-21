package com.brightedu.dao.edu;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitAgentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RecruitAgentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int countByExample(RecruitAgentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int deleteByExample(RecruitAgentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	@Delete({ "delete from edu.recruit_agent",
			"where agent_id = #{agent_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer agent_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	@Insert({
			"insert into edu.recruit_agent (agent_id, agent_name, ",
			"agent_type_id, responsible_person, ",
			"contact_person, contact_address, ",
			"contact_phone, contact_mobile, ",
			"account_name, bank_name, ",
			"bank_account, remark, ",
			"user_id, college_url, ",
			"userid_for_college, password_for_college, ",
			"register_date)",
			"values (#{agent_id,jdbcType=NUMERIC}, #{agent_name,jdbcType=VARCHAR}, ",
			"#{agent_type_id,jdbcType=NUMERIC}, #{responsible_person,jdbcType=VARCHAR}, ",
			"#{contact_person,jdbcType=VARCHAR}, #{contact_address,jdbcType=VARCHAR}, ",
			"#{contact_phone,jdbcType=VARCHAR}, #{contact_mobile,jdbcType=VARCHAR}, ",
			"#{account_name,jdbcType=VARCHAR}, #{bank_name,jdbcType=VARCHAR}, ",
			"#{bank_account,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
			"#{user_id,jdbcType=NUMERIC}, #{college_url,jdbcType=VARCHAR}, ",
			"#{userid_for_college,jdbcType=VARCHAR}, #{password_for_college,jdbcType=VARCHAR}, ",
			"#{register_date,jdbcType=TIMESTAMP})" })
	int insert(RecruitAgent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int insertSelective(RecruitAgent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	List<RecruitAgent> selectByExample(RecruitAgentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	@Select({
			"select",
			"agent_id, agent_name, agent_type_id, responsible_person, contact_person, contact_address, ",
			"contact_phone, contact_mobile, account_name, bank_name, bank_account, remark, ",
			"user_id, college_url, userid_for_college, password_for_college, register_date",
			"from edu.recruit_agent",
			"where agent_id = #{agent_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	RecruitAgent selectByPrimaryKey(Integer agent_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int updateByExampleSelective(@Param("record") RecruitAgent record,
			@Param("example") RecruitAgentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int updateByExample(@Param("record") RecruitAgent record,
			@Param("example") RecruitAgentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	int updateByPrimaryKeySelective(RecruitAgent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.recruit_agent
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	@Update({ "update edu.recruit_agent",
			"set agent_name = #{agent_name,jdbcType=VARCHAR},",
			"agent_type_id = #{agent_type_id,jdbcType=NUMERIC},",
			"responsible_person = #{responsible_person,jdbcType=VARCHAR},",
			"contact_person = #{contact_person,jdbcType=VARCHAR},",
			"contact_address = #{contact_address,jdbcType=VARCHAR},",
			"contact_phone = #{contact_phone,jdbcType=VARCHAR},",
			"contact_mobile = #{contact_mobile,jdbcType=VARCHAR},",
			"account_name = #{account_name,jdbcType=VARCHAR},",
			"bank_name = #{bank_name,jdbcType=VARCHAR},",
			"bank_account = #{bank_account,jdbcType=VARCHAR},",
			"remark = #{remark,jdbcType=VARCHAR},",
			"user_id = #{user_id,jdbcType=NUMERIC},",
			"college_url = #{college_url,jdbcType=VARCHAR},",
			"userid_for_college = #{userid_for_college,jdbcType=VARCHAR},",
			"password_for_college = #{password_for_college,jdbcType=VARCHAR},",
			"register_date = #{register_date,jdbcType=TIMESTAMP}",
			"where agent_id = #{agent_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(RecruitAgent record);
}