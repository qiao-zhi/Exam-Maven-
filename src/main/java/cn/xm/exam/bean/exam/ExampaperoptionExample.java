package cn.xm.exam.bean.exam;

import java.util.ArrayList;
import java.util.List;

public class ExampaperoptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExampaperoptionExample() {
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

        public Criteria andOptionidIsNull() {
            addCriterion("optionId is null");
            return (Criteria) this;
        }

        public Criteria andOptionidIsNotNull() {
            addCriterion("optionId is not null");
            return (Criteria) this;
        }

        public Criteria andOptionidEqualTo(Integer value) {
            addCriterion("optionId =", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidNotEqualTo(Integer value) {
            addCriterion("optionId <>", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidGreaterThan(Integer value) {
            addCriterion("optionId >", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidGreaterThanOrEqualTo(Integer value) {
            addCriterion("optionId >=", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidLessThan(Integer value) {
            addCriterion("optionId <", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidLessThanOrEqualTo(Integer value) {
            addCriterion("optionId <=", value, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidIn(List<Integer> values) {
            addCriterion("optionId in", values, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidNotIn(List<Integer> values) {
            addCriterion("optionId not in", values, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidBetween(Integer value1, Integer value2) {
            addCriterion("optionId between", value1, value2, "optionid");
            return (Criteria) this;
        }

        public Criteria andOptionidNotBetween(Integer value1, Integer value2) {
            addCriterion("optionId not between", value1, value2, "optionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidIsNull() {
            addCriterion("questionId is null");
            return (Criteria) this;
        }

        public Criteria andQuestionidIsNotNull() {
            addCriterion("questionId is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionidEqualTo(String value) {
            addCriterion("questionId =", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidNotEqualTo(String value) {
            addCriterion("questionId <>", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidGreaterThan(String value) {
            addCriterion("questionId >", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidGreaterThanOrEqualTo(String value) {
            addCriterion("questionId >=", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidLessThan(String value) {
            addCriterion("questionId <", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidLessThanOrEqualTo(String value) {
            addCriterion("questionId <=", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidLike(String value) {
            addCriterion("questionId like", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidNotLike(String value) {
            addCriterion("questionId not like", value, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidIn(List<String> values) {
            addCriterion("questionId in", values, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidNotIn(List<String> values) {
            addCriterion("questionId not in", values, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidBetween(String value1, String value2) {
            addCriterion("questionId between", value1, value2, "questionid");
            return (Criteria) this;
        }

        public Criteria andQuestionidNotBetween(String value1, String value2) {
            addCriterion("questionId not between", value1, value2, "questionid");
            return (Criteria) this;
        }

        public Criteria andOptioncontentIsNull() {
            addCriterion("optionContent is null");
            return (Criteria) this;
        }

        public Criteria andOptioncontentIsNotNull() {
            addCriterion("optionContent is not null");
            return (Criteria) this;
        }

        public Criteria andOptioncontentEqualTo(String value) {
            addCriterion("optionContent =", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentNotEqualTo(String value) {
            addCriterion("optionContent <>", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentGreaterThan(String value) {
            addCriterion("optionContent >", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentGreaterThanOrEqualTo(String value) {
            addCriterion("optionContent >=", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentLessThan(String value) {
            addCriterion("optionContent <", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentLessThanOrEqualTo(String value) {
            addCriterion("optionContent <=", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentLike(String value) {
            addCriterion("optionContent like", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentNotLike(String value) {
            addCriterion("optionContent not like", value, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentIn(List<String> values) {
            addCriterion("optionContent in", values, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentNotIn(List<String> values) {
            addCriterion("optionContent not in", values, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentBetween(String value1, String value2) {
            addCriterion("optionContent between", value1, value2, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptioncontentNotBetween(String value1, String value2) {
            addCriterion("optionContent not between", value1, value2, "optioncontent");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceIsNull() {
            addCriterion("optionSequence is null");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceIsNotNull() {
            addCriterion("optionSequence is not null");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceEqualTo(String value) {
            addCriterion("optionSequence =", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceNotEqualTo(String value) {
            addCriterion("optionSequence <>", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceGreaterThan(String value) {
            addCriterion("optionSequence >", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceGreaterThanOrEqualTo(String value) {
            addCriterion("optionSequence >=", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceLessThan(String value) {
            addCriterion("optionSequence <", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceLessThanOrEqualTo(String value) {
            addCriterion("optionSequence <=", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceLike(String value) {
            addCriterion("optionSequence like", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceNotLike(String value) {
            addCriterion("optionSequence not like", value, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceIn(List<String> values) {
            addCriterion("optionSequence in", values, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceNotIn(List<String> values) {
            addCriterion("optionSequence not in", values, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceBetween(String value1, String value2) {
            addCriterion("optionSequence between", value1, value2, "optionsequence");
            return (Criteria) this;
        }

        public Criteria andOptionsequenceNotBetween(String value1, String value2) {
            addCriterion("optionSequence not between", value1, value2, "optionsequence");
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

        public Criteria andIsanswerIsNull() {
            addCriterion("isAnswer is null");
            return (Criteria) this;
        }

        public Criteria andIsanswerIsNotNull() {
            addCriterion("isAnswer is not null");
            return (Criteria) this;
        }

        public Criteria andIsanswerEqualTo(String value) {
            addCriterion("isAnswer =", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotEqualTo(String value) {
            addCriterion("isAnswer <>", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerGreaterThan(String value) {
            addCriterion("isAnswer >", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerGreaterThanOrEqualTo(String value) {
            addCriterion("isAnswer >=", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerLessThan(String value) {
            addCriterion("isAnswer <", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerLessThanOrEqualTo(String value) {
            addCriterion("isAnswer <=", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerLike(String value) {
            addCriterion("isAnswer like", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotLike(String value) {
            addCriterion("isAnswer not like", value, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerIn(List<String> values) {
            addCriterion("isAnswer in", values, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotIn(List<String> values) {
            addCriterion("isAnswer not in", values, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerBetween(String value1, String value2) {
            addCriterion("isAnswer between", value1, value2, "isanswer");
            return (Criteria) this;
        }

        public Criteria andIsanswerNotBetween(String value1, String value2) {
            addCriterion("isAnswer not between", value1, value2, "isanswer");
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