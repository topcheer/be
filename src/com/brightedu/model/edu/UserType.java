package com.brightedu.model.edu;

import java.io.Serializable;

public class UserType implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.user_type.user_type_id
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	private Integer user_type_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.user_type.user_type_name
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	private String user_type_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.user_type
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.user_type.user_type_id
	 * @return  the value of edu.user_type.user_type_id
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public Integer getUser_type_id() {
		return user_type_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.user_type.user_type_id
	 * @param user_type_id  the value for edu.user_type.user_type_id
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public void setUser_type_id(Integer user_type_id) {
		this.user_type_id = user_type_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.user_type.user_type_name
	 * @return  the value of edu.user_type.user_type_name
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public String getUser_type_name() {
		return user_type_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.user_type.user_type_name
	 * @param user_type_name  the value for edu.user_type.user_type_name
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name == null ? null : user_type_name
				.trim();
	}
}