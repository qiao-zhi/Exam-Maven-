package cn.xm.exam.bean.exam;

import java.util.ArrayList;
import java.util.List;

public class OnlineexamanswerinforExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OnlineexamanswerinforExample() {
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

        public Criteria andAnswerinfoidIsNull() {
            addCriterion("answerInfoId is null");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidIsNotNull() {
            addCriterion("answerInfoId is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidEqualTo(String value) {
            addCriterion("answerInfoId =", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidNotEqualTo(String value) {
            addCriterion("answerInfoId <>", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidGreaterThan(String value) {
            addCriterion("answerInfoId >", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidGreaterThanOrEqualTo(String value) {
            addCriterion("answerInfoId >=", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidLessThan(String value) {
            addCriterion("answerInfoId <", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidLessThanOrEqualTo(String value) {
            addCriterion("answerInfoId <=", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidLike(String value) {
            addCriterion("answerInfoId like", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidNotLike(String value) {
            addCriterion("answerInfoId not like", value, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidIn(List<String> values) {
            addCriterion("answerInfoId in", values, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidNotIn(List<String> values) {
            addCriterion("answerInfoId not in", values, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidBetween(String value1, String value2) {
            addCriterion("answerInfoId between", value1, value2, "answerinfoid");
            return (Criteria) this;
        }

        public Criteria andAnswerinfoidNotBetween(String value1, String value2) {
            addCriterion("answerInfoId not between", value1, value2, "answerinfoid");
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

        public Criteria andQuestiontypeIsNull() {
            addCriterion("questionType is null");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeIsNotNull() {
            addCriterion("questionType is not null");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeEqualTo(String value) {
            addCriterion("questionType =", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotEqualTo(String value) {
            addCriterion("questionType <>", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeGreaterThan(String value) {
            addCriterion("questionType >", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeGreaterThanOrEqualTo(String value) {
            addCriterion("questionType >=", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLessThan(String value) {
            addCriterion("questionType <", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLessThanOrEqualTo(String value) {
            addCriterion("questionType <=", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeLike(String value) {
            addCriterion("questionType like", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotLike(String value) {
            addCriterion("questionType not like", value, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeIn(List<String> values) {
            addCriterion("questionType in", values, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotIn(List<String> values) {
            addCriterion("questionType not in", values, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeBetween(String value1, String value2) {
            addCriterion("questionType between", value1, value2, "questiontype");
            return (Criteria) this;
        }

        public Criteria andQuestiontypeNotBetween(String value1, String value2) {
            addCriterion("questionType not between", value1, value2, "questiontype");
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

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andSelectoptionIsNull() {
            addCriterion("selectOption is null");
            return (Criteria) this;
        }

        public Criteria andSelectoptionIsNotNull() {
            addCriterion("selectOption is not null");
            return (Criteria) this;
        }

        public Criteria andSelectoptionEqualTo(String value) {
            addCriterion("selectOption =", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionNotEqualTo(String value) {
            addCriterion("selectOption <>", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionGreaterThan(String value) {
            addCriterion("selectOption >", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionGreaterThanOrEqualTo(String value) {
            addCriterion("selectOption >=", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionLessThan(String value) {
            addCriterion("selectOption <", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionLessThanOrEqualTo(String value) {
            addCriterion("selectOption <=", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionLike(String value) {
            addCriterion("selectOption like", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionNotLike(String value) {
            addCriterion("selectOption not like", value, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionIn(List<String> values) {
            addCriterion("selectOption in", values, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionNotIn(List<String> values) {
            addCriterion("selectOption not in", values, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionBetween(String value1, String value2) {
            addCriterion("selectOption between", value1, value2, "selectoption");
            return (Criteria) this;
        }

        public Criteria andSelectoptionNotBetween(String value1, String value2) {
            addCriterion("selectOption not between", value1, value2, "selectoption");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreIsNull() {
            addCriterion("questionScore is null");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreIsNotNull() {
            addCriterion("questionScore is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreEqualTo(Float value) {
            addCriterion("questionScore =", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreNotEqualTo(Float value) {
            addCriterion("questionScore <>", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreGreaterThan(Float value) {
            addCriterion("questionScore >", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreGreaterThanOrEqualTo(Float value) {
            addCriterion("questionScore >=", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreLessThan(Float value) {
            addCriterion("questionScore <", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreLessThanOrEqualTo(Float value) {
            addCriterion("questionScore <=", value, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreIn(List<Float> values) {
            addCriterion("questionScore in", values, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreNotIn(List<Float> values) {
            addCriterion("questionScore not in", values, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreBetween(Float value1, Float value2) {
            addCriterion("questionScore between", value1, value2, "questionscore");
            return (Criteria) this;
        }

        public Criteria andQuestionscoreNotBetween(Float value1, Float value2) {
            addCriterion("questionScore not between", value1, value2, "questionscore");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Float value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Float value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Float value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Float value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Float value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Float> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Float> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Float value1, Float value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Float value1, Float value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidIsNull() {
            addCriterion("onlineAnswerExamid is null");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidIsNotNull() {
            addCriterion("onlineAnswerExamid is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidEqualTo(String value) {
            addCriterion("onlineAnswerExamid =", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidNotEqualTo(String value) {
            addCriterion("onlineAnswerExamid <>", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidGreaterThan(String value) {
            addCriterion("onlineAnswerExamid >", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidGreaterThanOrEqualTo(String value) {
            addCriterion("onlineAnswerExamid >=", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidLessThan(String value) {
            addCriterion("onlineAnswerExamid <", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidLessThanOrEqualTo(String value) {
            addCriterion("onlineAnswerExamid <=", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidLike(String value) {
            addCriterion("onlineAnswerExamid like", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidNotLike(String value) {
            addCriterion("onlineAnswerExamid not like", value, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidIn(List<String> values) {
            addCriterion("onlineAnswerExamid in", values, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidNotIn(List<String> values) {
            addCriterion("onlineAnswerExamid not in", values, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidBetween(String value1, String value2) {
            addCriterion("onlineAnswerExamid between", value1, value2, "onlineanswerexamid");
            return (Criteria) this;
        }

        public Criteria andOnlineanswerexamidNotBetween(String value1, String value2) {
            addCriterion("onlineAnswerExamid not between", value1, value2, "onlineanswerexamid");
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