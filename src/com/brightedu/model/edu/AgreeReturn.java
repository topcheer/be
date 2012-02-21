package com.brightedu.model.edu;

import java.io.Serializable;
import java.util.Date;

public class AgreeReturn  implements Serializable{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agree_return.agree_return_id
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	private Integer agree_return_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agree_return.agree_return_name
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	private String agree_return_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agree_return.register_date
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	private Date register_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agree_return.comment
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	private String comment;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agree_return.agree_copy
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	private byte[] agree_copy;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agree_return.agree_return_id
	 * @return  the value of edu.agree_return.agree_return_id
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public Integer getAgree_return_id() {
		return agree_return_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agree_return.agree_return_id
	 * @param agree_return_id  the value for edu.agree_return.agree_return_id
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setAgree_return_id(Integer agree_return_id) {
		this.agree_return_id = agree_return_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agree_return.agree_return_name
	 * @return  the value of edu.agree_return.agree_return_name
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public String getAgree_return_name() {
		return agree_return_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agree_return.agree_return_name
	 * @param agree_return_name  the value for edu.agree_return.agree_return_name
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setAgree_return_name(String agree_return_name) {
		this.agree_return_name = agree_return_name == null ? null
				: agree_return_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agree_return.register_date
	 * @return  the value of edu.agree_return.register_date
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public Date getRegister_date() {
		return register_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agree_return.register_date
	 * @param register_date  the value for edu.agree_return.register_date
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agree_return.comment
	 * @return  the value of edu.agree_return.comment
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agree_return.comment
	 * @param comment  the value for edu.agree_return.comment
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agree_return.agree_copy
	 * @return  the value of edu.agree_return.agree_copy
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public byte[] getAgree_copy() {
		return agree_copy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agree_return.agree_copy
	 * @param agree_copy  the value for edu.agree_return.agree_copy
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setAgree_copy(byte[] agree_copy) {
		this.agree_copy = agree_copy;
	}
}