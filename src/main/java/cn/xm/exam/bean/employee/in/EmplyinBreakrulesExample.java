package cn.xm.exam.bean.employee.in;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmplyinBreakrulesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmplyinBreakrulesExample() {
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

        public Criteria andEmpinbreakidIsNull() {
            addCriterion("empInBreakId is null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidIsNotNull() {
            addCriterion("empInBreakId is not null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidEqualTo(Integer value) {
            addCriterion("empInBreakId =", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidNotEqualTo(Integer value) {
            addCriterion("empInBreakId <>", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidGreaterThan(Integer value) {
            addCriterion("empInBreakId >", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidGreaterThanOrEqualTo(Integer value) {
            addCriterion("empInBreakId >=", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidLessThan(Integer value) {
            addCriterion("empInBreakId <", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidLessThanOrEqualTo(Integer value) {
            addCriterion("empInBreakId <=", value, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidIn(List<Integer> values) {
            addCriterion("empInBreakId in", values, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidNotIn(List<Integer> values) {
            addCriterion("empInBreakId not in", values, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidBetween(Integer value1, Integer value2) {
            addCriterion("empInBreakId between", value1, value2, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakidNotBetween(Integer value1, Integer value2) {
            addCriterion("empInBreakId not between", value1, value2, "empinbreakid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidIsNull() {
            addCriterion("empInEmployeeId is null");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidIsNotNull() {
            addCriterion("empInEmployeeId is not null");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidEqualTo(String value) {
            addCriterion("empInEmployeeId =", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidNotEqualTo(String value) {
            addCriterion("empInEmployeeId <>", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidGreaterThan(String value) {
            addCriterion("empInEmployeeId >", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidGreaterThanOrEqualTo(String value) {
            addCriterion("empInEmployeeId >=", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidLessThan(String value) {
            addCriterion("empInEmployeeId <", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidLessThanOrEqualTo(String value) {
            addCriterion("empInEmployeeId <=", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidLike(String value) {
            addCriterion("empInEmployeeId like", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidNotLike(String value) {
            addCriterion("empInEmployeeId not like", value, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidIn(List<String> values) {
            addCriterion("empInEmployeeId in", values, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidNotIn(List<String> values) {
            addCriterion("empInEmployeeId not in", values, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidBetween(String value1, String value2) {
            addCriterion("empInEmployeeId between", value1, value2, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinemployeeidNotBetween(String value1, String value2) {
            addCriterion("empInEmployeeId not between", value1, value2, "empinemployeeid");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentIsNull() {
            addCriterion("empInBreakContent is null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentIsNotNull() {
            addCriterion("empInBreakContent is not null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentEqualTo(String value) {
            addCriterion("empInBreakContent =", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentNotEqualTo(String value) {
            addCriterion("empInBreakContent <>", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentGreaterThan(String value) {
            addCriterion("empInBreakContent >", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentGreaterThanOrEqualTo(String value) {
            addCriterion("empInBreakContent >=", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentLessThan(String value) {
            addCriterion("empInBreakContent <", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentLessThanOrEqualTo(String value) {
            addCriterion("empInBreakContent <=", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentLike(String value) {
            addCriterion("empInBreakContent like", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentNotLike(String value) {
            addCriterion("empInBreakContent not like", value, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentIn(List<String> values) {
            addCriterion("empInBreakContent in", values, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentNotIn(List<String> values) {
            addCriterion("empInBreakContent not in", values, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentBetween(String value1, String value2) {
            addCriterion("empInBreakContent between", value1, value2, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreakcontentNotBetween(String value1, String value2) {
            addCriterion("empInBreakContent not between", value1, value2, "empinbreakcontent");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeIsNull() {
            addCriterion("empInBreakTime is null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeIsNotNull() {
            addCriterion("empInBreakTime is not null");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeEqualTo(Date value) {
            addCriterion("empInBreakTime =", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeNotEqualTo(Date value) {
            addCriterion("empInBreakTime <>", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeGreaterThan(Date value) {
            addCriterion("empInBreakTime >", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("empInBreakTime >=", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeLessThan(Date value) {
            addCriterion("empInBreakTime <", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeLessThanOrEqualTo(Date value) {
            addCriterion("empInBreakTime <=", value, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeIn(List<Date> values) {
            addCriterion("empInBreakTime in", values, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeNotIn(List<Date> values) {
            addCriterion("empInBreakTime not in", values, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeBetween(Date value1, Date value2) {
            addCriterion("empInBreakTime between", value1, value2, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinbreaktimeNotBetween(Date value1, Date value2) {
            addCriterion("empInBreakTime not between", value1, value2, "empinbreaktime");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumIsNull() {
            addCriterion("empInMinusNum is null");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumIsNotNull() {
            addCriterion("empInMinusNum is not null");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumEqualTo(Integer value) {
            addCriterion("empInMinusNum =", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumNotEqualTo(Integer value) {
            addCriterion("empInMinusNum <>", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumGreaterThan(Integer value) {
            addCriterion("empInMinusNum >", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("empInMinusNum >=", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumLessThan(Integer value) {
            addCriterion("empInMinusNum <", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumLessThanOrEqualTo(Integer value) {
            addCriterion("empInMinusNum <=", value, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumIn(List<Integer> values) {
            addCriterion("empInMinusNum in", values, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumNotIn(List<Integer> values) {
            addCriterion("empInMinusNum not in", values, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumBetween(Integer value1, Integer value2) {
            addCriterion("empInMinusNum between", value1, value2, "empinminusnum");
            return (Criteria) this;
        }

        public Criteria andEmpinminusnumNotBetween(Integer value1, Integer value2) {
            addCriterion("empInMinusNum not between", value1, value2, "empinminusnum");
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