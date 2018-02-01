package cn.xm.exam.bean.employee.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BlacklistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BlacklistExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andEmployeeidIsNull() {
            addCriterion("employeeId is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIsNotNull() {
            addCriterion("employeeId is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidEqualTo(String value) {
            addCriterion("employeeId =", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotEqualTo(String value) {
            addCriterion("employeeId <>", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThan(String value) {
            addCriterion("employeeId >", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThanOrEqualTo(String value) {
            addCriterion("employeeId >=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThan(String value) {
            addCriterion("employeeId <", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThanOrEqualTo(String value) {
            addCriterion("employeeId <=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLike(String value) {
            addCriterion("employeeId like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotLike(String value) {
            addCriterion("employeeId not like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIn(List<String> values) {
            addCriterion("employeeId in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotIn(List<String> values) {
            addCriterion("employeeId not in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidBetween(String value1, String value2) {
            addCriterion("employeeId between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotBetween(String value1, String value2) {
            addCriterion("employeeId not between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterionForJDBCDate("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterionForJDBCDate("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterionForJDBCDate("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andBlackidcardIsNull() {
            addCriterion("blackIdcard is null");
            return (Criteria) this;
        }

        public Criteria andBlackidcardIsNotNull() {
            addCriterion("blackIdcard is not null");
            return (Criteria) this;
        }

        public Criteria andBlackidcardEqualTo(String value) {
            addCriterion("blackIdcard =", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardNotEqualTo(String value) {
            addCriterion("blackIdcard <>", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardGreaterThan(String value) {
            addCriterion("blackIdcard >", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardGreaterThanOrEqualTo(String value) {
            addCriterion("blackIdcard >=", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardLessThan(String value) {
            addCriterion("blackIdcard <", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardLessThanOrEqualTo(String value) {
            addCriterion("blackIdcard <=", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardLike(String value) {
            addCriterion("blackIdcard like", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardNotLike(String value) {
            addCriterion("blackIdcard not like", value, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardIn(List<String> values) {
            addCriterion("blackIdcard in", values, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardNotIn(List<String> values) {
            addCriterion("blackIdcard not in", values, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardBetween(String value1, String value2) {
            addCriterion("blackIdcard between", value1, value2, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andBlackidcardNotBetween(String value1, String value2) {
            addCriterion("blackIdcard not between", value1, value2, "blackidcard");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusIsNull() {
            addCriterion("temporaryInStatus is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusIsNotNull() {
            addCriterion("temporaryInStatus is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusEqualTo(String value) {
            addCriterion("temporaryInStatus =", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusNotEqualTo(String value) {
            addCriterion("temporaryInStatus <>", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusGreaterThan(String value) {
            addCriterion("temporaryInStatus >", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusGreaterThanOrEqualTo(String value) {
            addCriterion("temporaryInStatus >=", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusLessThan(String value) {
            addCriterion("temporaryInStatus <", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusLessThanOrEqualTo(String value) {
            addCriterion("temporaryInStatus <=", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusLike(String value) {
            addCriterion("temporaryInStatus like", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusNotLike(String value) {
            addCriterion("temporaryInStatus not like", value, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusIn(List<String> values) {
            addCriterion("temporaryInStatus in", values, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusNotIn(List<String> values) {
            addCriterion("temporaryInStatus not in", values, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusBetween(String value1, String value2) {
            addCriterion("temporaryInStatus between", value1, value2, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andTemporaryinstatusNotBetween(String value1, String value2) {
            addCriterion("temporaryInStatus not between", value1, value2, "temporaryinstatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusIsNull() {
            addCriterion("employeeStatus is null");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusIsNotNull() {
            addCriterion("employeeStatus is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusEqualTo(String value) {
            addCriterion("employeeStatus =", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusNotEqualTo(String value) {
            addCriterion("employeeStatus <>", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusGreaterThan(String value) {
            addCriterion("employeeStatus >", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusGreaterThanOrEqualTo(String value) {
            addCriterion("employeeStatus >=", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusLessThan(String value) {
            addCriterion("employeeStatus <", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusLessThanOrEqualTo(String value) {
            addCriterion("employeeStatus <=", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusLike(String value) {
            addCriterion("employeeStatus like", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusNotLike(String value) {
            addCriterion("employeeStatus not like", value, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusIn(List<String> values) {
            addCriterion("employeeStatus in", values, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusNotIn(List<String> values) {
            addCriterion("employeeStatus not in", values, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusBetween(String value1, String value2) {
            addCriterion("employeeStatus between", value1, value2, "employeestatus");
            return (Criteria) this;
        }

        public Criteria andEmployeestatusNotBetween(String value1, String value2) {
            addCriterion("employeeStatus not between", value1, value2, "employeestatus");
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