package cn.xm.exam.bean.employee.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreakrulesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BreakrulesExample() {
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

        public Criteria andBreakidIsNull() {
            addCriterion("breakId is null");
            return (Criteria) this;
        }

        public Criteria andBreakidIsNotNull() {
            addCriterion("breakId is not null");
            return (Criteria) this;
        }

        public Criteria andBreakidEqualTo(Integer value) {
            addCriterion("breakId =", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidNotEqualTo(Integer value) {
            addCriterion("breakId <>", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidGreaterThan(Integer value) {
            addCriterion("breakId >", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidGreaterThanOrEqualTo(Integer value) {
            addCriterion("breakId >=", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidLessThan(Integer value) {
            addCriterion("breakId <", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidLessThanOrEqualTo(Integer value) {
            addCriterion("breakId <=", value, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidIn(List<Integer> values) {
            addCriterion("breakId in", values, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidNotIn(List<Integer> values) {
            addCriterion("breakId not in", values, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidBetween(Integer value1, Integer value2) {
            addCriterion("breakId between", value1, value2, "breakid");
            return (Criteria) this;
        }

        public Criteria andBreakidNotBetween(Integer value1, Integer value2) {
            addCriterion("breakId not between", value1, value2, "breakid");
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

        public Criteria andBigemployeeoutidIsNull() {
            addCriterion("BigEmployeeoutId is null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIsNotNull() {
            addCriterion("BigEmployeeoutId is not null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidEqualTo(String value) {
            addCriterion("BigEmployeeoutId =", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotEqualTo(String value) {
            addCriterion("BigEmployeeoutId <>", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThan(String value) {
            addCriterion("BigEmployeeoutId >", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThanOrEqualTo(String value) {
            addCriterion("BigEmployeeoutId >=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThan(String value) {
            addCriterion("BigEmployeeoutId <", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThanOrEqualTo(String value) {
            addCriterion("BigEmployeeoutId <=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLike(String value) {
            addCriterion("BigEmployeeoutId like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotLike(String value) {
            addCriterion("BigEmployeeoutId not like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIn(List<String> values) {
            addCriterion("BigEmployeeoutId in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotIn(List<String> values) {
            addCriterion("BigEmployeeoutId not in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidBetween(String value1, String value2) {
            addCriterion("BigEmployeeoutId between", value1, value2, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotBetween(String value1, String value2) {
            addCriterion("BigEmployeeoutId not between", value1, value2, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBreakcontentIsNull() {
            addCriterion("breakContent is null");
            return (Criteria) this;
        }

        public Criteria andBreakcontentIsNotNull() {
            addCriterion("breakContent is not null");
            return (Criteria) this;
        }

        public Criteria andBreakcontentEqualTo(String value) {
            addCriterion("breakContent =", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentNotEqualTo(String value) {
            addCriterion("breakContent <>", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentGreaterThan(String value) {
            addCriterion("breakContent >", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentGreaterThanOrEqualTo(String value) {
            addCriterion("breakContent >=", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentLessThan(String value) {
            addCriterion("breakContent <", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentLessThanOrEqualTo(String value) {
            addCriterion("breakContent <=", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentLike(String value) {
            addCriterion("breakContent like", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentNotLike(String value) {
            addCriterion("breakContent not like", value, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentIn(List<String> values) {
            addCriterion("breakContent in", values, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentNotIn(List<String> values) {
            addCriterion("breakContent not in", values, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentBetween(String value1, String value2) {
            addCriterion("breakContent between", value1, value2, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreakcontentNotBetween(String value1, String value2) {
            addCriterion("breakContent not between", value1, value2, "breakcontent");
            return (Criteria) this;
        }

        public Criteria andBreaktimeIsNull() {
            addCriterion("breakTime is null");
            return (Criteria) this;
        }

        public Criteria andBreaktimeIsNotNull() {
            addCriterion("breakTime is not null");
            return (Criteria) this;
        }

        public Criteria andBreaktimeEqualTo(Date value) {
            addCriterion("breakTime =", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeNotEqualTo(Date value) {
            addCriterion("breakTime <>", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeGreaterThan(Date value) {
            addCriterion("breakTime >", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("breakTime >=", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeLessThan(Date value) {
            addCriterion("breakTime <", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeLessThanOrEqualTo(Date value) {
            addCriterion("breakTime <=", value, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeIn(List<Date> values) {
            addCriterion("breakTime in", values, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeNotIn(List<Date> values) {
            addCriterion("breakTime not in", values, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeBetween(Date value1, Date value2) {
            addCriterion("breakTime between", value1, value2, "breaktime");
            return (Criteria) this;
        }

        public Criteria andBreaktimeNotBetween(Date value1, Date value2) {
            addCriterion("breakTime not between", value1, value2, "breaktime");
            return (Criteria) this;
        }

        public Criteria andMinusnumIsNull() {
            addCriterion("minusNum is null");
            return (Criteria) this;
        }

        public Criteria andMinusnumIsNotNull() {
            addCriterion("minusNum is not null");
            return (Criteria) this;
        }

        public Criteria andMinusnumEqualTo(Integer value) {
            addCriterion("minusNum =", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumNotEqualTo(Integer value) {
            addCriterion("minusNum <>", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumGreaterThan(Integer value) {
            addCriterion("minusNum >", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("minusNum >=", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumLessThan(Integer value) {
            addCriterion("minusNum <", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumLessThanOrEqualTo(Integer value) {
            addCriterion("minusNum <=", value, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumIn(List<Integer> values) {
            addCriterion("minusNum in", values, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumNotIn(List<Integer> values) {
            addCriterion("minusNum not in", values, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumBetween(Integer value1, Integer value2) {
            addCriterion("minusNum between", value1, value2, "minusnum");
            return (Criteria) this;
        }

        public Criteria andMinusnumNotBetween(Integer value1, Integer value2) {
            addCriterion("minusNum not between", value1, value2, "minusnum");
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