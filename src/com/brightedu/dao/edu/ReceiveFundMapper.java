package com.brightedu.dao.edu;

import com.brightedu.model.edu.ReceiveFund;
import com.brightedu.model.edu.ReceiveFundExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ReceiveFundMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int countByExample(ReceiveFundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int deleteByExample(ReceiveFundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	@Delete({ "delete from edu.receive_fund",
			"where fund_id = #{fund_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Long fund_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	@Insert({
			"insert into edu.receive_fund (fund_id, student_id, ",
			"charge_type_id, charge_time, ",
			"fund_income, user_id, ",
			"bank_code, remark)",
			"values (#{fund_id,jdbcType=NUMERIC}, #{student_id,jdbcType=NUMERIC}, ",
			"#{charge_type_id,jdbcType=NUMERIC}, #{charge_time,jdbcType=TIMESTAMP}, ",
			"#{fund_income,jdbcType=NUMERIC}, #{user_id,jdbcType=NUMERIC}, ",
			"#{bank_code,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})" })
	int insert(ReceiveFund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int insertSelective(ReceiveFund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	List<ReceiveFund> selectByExample(ReceiveFundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	@Select({
			"select",
			"fund_id, student_id, charge_type_id, charge_time, fund_income, user_id, bank_code, ",
			"remark", "from edu.receive_fund",
			"where fund_id = #{fund_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	ReceiveFund selectByPrimaryKey(Long fund_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int updateByExampleSelective(@Param("record") ReceiveFund record,
			@Param("example") ReceiveFundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int updateByExample(@Param("record") ReceiveFund record,
			@Param("example") ReceiveFundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	int updateByPrimaryKeySelective(ReceiveFund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.receive_fund
	 * @mbggenerated  Mon Jan 16 17:46:15 CST 2012
	 */
	@Update({ "update edu.receive_fund",
			"set student_id = #{student_id,jdbcType=NUMERIC},",
			"charge_type_id = #{charge_type_id,jdbcType=NUMERIC},",
			"charge_time = #{charge_time,jdbcType=TIMESTAMP},",
			"fund_income = #{fund_income,jdbcType=NUMERIC},",
			"user_id = #{user_id,jdbcType=NUMERIC},",
			"bank_code = #{bank_code,jdbcType=VARCHAR},",
			"remark = #{remark,jdbcType=VARCHAR}",
			"where fund_id = #{fund_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(ReceiveFund record);
}