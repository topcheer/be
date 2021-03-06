package com.brightedu.model.edu;

import com.brightedu.client.ds.Page;
import java.util.ArrayList;
import java.util.List;

public class RightsFunctionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public RightsFunctionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFunction_idIsNull() {
            addCriterion("function_id is null");
            return (Criteria) this;
        }

        public Criteria andFunction_idIsNotNull() {
            addCriterion("function_id is not null");
            return (Criteria) this;
        }

        public Criteria andFunction_idEqualTo(String value) {
            addCriterion("function_id =", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idNotEqualTo(String value) {
            addCriterion("function_id <>", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idGreaterThan(String value) {
            addCriterion("function_id >", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idGreaterThanOrEqualTo(String value) {
            addCriterion("function_id >=", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idLessThan(String value) {
            addCriterion("function_id <", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idLessThanOrEqualTo(String value) {
            addCriterion("function_id <=", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idLike(String value) {
            addCriterion("function_id like", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idNotLike(String value) {
            addCriterion("function_id not like", value, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idIn(List<String> values) {
            addCriterion("function_id in", values, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idNotIn(List<String> values) {
            addCriterion("function_id not in", values, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idBetween(String value1, String value2) {
            addCriterion("function_id between", value1, value2, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_idNotBetween(String value1, String value2) {
            addCriterion("function_id not between", value1, value2, "function_id");
            return (Criteria) this;
        }

        public Criteria andFunction_nameIsNull() {
            addCriterion("function_name is null");
            return (Criteria) this;
        }

        public Criteria andFunction_nameIsNotNull() {
            addCriterion("function_name is not null");
            return (Criteria) this;
        }

        public Criteria andFunction_nameEqualTo(String value) {
            addCriterion("function_name =", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameNotEqualTo(String value) {
            addCriterion("function_name <>", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameGreaterThan(String value) {
            addCriterion("function_name >", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameGreaterThanOrEqualTo(String value) {
            addCriterion("function_name >=", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameLessThan(String value) {
            addCriterion("function_name <", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameLessThanOrEqualTo(String value) {
            addCriterion("function_name <=", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameLike(String value) {
            addCriterion("function_name like", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameNotLike(String value) {
            addCriterion("function_name not like", value, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameIn(List<String> values) {
            addCriterion("function_name in", values, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameNotIn(List<String> values) {
            addCriterion("function_name not in", values, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameBetween(String value1, String value2) {
            addCriterion("function_name between", value1, value2, "function_name");
            return (Criteria) this;
        }

        public Criteria andFunction_nameNotBetween(String value1, String value2) {
            addCriterion("function_name not between", value1, value2, "function_name");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table edu.rights_function
     *
     * @mbggenerated do_not_delete_during_merge Fri Feb 17 17:28:53 CST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table edu.rights_function
     *
     * @mbggenerated Fri Feb 17 17:28:53 CST 2012
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}