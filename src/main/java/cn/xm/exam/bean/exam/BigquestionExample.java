package cn.xm.exam.bean.exam;

import java.util.ArrayList;
import java.util.List;

public class BigquestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BigquestionExample() {
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

        public Criteria andBigquertionidIsNull() {
            addCriterion("bigQuertionId is null");
            return (Criteria) this;
        }

        public Criteria andBigquertionidIsNotNull() {
            addCriterion("bigQuertionId is not null");
            return (Criteria) this;
        }

        public Criteria andBigquertionidEqualTo(String value) {
            addCriterion("bigQuertionId =", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidNotEqualTo(String value) {
            addCriterion("bigQuertionId <>", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidGreaterThan(String value) {
            addCriterion("bigQuertionId >", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidGreaterThanOrEqualTo(String value) {
            addCriterion("bigQuertionId >=", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidLessThan(String value) {
            addCriterion("bigQuertionId <", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidLessThanOrEqualTo(String value) {
            addCriterion("bigQuertionId <=", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidLike(String value) {
            addCriterion("bigQuertionId like", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidNotLike(String value) {
            addCriterion("bigQuertionId not like", value, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidIn(List<String> values) {
            addCriterion("bigQuertionId in", values, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidNotIn(List<String> values) {
            addCriterion("bigQuertionId not in", values, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidBetween(String value1, String value2) {
            addCriterion("bigQuertionId between", value1, value2, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andBigquertionidNotBetween(String value1, String value2) {
            addCriterion("bigQuertionId not between", value1, value2, "bigquertionid");
            return (Criteria) this;
        }

        public Criteria andPaperidIsNull() {
            addCriterion("paperId is null");
            return (Criteria) this;
        }

        public Criteria andPaperidIsNotNull() {
            addCriterion("paperId is not null");
            return (Criteria) this;
        }

        public Criteria andPaperidEqualTo(String value) {
            addCriterion("paperId =", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidNotEqualTo(String value) {
            addCriterion("paperId <>", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidGreaterThan(String value) {
            addCriterion("paperId >", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidGreaterThanOrEqualTo(String value) {
            addCriterion("paperId >=", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidLessThan(String value) {
            addCriterion("paperId <", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidLessThanOrEqualTo(String value) {
            addCriterion("paperId <=", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidLike(String value) {
            addCriterion("paperId like", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidNotLike(String value) {
            addCriterion("paperId not like", value, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidIn(List<String> values) {
            addCriterion("paperId in", values, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidNotIn(List<String> values) {
            addCriterion("paperId not in", values, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidBetween(String value1, String value2) {
            addCriterion("paperId between", value1, value2, "paperid");
            return (Criteria) this;
        }

        public Criteria andPaperidNotBetween(String value1, String value2) {
            addCriterion("paperId not between", value1, value2, "paperid");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameIsNull() {
            addCriterion("bigQuestionName is null");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameIsNotNull() {
            addCriterion("bigQuestionName is not null");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameEqualTo(String value) {
            addCriterion("bigQuestionName =", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameNotEqualTo(String value) {
            addCriterion("bigQuestionName <>", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameGreaterThan(String value) {
            addCriterion("bigQuestionName >", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameGreaterThanOrEqualTo(String value) {
            addCriterion("bigQuestionName >=", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameLessThan(String value) {
            addCriterion("bigQuestionName <", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameLessThanOrEqualTo(String value) {
            addCriterion("bigQuestionName <=", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameLike(String value) {
            addCriterion("bigQuestionName like", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameNotLike(String value) {
            addCriterion("bigQuestionName not like", value, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameIn(List<String> values) {
            addCriterion("bigQuestionName in", values, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameNotIn(List<String> values) {
            addCriterion("bigQuestionName not in", values, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameBetween(String value1, String value2) {
            addCriterion("bigQuestionName between", value1, value2, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionnameNotBetween(String value1, String value2) {
            addCriterion("bigQuestionName not between", value1, value2, "bigquestionname");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceIsNull() {
            addCriterion("bigQuestionSequence is null");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceIsNotNull() {
            addCriterion("bigQuestionSequence is not null");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceEqualTo(Integer value) {
            addCriterion("bigQuestionSequence =", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceNotEqualTo(Integer value) {
            addCriterion("bigQuestionSequence <>", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceGreaterThan(Integer value) {
            addCriterion("bigQuestionSequence >", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("bigQuestionSequence >=", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceLessThan(Integer value) {
            addCriterion("bigQuestionSequence <", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceLessThanOrEqualTo(Integer value) {
            addCriterion("bigQuestionSequence <=", value, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceIn(List<Integer> values) {
            addCriterion("bigQuestionSequence in", values, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceNotIn(List<Integer> values) {
            addCriterion("bigQuestionSequence not in", values, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceBetween(Integer value1, Integer value2) {
            addCriterion("bigQuestionSequence between", value1, value2, "bigquestionsequence");
            return (Criteria) this;
        }

        public Criteria andBigquestionsequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("bigQuestionSequence not between", value1, value2, "bigquestionsequence");
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