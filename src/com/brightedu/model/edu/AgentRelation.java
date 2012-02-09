package com.brightedu.model.edu;

import java.io.Serializable;
import java.util.Date;

public class AgentRelation extends AgentRelationKey  implements Serializable{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.agent_relation.register_date
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	private Date register_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.agent_relation
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.agent_relation.register_date
	 * @return  the value of edu.agent_relation.register_date
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public Date getRegister_date() {
		return register_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.agent_relation.register_date
	 * @param register_date  the value for edu.agent_relation.register_date
	 * @mbggenerated  Thu Feb 09 18:41:46 CST 2012
	 */
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
}