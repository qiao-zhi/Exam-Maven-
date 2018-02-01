package cn.xm.exam.bean.exam;

import java.util.ArrayList;
import java.util.List;

public class ExampaperquestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExampaperquestionExample() {
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

        public Criteria andQuestioncontentIsNull() {
            addCriterion("questionContent is null");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentIsNotNull() {
            addCriterion("questionContent is not null");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentEqualTo(String value) {
            addCriterion("questionContent =", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotEqualTo(String value) {
            addCriterion("questionContent <>", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentGreaterThan(String value) {
            addCriterion("questionContent >", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentGreaterThanOrEqualTo(String value) {
            addCriterion("questionContent >=", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLessThan(String value) {
            addCriterion("questionContent <", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLessThanOrEqualTo(String value) {
            addCriterion("questionContent <=", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLike(String value) {
            addCriterion("questionContent like", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotLike(String value) {
            addCriterion("questionContent not like", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentIn(List<String> values) {
            addCriterion("questionContent in", values, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotIn(List<String> values) {
            addCriterion("questionContent not in", values, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentBetween(String value1, String value2) {
            addCriterion("questionContent between", value1, value2, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotBetween(String value1, String value2) {
            addCriterion("questionContent not between", value1, value2, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceIsNull() {
            addCriterion("questionSequence is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceIsNotNull() {
            addCriterion("questionSequence is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceEqualTo(Integer value) {
            addCriterion("questionSequence =", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceNotEqualTo(Integer value) {
            addCriterion("questionSequence <>", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceGreaterThan(Integer value) {
            addCriterion("questionSequence >", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("questionSequence >=", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceLessThan(Integer value) {
            addCriterion("questionSequence <", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceLessThanOrEqualTo(Integer value) {
            addCriterion("questionSequence <=", value, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceIn(List<Integer> values) {
            addCriterion("questionSequence in", values, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceNotIn(List<Integer> values) {
            addCriterion("questionSequence not in", values, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceBetween(Integer value1, Integer value2) {
            addCriterion("questionSequence between", value1, value2, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andQuestionsequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("questionSequence not between", value1, value2, "questionsequence");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andAnalysisIsNull() {
            addCriterion("analysis is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisIsNotNull() {
            addCriterion("analysis is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisEqualTo(String value) {
            addCriterion("analysis =", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisNotEqualTo(String value) {
            addCriterion("analysis <>", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisGreaterThan(String value) {
            addCriterion("analysis >", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisGreaterThanOrEqualTo(String value) {
            addCriterion("analysis >=", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisLessThan(String value) {
            addCriterion("analysis <", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisLessThanOrEqualTo(String value) {
            addCriterion("analysis <=", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisLike(String value) {
            addCriterion("analysis like", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisNotLike(String value) {
            addCriterion("analysis not like", value, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisIn(List<String> values) {
            addCriterion("analysis in", values, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisNotIn(List<String> values) {
            addCriterion("analysis not in", values, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisBetween(String value1, String value2) {
            addCriterion("analysis between", value1, value2, "analysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisNotBetween(String value1, String value2) {
            addCriterion("analysis not between", value1, value2, "analysis");
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

        public Criteria andQuestionsourceIsNull() {
            addCriterion("questionSource is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceIsNotNull() {
            addCriterion("questionSource is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceEqualTo(String value) {
            addCriterion("questionSource =", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceNotEqualTo(String value) {
            addCriterion("questionSource <>", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceGreaterThan(String value) {
            addCriterion("questionSource >", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceGreaterThanOrEqualTo(String value) {
            addCriterion("questionSource >=", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceLessThan(String value) {
            addCriterion("questionSource <", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceLessThanOrEqualTo(String value) {
            addCriterion("questionSource <=", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceLike(String value) {
            addCriterion("questionSource like", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceNotLike(String value) {
            addCriterion("questionSource not like", value, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceIn(List<String> values) {
            addCriterion("questionSource in", values, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceNotIn(List<String> values) {
            addCriterion("questionSource not in", values, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceBetween(String value1, String value2) {
            addCriterion("questionSource between", value1, value2, "questionsource");
            return (Criteria) this;
        }

        public Criteria andQuestionsourceNotBetween(String value1, String value2) {
            addCriterion("questionSource not between", value1, value2, "questionsource");
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