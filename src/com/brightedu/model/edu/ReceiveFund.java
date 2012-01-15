package com.brightedu.model.edu;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiveFund {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.fund_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Long fund_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Integer student_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.fund_income
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private BigDecimal fund_income;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Integer user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.bank_code
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private String bank_code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Date register_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.receive_fund.remark
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.fund_id
     *
     * @return the value of edu.receive_fund.fund_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Long getFund_id() {
        return fund_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.fund_id
     *
     * @param fund_id the value for edu.receive_fund.fund_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setFund_id(Long fund_id) {
        this.fund_id = fund_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.student_id
     *
     * @return the value of edu.receive_fund.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.student_id
     *
     * @param student_id the value for edu.receive_fund.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.fund_income
     *
     * @return the value of edu.receive_fund.fund_income
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public BigDecimal getFund_income() {
        return fund_income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.fund_income
     *
     * @param fund_income the value for edu.receive_fund.fund_income
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setFund_income(BigDecimal fund_income) {
        this.fund_income = fund_income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.user_id
     *
     * @return the value of edu.receive_fund.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.user_id
     *
     * @param user_id the value for edu.receive_fund.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.bank_code
     *
     * @return the value of edu.receive_fund.bank_code
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public String getBank_code() {
        return bank_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.bank_code
     *
     * @param bank_code the value for edu.receive_fund.bank_code
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code == null ? null : bank_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.register_date
     *
     * @return the value of edu.receive_fund.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Date getRegister_date() {
        return register_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.register_date
     *
     * @param register_date the value for edu.receive_fund.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.receive_fund.remark
     *
     * @return the value of edu.receive_fund.remark
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.receive_fund.remark
     *
     * @param remark the value for edu.receive_fund.remark
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}