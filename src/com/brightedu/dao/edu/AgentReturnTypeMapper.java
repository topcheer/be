package com.brightedu.dao.edu;

import com.brightedu.model.edu.AgentReturnType;
import com.brightedu.model.edu.AgentReturnTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AgentReturnTypeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int countByExample(AgentReturnTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int deleteByExample(AgentReturnTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	@Delete({ "delete from edu.agent_return_type",
			"where ag_return_type_id = #{ag_return_type_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer ag_return_type_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	@Insert({
			"insert into edu.agent_return_type (ag_return_type_id, aggregation_desc, ",
			"fee_id, register_date, ",
			"update_date)",
			"values (#{ag_return_type_id,jdbcType=NUMERIC}, #{aggregation_desc,jdbcType=VARCHAR}, ",
			"#{fee_id,jdbcType=NUMERIC}, #{register_date,jdbcType=TIMESTAMP}, ",
			"#{update_date,jdbcType=TIMESTAMP})" })
	int insert(AgentReturnType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int insertSelective(AgentReturnType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	List<AgentReturnType> selectByExample(AgentReturnTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	@Select({
			"select",
			"ag_return_type_id, aggregation_desc, fee_id, register_date, update_date",
			"from edu.agent_return_type",
			"where ag_return_type_id = #{ag_return_type_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	AgentReturnType selectByPrimaryKey(Integer ag_return_type_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int updateByExampleSelective(@Param("record") AgentReturnType record,
			@Param("example") AgentReturnTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int updateByExample(@Param("record") AgentReturnType record,
			@Param("example") AgentReturnTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	int updateByPrimaryKeySelective(AgentReturnType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.agent_return_type
	 * @mbggenerated  Tue Feb 21 22:02:26 CST 2012
	 */
	@Update({ "update edu.agent_return_type",
			"set aggregation_desc = #{aggregation_desc,jdbcType=VARCHAR},",
			"fee_id = #{fee_id,jdbcType=NUMERIC},",
			"register_date = #{register_date,jdbcType=TIMESTAMP},",
			"update_date = #{update_date,jdbcType=TIMESTAMP}",
			"where ag_return_type_id = #{ag_return_type_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(AgentReturnType record);
}