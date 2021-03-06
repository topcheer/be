package com.brightedu.model.edu;

import com.brightedu.client.ds.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageRealExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	protected Page page;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public MessageRealExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
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

		public Criteria andMessage_idIsNull() {
			addCriterion("message_id is null");
			return (Criteria) this;
		}

		public Criteria andMessage_idIsNotNull() {
			addCriterion("message_id is not null");
			return (Criteria) this;
		}

		public Criteria andMessage_idEqualTo(Long value) {
			addCriterion("message_id =", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idNotEqualTo(Long value) {
			addCriterion("message_id <>", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idGreaterThan(Long value) {
			addCriterion("message_id >", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idGreaterThanOrEqualTo(Long value) {
			addCriterion("message_id >=", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idLessThan(Long value) {
			addCriterion("message_id <", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idLessThanOrEqualTo(Long value) {
			addCriterion("message_id <=", value, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idIn(List<Long> values) {
			addCriterion("message_id in", values, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idNotIn(List<Long> values) {
			addCriterion("message_id not in", values, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idBetween(Long value1, Long value2) {
			addCriterion("message_id between", value1, value2, "message_id");
			return (Criteria) this;
		}

		public Criteria andMessage_idNotBetween(Long value1, Long value2) {
			addCriterion("message_id not between", value1, value2, "message_id");
			return (Criteria) this;
		}

		public Criteria andFrom_userIsNull() {
			addCriterion("from_user is null");
			return (Criteria) this;
		}

		public Criteria andFrom_userIsNotNull() {
			addCriterion("from_user is not null");
			return (Criteria) this;
		}

		public Criteria andFrom_userEqualTo(Integer value) {
			addCriterion("from_user =", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userNotEqualTo(Integer value) {
			addCriterion("from_user <>", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userGreaterThan(Integer value) {
			addCriterion("from_user >", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userGreaterThanOrEqualTo(Integer value) {
			addCriterion("from_user >=", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userLessThan(Integer value) {
			addCriterion("from_user <", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userLessThanOrEqualTo(Integer value) {
			addCriterion("from_user <=", value, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userIn(List<Integer> values) {
			addCriterion("from_user in", values, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userNotIn(List<Integer> values) {
			addCriterion("from_user not in", values, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userBetween(Integer value1, Integer value2) {
			addCriterion("from_user between", value1, value2, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_userNotBetween(Integer value1, Integer value2) {
			addCriterion("from_user not between", value1, value2, "from_user");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameIsNull() {
			addCriterion("from_user_name is null");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameIsNotNull() {
			addCriterion("from_user_name is not null");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameEqualTo(String value) {
			addCriterion("from_user_name =", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameNotEqualTo(String value) {
			addCriterion("from_user_name <>", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameGreaterThan(String value) {
			addCriterion("from_user_name >", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameGreaterThanOrEqualTo(String value) {
			addCriterion("from_user_name >=", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameLessThan(String value) {
			addCriterion("from_user_name <", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameLessThanOrEqualTo(String value) {
			addCriterion("from_user_name <=", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameLike(String value) {
			addCriterion("from_user_name like", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameNotLike(String value) {
			addCriterion("from_user_name not like", value, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameIn(List<String> values) {
			addCriterion("from_user_name in", values, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameNotIn(List<String> values) {
			addCriterion("from_user_name not in", values, "from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameBetween(String value1, String value2) {
			addCriterion("from_user_name between", value1, value2,
					"from_user_name");
			return (Criteria) this;
		}

		public Criteria andFrom_user_nameNotBetween(String value1, String value2) {
			addCriterion("from_user_name not between", value1, value2,
					"from_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_userIsNull() {
			addCriterion("to_user is null");
			return (Criteria) this;
		}

		public Criteria andTo_userIsNotNull() {
			addCriterion("to_user is not null");
			return (Criteria) this;
		}

		public Criteria andTo_userEqualTo(Integer value) {
			addCriterion("to_user =", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userNotEqualTo(Integer value) {
			addCriterion("to_user <>", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userGreaterThan(Integer value) {
			addCriterion("to_user >", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userGreaterThanOrEqualTo(Integer value) {
			addCriterion("to_user >=", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userLessThan(Integer value) {
			addCriterion("to_user <", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userLessThanOrEqualTo(Integer value) {
			addCriterion("to_user <=", value, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userIn(List<Integer> values) {
			addCriterion("to_user in", values, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userNotIn(List<Integer> values) {
			addCriterion("to_user not in", values, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userBetween(Integer value1, Integer value2) {
			addCriterion("to_user between", value1, value2, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_userNotBetween(Integer value1, Integer value2) {
			addCriterion("to_user not between", value1, value2, "to_user");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameIsNull() {
			addCriterion("to_user_name is null");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameIsNotNull() {
			addCriterion("to_user_name is not null");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameEqualTo(String value) {
			addCriterion("to_user_name =", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameNotEqualTo(String value) {
			addCriterion("to_user_name <>", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameGreaterThan(String value) {
			addCriterion("to_user_name >", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameGreaterThanOrEqualTo(String value) {
			addCriterion("to_user_name >=", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameLessThan(String value) {
			addCriterion("to_user_name <", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameLessThanOrEqualTo(String value) {
			addCriterion("to_user_name <=", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameLike(String value) {
			addCriterion("to_user_name like", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameNotLike(String value) {
			addCriterion("to_user_name not like", value, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameIn(List<String> values) {
			addCriterion("to_user_name in", values, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameNotIn(List<String> values) {
			addCriterion("to_user_name not in", values, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameBetween(String value1, String value2) {
			addCriterion("to_user_name between", value1, value2, "to_user_name");
			return (Criteria) this;
		}

		public Criteria andTo_user_nameNotBetween(String value1, String value2) {
			addCriterion("to_user_name not between", value1, value2,
					"to_user_name");
			return (Criteria) this;
		}

		public Criteria andMessageIsNull() {
			addCriterion("message is null");
			return (Criteria) this;
		}

		public Criteria andMessageIsNotNull() {
			addCriterion("message is not null");
			return (Criteria) this;
		}

		public Criteria andMessageEqualTo(String value) {
			addCriterion("message =", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotEqualTo(String value) {
			addCriterion("message <>", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThan(String value) {
			addCriterion("message >", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThanOrEqualTo(String value) {
			addCriterion("message >=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThan(String value) {
			addCriterion("message <", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThanOrEqualTo(String value) {
			addCriterion("message <=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLike(String value) {
			addCriterion("message like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotLike(String value) {
			addCriterion("message not like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageIn(List<String> values) {
			addCriterion("message in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotIn(List<String> values) {
			addCriterion("message not in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageBetween(String value1, String value2) {
			addCriterion("message between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotBetween(String value1, String value2) {
			addCriterion("message not between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpIsNull() {
			addCriterion("receive_tstp is null");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpIsNotNull() {
			addCriterion("receive_tstp is not null");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpEqualTo(Date value) {
			addCriterion("receive_tstp =", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpNotEqualTo(Date value) {
			addCriterion("receive_tstp <>", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpGreaterThan(Date value) {
			addCriterion("receive_tstp >", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpGreaterThanOrEqualTo(Date value) {
			addCriterion("receive_tstp >=", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpLessThan(Date value) {
			addCriterion("receive_tstp <", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpLessThanOrEqualTo(Date value) {
			addCriterion("receive_tstp <=", value, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpIn(List<Date> values) {
			addCriterion("receive_tstp in", values, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpNotIn(List<Date> values) {
			addCriterion("receive_tstp not in", values, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpBetween(Date value1, Date value2) {
			addCriterion("receive_tstp between", value1, value2, "receive_tstp");
			return (Criteria) this;
		}

		public Criteria andReceive_tstpNotBetween(Date value1, Date value2) {
			addCriterion("receive_tstp not between", value1, value2,
					"receive_tstp");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table edu.messages_real
	 * @mbggenerated  Tue Feb 28 16:17:24 CST 2012
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
     * This class corresponds to the database table edu.messages_real
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 28 14:45:50 CST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}