package com.brightedu.model.edu;

import java.math.BigDecimal;
import java.util.Date;

public class DerateRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.derate_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Integer derate_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Integer student_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.derate_fee
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private BigDecimal derate_fee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.derate_content
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private String derate_content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.approve
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Boolean approve;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.approve_by
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private String approve_by;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Integer user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Date register_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.update_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private Date update_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.derate_request.attachment
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    private byte[] attachment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.derate_id
     *
     * @return the value of edu.derate_request.derate_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Integer getDerate_id() {
        return derate_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.derate_id
     *
     * @param derate_id the value for edu.derate_request.derate_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setDerate_id(Integer derate_id) {
        this.derate_id = derate_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.student_id
     *
     * @return the value of edu.derate_request.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Integer getStudent_id() {
        return student_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.student_id
     *
     * @param student_id the value for edu.derate_request.student_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.derate_fee
     *
     * @return the value of edu.derate_request.derate_fee
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public BigDecimal getDerate_fee() {
        return derate_fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.derate_fee
     *
     * @param derate_fee the value for edu.derate_request.derate_fee
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setDerate_fee(BigDecimal derate_fee) {
        this.derate_fee = derate_fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.derate_content
     *
     * @return the value of edu.derate_request.derate_content
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public String getDerate_content() {
        return derate_content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.derate_content
     *
     * @param derate_content the value for edu.derate_request.derate_content
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setDerate_content(String derate_content) {
        this.derate_content = derate_content == null ? null : derate_content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.approve
     *
     * @return the value of edu.derate_request.approve
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Boolean getApprove() {
        return approve;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.approve
     *
     * @param approve the value for edu.derate_request.approve
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.approve_by
     *
     * @return the value of edu.derate_request.approve_by
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public String getApprove_by() {
        return approve_by;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.approve_by
     *
     * @param approve_by the value for edu.derate_request.approve_by
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setApprove_by(String approve_by) {
        this.approve_by = approve_by == null ? null : approve_by.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.user_id
     *
     * @return the value of edu.derate_request.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.user_id
     *
     * @param user_id the value for edu.derate_request.user_id
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.register_date
     *
     * @return the value of edu.derate_request.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Date getRegister_date() {
        return register_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.register_date
     *
     * @param register_date the value for edu.derate_request.register_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.update_date
     *
     * @return the value of edu.derate_request.update_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public Date getUpdate_date() {
        return update_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.update_date
     *
     * @param update_date the value for edu.derate_request.update_date
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.derate_request.attachment
     *
     * @return the value of edu.derate_request.attachment
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public byte[] getAttachment() {
        return attachment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.derate_request.attachment
     *
     * @param attachment the value for edu.derate_request.attachment
     *
     * @mbggenerated Sun Jan 15 18:52:28 CST 2012
     */
    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}