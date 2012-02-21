package com.brightedu.model.edu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.brightedu.client.ds.Page;

public class RefundmentAdminExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public RefundmentAdminExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andRefund_idIsNull() {
			addCriterion("refund_id is null");
			return (Criteria) this;
		}

		public Criteria andRefund_idIsNotNull() {
			addCriterion("refund_id is not null");
			return (Criteria) this;
		}

		public Criteria andRefund_idEqualTo(Integer value) {
			addCriterion("refund_id =", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idNotEqualTo(Integer value) {
			addCriterion("refund_id <>", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idGreaterThan(Integer value) {
			addCriterion("refund_id >", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idGreaterThanOrEqualTo(Integer value) {
			addCriterion("refund_id >=", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idLessThan(Integer value) {
			addCriterion("refund_id <", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idLessThanOrEqualTo(Integer value) {
			addCriterion("refund_id <=", value, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idIn(List<Integer> values) {
			addCriterion("refund_id in", values, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idNotIn(List<Integer> values) {
			addCriterion("refund_id not in", values, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idBetween(Integer value1, Integer value2) {
			addCriterion("refund_id between", value1, value2, "refund_id");
			return (Criteria) this;
		}

		public Criteria andRefund_idNotBetween(Integer value1, Integer value2) {
			addCriterion("refund_id not between", value1, value2, "refund_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idIsNull() {
			addCriterion("student_id is null");
			return (Criteria) this;
		}

		public Criteria andStudent_idIsNotNull() {
			addCriterion("student_id is not null");
			return (Criteria) this;
		}

		public Criteria andStudent_idEqualTo(Integer value) {
			addCriterion("student_id =", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idNotEqualTo(Integer value) {
			addCriterion("student_id <>", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idGreaterThan(Integer value) {
			addCriterion("student_id >", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idGreaterThanOrEqualTo(Integer value) {
			addCriterion("student_id >=", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idLessThan(Integer value) {
			addCriterion("student_id <", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idLessThanOrEqualTo(Integer value) {
			addCriterion("student_id <=", value, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idIn(List<Integer> values) {
			addCriterion("student_id in", values, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idNotIn(List<Integer> values) {
			addCriterion("student_id not in", values, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idBetween(Integer value1, Integer value2) {
			addCriterion("student_id between", value1, value2, "student_id");
			return (Criteria) this;
		}

		public Criteria andStudent_idNotBetween(Integer value1, Integer value2) {
			addCriterion("student_id not between", value1, value2, "student_id");
			return (Criteria) this;
		}

		public Criteria andRefund_feeIsNull() {
			addCriterion("refund_fee is null");
			return (Criteria) this;
		}

		public Criteria andRefund_feeIsNotNull() {
			addCriterion("refund_fee is not null");
			return (Criteria) this;
		}

		public Criteria andRefund_feeEqualTo(BigDecimal value) {
			addCriterion("refund_fee =", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeNotEqualTo(BigDecimal value) {
			addCriterion("refund_fee <>", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeGreaterThan(BigDecimal value) {
			addCriterion("refund_fee >", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("refund_fee >=", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeLessThan(BigDecimal value) {
			addCriterion("refund_fee <", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("refund_fee <=", value, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeIn(List<BigDecimal> values) {
			addCriterion("refund_fee in", values, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeNotIn(List<BigDecimal> values) {
			addCriterion("refund_fee not in", values, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("refund_fee between", value1, value2, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_feeNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("refund_fee not between", value1, value2, "refund_fee");
			return (Criteria) this;
		}

		public Criteria andRefund_descIsNull() {
			addCriterion("refund_desc is null");
			return (Criteria) this;
		}

		public Criteria andRefund_descIsNotNull() {
			addCriterion("refund_desc is not null");
			return (Criteria) this;
		}

		public Criteria andRefund_descEqualTo(String value) {
			addCriterion("refund_desc =", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descNotEqualTo(String value) {
			addCriterion("refund_desc <>", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descGreaterThan(String value) {
			addCriterion("refund_desc >", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descGreaterThanOrEqualTo(String value) {
			addCriterion("refund_desc >=", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descLessThan(String value) {
			addCriterion("refund_desc <", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descLessThanOrEqualTo(String value) {
			addCriterion("refund_desc <=", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descLike(String value) {
			addCriterion("refund_desc like", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descNotLike(String value) {
			addCriterion("refund_desc not like", value, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descIn(List<String> values) {
			addCriterion("refund_desc in", values, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descNotIn(List<String> values) {
			addCriterion("refund_desc not in", values, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descBetween(String value1, String value2) {
			addCriterion("refund_desc between", value1, value2, "refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_descNotBetween(String value1, String value2) {
			addCriterion("refund_desc not between", value1, value2,
					"refund_desc");
			return (Criteria) this;
		}

		public Criteria andRefund_flagIsNull() {
			addCriterion("refund_flag is null");
			return (Criteria) this;
		}

		public Criteria andRefund_flagIsNotNull() {
			addCriterion("refund_flag is not null");
			return (Criteria) this;
		}

		public Criteria andRefund_flagEqualTo(Boolean value) {
			addCriterion("refund_flag =", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagNotEqualTo(Boolean value) {
			addCriterion("refund_flag <>", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagGreaterThan(Boolean value) {
			addCriterion("refund_flag >", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagGreaterThanOrEqualTo(Boolean value) {
			addCriterion("refund_flag >=", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagLessThan(Boolean value) {
			addCriterion("refund_flag <", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagLessThanOrEqualTo(Boolean value) {
			addCriterion("refund_flag <=", value, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagIn(List<Boolean> values) {
			addCriterion("refund_flag in", values, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagNotIn(List<Boolean> values) {
			addCriterion("refund_flag not in", values, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagBetween(Boolean value1, Boolean value2) {
			addCriterion("refund_flag between", value1, value2, "refund_flag");
			return (Criteria) this;
		}

		public Criteria andRefund_flagNotBetween(Boolean value1, Boolean value2) {
			addCriterion("refund_flag not between", value1, value2,
					"refund_flag");
			return (Criteria) this;
		}

		public Criteria andUser_idIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUser_idIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUser_idEqualTo(Integer value) {
			addCriterion("user_id =", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idGreaterThan(Integer value) {
			addCriterion("user_id >", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idLessThan(Integer value) {
			addCriterion("user_id <", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idIn(List<Integer> values) {
			addCriterion("user_id in", values, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "user_id");
			return (Criteria) this;
		}

		public Criteria andRegister_dateIsNull() {
			addCriterion("register_date is null");
			return (Criteria) this;
		}

		public Criteria andRegister_dateIsNotNull() {
			addCriterion("register_date is not null");
			return (Criteria) this;
		}

		public Criteria andRegister_dateEqualTo(Date value) {
			addCriterion("register_date =", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateNotEqualTo(Date value) {
			addCriterion("register_date <>", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateGreaterThan(Date value) {
			addCriterion("register_date >", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateGreaterThanOrEqualTo(Date value) {
			addCriterion("register_date >=", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateLessThan(Date value) {
			addCriterion("register_date <", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateLessThanOrEqualTo(Date value) {
			addCriterion("register_date <=", value, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateIn(List<Date> values) {
			addCriterion("register_date in", values, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateNotIn(List<Date> values) {
			addCriterion("register_date not in", values, "register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateBetween(Date value1, Date value2) {
			addCriterion("register_date between", value1, value2,
					"register_date");
			return (Criteria) this;
		}

		public Criteria andRegister_dateNotBetween(Date value1, Date value2) {
			addCriterion("register_date not between", value1, value2,
					"register_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateIsNull() {
			addCriterion("update_date is null");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateIsNotNull() {
			addCriterion("update_date is not null");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateEqualTo(Date value) {
			addCriterion("update_date =", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateNotEqualTo(Date value) {
			addCriterion("update_date <>", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateGreaterThan(Date value) {
			addCriterion("update_date >", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateGreaterThanOrEqualTo(Date value) {
			addCriterion("update_date >=", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateLessThan(Date value) {
			addCriterion("update_date <", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateLessThanOrEqualTo(Date value) {
			addCriterion("update_date <=", value, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateIn(List<Date> values) {
			addCriterion("update_date in", values, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateNotIn(List<Date> values) {
			addCriterion("update_date not in", values, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateBetween(Date value1, Date value2) {
			addCriterion("update_date between", value1, value2, "update_date");
			return (Criteria) this;
		}

		public Criteria andUpdate_dateNotBetween(Date value1, Date value2) {
			addCriterion("update_date not between", value1, value2,
					"update_date");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table edu.refundment_admin
	 * @mbggenerated  Tue Feb 21 13:01:30 CST 2012
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table edu.refundment_admin
     *
     * @mbggenerated do_not_delete_during_merge Sun Jan 15 18:52:28 CST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}