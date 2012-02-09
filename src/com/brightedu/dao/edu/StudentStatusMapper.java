package com.brightedu.dao.edu;

import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StudentStatusMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int countByExample(StudentStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int deleteByExample(StudentStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Delete({ "delete from edu.student_status",
			"where stu_status_id = #{stu_status_id,jdbcType=NUMERIC}" })
	int deleteByPrimaryKey(Integer stu_status_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Insert({
			"insert into edu.student_status (stu_status_id, stu_status_name)",
			"values (#{stu_status_id,jdbcType=NUMERIC}, #{stu_status_name,jdbcType=NUMERIC})" })
	int insert(StudentStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int insertSelective(StudentStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	List<StudentStatus> selectByExample(StudentStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Select({ "select", "stu_status_id, stu_status_name",
			"from edu.student_status",
			"where stu_status_id = #{stu_status_id,jdbcType=NUMERIC}" })
	@ResultMap("BaseResultMap")
	StudentStatus selectByPrimaryKey(Integer stu_status_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExampleSelective(@Param("record") StudentStatus record,
			@Param("example") StudentStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByExample(@Param("record") StudentStatus record,
			@Param("example") StudentStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	int updateByPrimaryKeySelective(StudentStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.student_status
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	@Update({ "update edu.student_status",
			"set stu_status_name = #{stu_status_name,jdbcType=NUMERIC}",
			"where stu_status_id = #{stu_status_id,jdbcType=NUMERIC}" })
	int updateByPrimaryKey(StudentStatus record);
}