package cn.xm.exam.bean.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionsExample() {
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

        public Criteria andQuestionbankidIsNull() {
            addCriterion("questionBankId is null");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidIsNotNull() {
            addCriterion("questionBankId is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidEqualTo(String value) {
            addCriterion("questionBankId =", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidNotEqualTo(String value) {
            addCriterion("questionBankId <>", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidGreaterThan(String value) {
            addCriterion("questionBankId >", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidGreaterThanOrEqualTo(String value) {
            addCriterion("questionBankId >=", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidLessThan(String value) {
            addCriterion("questionBankId <", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidLessThanOrEqualTo(String value) {
            addCriterion("questionBankId <=", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidLike(String value) {
            addCriterion("questionBankId like", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidNotLike(String value) {
            addCriterion("questionBankId not like", value, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidIn(List<String> values) {
            addCriterion("questionBankId in", values, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidNotIn(List<String> values) {
            addCriterion("questionBankId not in", values, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidBetween(String value1, String value2) {
            addCriterion("questionBankId between", value1, value2, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionbankidNotBetween(String value1, String value2) {
            addCriterion("questionBankId not between", value1, value2, "questionbankid");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNull() {
            addCriterion("question is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNotNull() {
            addCriterion("question is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEqualTo(String value) {
            addCriterion("question =", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotEqualTo(String value) {
            addCriterion("question <>", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThan(String value) {
            addCriterion("question >", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("question >=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThan(String value) {
            addCriterion("question <", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThanOrEqualTo(String value) {
            addCriterion("question <=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLike(String value) {
            addCriterion("question like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotLike(String value) {
            addCriterion("question not like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionIn(List<String> values) {
            addCriterion("question in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotIn(List<String> values) {
            addCriterion("question not in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionBetween(String value1, String value2) {
            addCriterion("question between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotBetween(String value1, String value2) {
            addCriterion("question not between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagIsNull() {
            addCriterion("questionWithTag is null");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagIsNotNull() {
            addCriterion("questionWithTag is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagEqualTo(String value) {
            addCriterion("questionWithTag =", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagNotEqualTo(String value) {
            addCriterion("questionWithTag <>", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagGreaterThan(String value) {
            addCriterion("questionWithTag >", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagGreaterThanOrEqualTo(String value) {
            addCriterion("questionWithTag >=", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagLessThan(String value) {
            addCriterion("questionWithTag <", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagLessThanOrEqualTo(String value) {
            addCriterion("questionWithTag <=", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagLike(String value) {
            addCriterion("questionWithTag like", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagNotLike(String value) {
            addCriterion("questionWithTag not like", value, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagIn(List<String> values) {
            addCriterion("questionWithTag in", values, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagNotIn(List<String> values) {
            addCriterion("questionWithTag not in", values, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagBetween(String value1, String value2) {
            addCriterion("questionWithTag between", value1, value2, "questionwithtag");
            return (Criteria) this;
        }

        public Criteria andQuestionwithtagNotBetween(String value1, String value2) {
            addCriterion("questionWithTag not between", value1, value2, "questionwithtag");
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

        public Criteria andAnalysiswithtagIsNull() {
            addCriterion("analysisWithTag is null");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagIsNotNull() {
            addCriterion("analysisWithTag is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagEqualTo(String value) {
            addCriterion("analysisWithTag =", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagNotEqualTo(String value) {
            addCriterion("analysisWithTag <>", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagGreaterThan(String value) {
            addCriterion("analysisWithTag >", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagGreaterThanOrEqualTo(String value) {
            addCriterion("analysisWithTag >=", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagLessThan(String value) {
            addCriterion("analysisWithTag <", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagLessThanOrEqualTo(String value) {
            addCriterion("analysisWithTag <=", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagLike(String value) {
            addCriterion("analysisWithTag like", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagNotLike(String value) {
            addCriterion("analysisWithTag not like", value, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagIn(List<String> values) {
            addCriterion("analysisWithTag in", values, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagNotIn(List<String> values) {
            addCriterion("analysisWithTag not in", values, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagBetween(String value1, String value2) {
            addCriterion("analysisWithTag between", value1, value2, "analysiswithtag");
            return (Criteria) this;
        }

        public Criteria andAnalysiswithtagNotBetween(String value1, String value2) {
            addCriterion("analysisWithTag not between", value1, value2, "analysiswithtag");
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
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

        public Criteria andEmplloyeenameIsNull() {
            addCriterion("emplloyeeName is null");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameIsNotNull() {
            addCriterion("emplloyeeName is not null");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameEqualTo(String value) {
            addCriterion("emplloyeeName =", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameNotEqualTo(String value) {
            addCriterion("emplloyeeName <>", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameGreaterThan(String value) {
            addCriterion("emplloyeeName >", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameGreaterThanOrEqualTo(String value) {
            addCriterion("emplloyeeName >=", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameLessThan(String value) {
            addCriterion("emplloyeeName <", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameLessThanOrEqualTo(String value) {
            addCriterion("emplloyeeName <=", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameLike(String value) {
            addCriterion("emplloyeeName like", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameNotLike(String value) {
            addCriterion("emplloyeeName not like", value, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameIn(List<String> values) {
            addCriterion("emplloyeeName in", values, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameNotIn(List<String> values) {
            addCriterion("emplloyeeName not in", values, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameBetween(String value1, String value2) {
            addCriterion("emplloyeeName between", value1, value2, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andEmplloyeenameNotBetween(String value1, String value2) {
            addCriterion("emplloyeeName not between", value1, value2, "emplloyeename");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNull() {
            addCriterion("uploadTime is null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNotNull() {
            addCriterion("uploadTime is not null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeEqualTo(Date value) {
            addCriterion("uploadTime =", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotEqualTo(Date value) {
            addCriterion("uploadTime <>", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThan(Date value) {
            addCriterion("uploadTime >", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadTime >=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThan(Date value) {
            addCriterion("uploadTime <", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThanOrEqualTo(Date value) {
            addCriterion("uploadTime <=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIn(List<Date> values) {
            addCriterion("uploadTime in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotIn(List<Date> values) {
            addCriterion("uploadTime not in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeBetween(Date value1, Date value2) {
            addCriterion("uploadTime between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotBetween(Date value1, Date value2) {
            addCriterion("uploadTime not between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeIsNull() {
            addCriterion("knowledgeType is null");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeIsNotNull() {
            addCriterion("knowledgeType is not null");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeEqualTo(String value) {
            addCriterion("knowledgeType =", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeNotEqualTo(String value) {
            addCriterion("knowledgeType <>", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeGreaterThan(String value) {
            addCriterion("knowledgeType >", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeGreaterThanOrEqualTo(String value) {
            addCriterion("knowledgeType >=", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeLessThan(String value) {
            addCriterion("knowledgeType <", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeLessThanOrEqualTo(String value) {
            addCriterion("knowledgeType <=", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeLike(String value) {
            addCriterion("knowledgeType like", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeNotLike(String value) {
            addCriterion("knowledgeType not like", value, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeIn(List<String> values) {
            addCriterion("knowledgeType in", values, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeNotIn(List<String> values) {
            addCriterion("knowledgeType not in", values, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeBetween(String value1, String value2) {
            addCriterion("knowledgeType between", value1, value2, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andKnowledgetypeNotBetween(String value1, String value2) {
            addCriterion("knowledgeType not between", value1, value2, "knowledgetype");
            return (Criteria) this;
        }

        public Criteria andHaspictureIsNull() {
            addCriterion("hasPicture is null");
            return (Criteria) this;
        }

        public Criteria andHaspictureIsNotNull() {
            addCriterion("hasPicture is not null");
            return (Criteria) this;
        }

        public Criteria andHaspictureEqualTo(String value) {
            addCriterion("hasPicture =", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureNotEqualTo(String value) {
            addCriterion("hasPicture <>", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureGreaterThan(String value) {
            addCriterion("hasPicture >", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureGreaterThanOrEqualTo(String value) {
            addCriterion("hasPicture >=", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureLessThan(String value) {
            addCriterion("hasPicture <", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureLessThanOrEqualTo(String value) {
            addCriterion("hasPicture <=", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureLike(String value) {
            addCriterion("hasPicture like", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureNotLike(String value) {
            addCriterion("hasPicture not like", value, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureIn(List<String> values) {
            addCriterion("hasPicture in", values, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureNotIn(List<String> values) {
            addCriterion("hasPicture not in", values, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureBetween(String value1, String value2) {
            addCriterion("hasPicture between", value1, value2, "haspicture");
            return (Criteria) this;
        }

        public Criteria andHaspictureNotBetween(String value1, String value2) {
            addCriterion("hasPicture not between", value1, value2, "haspicture");
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