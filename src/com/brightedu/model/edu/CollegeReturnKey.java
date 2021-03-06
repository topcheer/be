package com.brightedu.model.edu;

import java.io.Serializable;

public class CollegeReturnKey implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.college_return.agreement_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	private Integer agreement_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.college_return.headcount
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	private Integer headcount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.college_return.batch_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	private Integer batch_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.college_return
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.college_return.agreement_id
	 * @return  the value of edu.college_return.agreement_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public Integer getAgreement_id() {
		return agreement_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.college_return.agreement_id
	 * @param agreement_id  the value for edu.college_return.agreement_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public void setAgreement_id(Integer agreement_id) {
		this.agreement_id = agreement_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.college_return.headcount
	 * @return  the value of edu.college_return.headcount
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public Integer getHeadcount() {
		return headcount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.college_return.headcount
	 * @param headcount  the value for edu.college_return.headcount
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public void setHeadcount(Integer headcount) {
		this.headcount = headcount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.college_return.batch_id
	 * @return  the value of edu.college_return.batch_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public Integer getBatch_id() {
		return batch_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.college_return.batch_id
	 * @param batch_id  the value for edu.college_return.batch_id
	 * @mbggenerated  Sat Feb 11 02:04:14 CST 2012
	 */
	public void setBatch_id(Integer batch_id) {
		this.batch_id = batch_id;
	}
}