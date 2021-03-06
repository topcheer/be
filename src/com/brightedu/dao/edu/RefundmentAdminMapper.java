package com.brightedu.dao.edu;

import com.brightedu.model.edu.RefundmentAdmin;
import com.brightedu.model.edu.RefundmentAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RefundmentAdminMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int countByExample(RefundmentAdminExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int deleteByExample(RefundmentAdminExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	@Delete({ "delete from edu.refundment_admin",
			"where refund_id = #{refund_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer refund_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	@Insert({
			"insert into edu.refundment_admin (refund_id, student_id, ",
			"refund_fee, refund_desc, ",
			"refund_flag, user_id, ",
			"register_date, update_date)",
			"values (#{refund_id,jdbcType=NUMERIC}, #{student_id,jdbcType=NUMERIC}, ",
			"#{refund_fee,jdbcType=NUMERIC}, #{refund_desc,jdbcType=VARCHAR}, ",
			"#{refund_flag,jdbcType=BIT}, #{user_id,jdbcType=NUMERIC}, ",
			"#{register_date,jdbcType=TIMESTAMP}, #{update_date,jdbcType=TIMESTAMP})" })
	int insert(RefundmentAdmin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int insertSelective(RefundmentAdmin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	List<RefundmentAdmin> selectByExample(RefundmentAdminExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	@Select({
			"select",
			"refund_id, student_id, refund_fee, refund_desc, refund_flag, user_id, register_date, ",
			"update_date", "from edu.refundment_admin",
			"where refund_id = #{refund_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	RefundmentAdmin selectByPrimaryKey(Integer refund_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int updateByExampleSelective(@Param("record") RefundmentAdmin record,
			@Param("example") RefundmentAdminExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int updateByExample(@Param("record") RefundmentAdmin record,
			@Param("example") RefundmentAdminExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	int updateByPrimaryKeySelective(RefundmentAdmin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	@Update({ "update edu.refundment_admin",
			"set student_id = #{student_id,jdbcType=NUMERIC},",
			"refund_fee = #{refund_fee,jdbcType=NUMERIC},",
			"refund_desc = #{refund_desc,jdbcType=VARCHAR},",
			"refund_flag = #{refund_flag,jdbcType=BIT},",
			"user_id = #{user_id,jdbcType=NUMERIC},",
			"register_date = #{register_date,jdbcType=TIMESTAMP},",
			"update_date = #{update_date,jdbcType=TIMESTAMP}",
			"where refund_id = #{refund_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(RefundmentAdmin record);
}