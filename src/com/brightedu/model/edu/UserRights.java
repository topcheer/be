package com.brightedu.model.edu;

import java.io.Serializable;

public class UserRights implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.category_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private String category_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.function_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private String function_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.category_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private String category_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.function_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private String function_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.isset
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private String isset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.userrights_view.user_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private Integer user_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.userrights_view
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.category_id
	 * @return  the value of edu.userrights_view.category_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public String getCategory_id() {
		return category_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.category_id
	 * @param category_id  the value for edu.userrights_view.category_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setCategory_id(String category_id) {
		this.category_id = category_id == null ? null : category_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.function_id
	 * @return  the value of edu.userrights_view.function_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public String getFunction_id() {
		return function_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.function_id
	 * @param function_id  the value for edu.userrights_view.function_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setFunction_id(String function_id) {
		this.function_id = function_id == null ? null : function_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.category_name
	 * @return  the value of edu.userrights_view.category_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public String getCategory_name() {
		return category_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.category_name
	 * @param category_name  the value for edu.userrights_view.category_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name == null ? null : category_name
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.function_name
	 * @return  the value of edu.userrights_view.function_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public String getFunction_name() {
		return function_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.function_name
	 * @param function_name  the value for edu.userrights_view.function_name
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setFunction_name(String function_name) {
		this.function_name = function_name == null ? null : function_name
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.isset
	 * @return  the value of edu.userrights_view.isset
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public String getIsset() {
		return isset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.isset
	 * @param isset  the value for edu.userrights_view.isset
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setIsset(String isset) {
		this.isset = isset == null ? null : isset.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.userrights_view.user_id
	 * @return  the value of edu.userrights_view.user_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.userrights_view.user_id
	 * @param user_id  the value for edu.userrights_view.user_id
	 * @mbggenerated  Tue Feb 21 12:16:24 CST 2012
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}