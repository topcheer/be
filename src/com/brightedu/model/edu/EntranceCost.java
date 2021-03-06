package com.brightedu.model.edu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EntranceCost extends EntranceCostKey  implements Serializable{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.entrance_cost.fee
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	private BigDecimal fee;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column edu.entrance_cost.register_date
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	private Date register_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.entrance_cost
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.entrance_cost.fee
	 * @return  the value of edu.entrance_cost.fee
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.entrance_cost.fee
	 * @param fee  the value for edu.entrance_cost.fee
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column edu.entrance_cost.register_date
	 * @return  the value of edu.entrance_cost.register_date
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	public Date getRegister_date() {
		return register_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column edu.entrance_cost.register_date
	 * @param register_date  the value for edu.entrance_cost.register_date
	 * @mbggenerated  Tue Feb 14 22:38:13 CST 2012
	 */
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
}