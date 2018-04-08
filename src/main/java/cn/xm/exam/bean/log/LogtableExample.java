package cn.xm.exam.bean.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogtableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogtableExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOperateorIsNull() {
            addCriterion("operateor is null");
            return (Criteria) this;
        }

        public Criteria andOperateorIsNotNull() {
            addCriterion("operateor is not null");
            return (Criteria) this;
        }

        public Criteria andOperateorEqualTo(String value) {
            addCriterion("operateor =", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorNotEqualTo(String value) {
            addCriterion("operateor <>", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorGreaterThan(String value) {
            addCriterion("operateor >", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorGreaterThanOrEqualTo(String value) {
            addCriterion("operateor >=", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorLessThan(String value) {
            addCriterion("operateor <", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorLessThanOrEqualTo(String value) {
            addCriterion("operateor <=", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorLike(String value) {
            addCriterion("operateor like", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorNotLike(String value) {
            addCriterion("operateor not like", value, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorIn(List<String> values) {
            addCriterion("operateor in", values, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorNotIn(List<String> values) {
            addCriterion("operateor not in", values, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorBetween(String value1, String value2) {
            addCriterion("operateor between", value1, value2, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperateorNotBetween(String value1, String value2) {
            addCriterion("operateor not between", value1, value2, "operateor");
            return (Criteria) this;
        }

        public Criteria andOperatetypeIsNull() {
            addCriterion("operateType is null");
            return (Criteria) this;
        }

        public Criteria andOperatetypeIsNotNull() {
            addCriterion("operateType is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetypeEqualTo(String value) {
            addCriterion("operateType =", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotEqualTo(String value) {
            addCriterion("operateType <>", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeGreaterThan(String value) {
            addCriterion("operateType >", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeGreaterThanOrEqualTo(String value) {
            addCriterion("operateType >=", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeLessThan(String value) {
            addCriterion("operateType <", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeLessThanOrEqualTo(String value) {
            addCriterion("operateType <=", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeLike(String value) {
            addCriterion("operateType like", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotLike(String value) {
            addCriterion("operateType not like", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeIn(List<String> values) {
            addCriterion("operateType in", values, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotIn(List<String> values) {
            addCriterion("operateType not in", values, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeBetween(String value1, String value2) {
            addCriterion("operateType between", value1, value2, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotBetween(String value1, String value2) {
            addCriterion("operateType not between", value1, value2, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatedateIsNull() {
            addCriterion("operateDate is null");
            return (Criteria) this;
        }

        public Criteria andOperatedateIsNotNull() {
            addCriterion("operateDate is not null");
            return (Criteria) this;
        }

        public Criteria andOperatedateEqualTo(Date value) {
            addCriterion("operateDate =", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateNotEqualTo(Date value) {
            addCriterion("operateDate <>", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateGreaterThan(Date value) {
            addCriterion("operateDate >", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("operateDate >=", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateLessThan(Date value) {
            addCriterion("operateDate <", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateLessThanOrEqualTo(Date value) {
            addCriterion("operateDate <=", value, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateIn(List<Date> values) {
            addCriterion("operateDate in", values, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateNotIn(List<Date> values) {
            addCriterion("operateDate not in", values, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateBetween(Date value1, Date value2) {
            addCriterion("operateDate between", value1, value2, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperatedateNotBetween(Date value1, Date value2) {
            addCriterion("operateDate not between", value1, value2, "operatedate");
            return (Criteria) this;
        }

        public Criteria andOperateresultIsNull() {
            addCriterion("operateResult is null");
            return (Criteria) this;
        }

        public Criteria andOperateresultIsNotNull() {
            addCriterion("operateResult is not null");
            return (Criteria) this;
        }

        public Criteria andOperateresultEqualTo(String value) {
            addCriterion("operateResult =", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultNotEqualTo(String value) {
            addCriterion("operateResult <>", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultGreaterThan(String value) {
            addCriterion("operateResult >", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultGreaterThanOrEqualTo(String value) {
            addCriterion("operateResult >=", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultLessThan(String value) {
            addCriterion("operateResult <", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultLessThanOrEqualTo(String value) {
            addCriterion("operateResult <=", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultLike(String value) {
            addCriterion("operateResult like", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultNotLike(String value) {
            addCriterion("operateResult not like", value, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultIn(List<String> values) {
            addCriterion("operateResult in", values, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultNotIn(List<String> values) {
            addCriterion("operateResult not in", values, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultBetween(String value1, String value2) {
            addCriterion("operateResult between", value1, value2, "operateresult");
            return (Criteria) this;
        }

        public Criteria andOperateresultNotBetween(String value1, String value2) {
            addCriterion("operateResult not between", value1, value2, "operateresult");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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