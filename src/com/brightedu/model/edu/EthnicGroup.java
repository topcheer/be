package com.brightedu.model.edu;

import java.io.Serializable;

public class EthnicGroup implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.sd_ethnic_group.ethnic_group_id
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    private Integer ethnic_group_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu.sd_ethnic_group.ethnic_group_name
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    private String ethnic_group_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table edu.sd_ethnic_group
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.sd_ethnic_group.ethnic_group_id
     *
     * @return the value of edu.sd_ethnic_group.ethnic_group_id
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    public Integer getEthnic_group_id() {
        return ethnic_group_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.sd_ethnic_group.ethnic_group_id
     *
     * @param ethnic_group_id the value for edu.sd_ethnic_group.ethnic_group_id
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    public void setEthnic_group_id(Integer ethnic_group_id) {
        this.ethnic_group_id = ethnic_group_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu.sd_ethnic_group.ethnic_group_name
     *
     * @return the value of edu.sd_ethnic_group.ethnic_group_name
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    public String getEthnic_group_name() {
        return ethnic_group_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu.sd_ethnic_group.ethnic_group_name
     *
     * @param ethnic_group_name the value for edu.sd_ethnic_group.ethnic_group_name
     *
     * @mbggenerated Sun Feb 12 11:48:59 CST 2012
     */
    public void setEthnic_group_name(String ethnic_group_name) {
        this.ethnic_group_name = ethnic_group_name == null ? null : ethnic_group_name.trim();
    }
}