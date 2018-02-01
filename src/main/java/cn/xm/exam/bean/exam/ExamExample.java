package cn.xm.exam.bean.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamExample() {
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

        public Criteria andExamidIsNull() {
            addCriterion("examId is null");
            return (Criteria) this;
        }

        public Criteria andExamidIsNotNull() {
            addCriterion("examId is not null");
            return (Criteria) this;
        }

        public Criteria andExamidEqualTo(String value) {
            addCriterion("examId =", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidNotEqualTo(String value) {
            addCriterion("examId <>", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidGreaterThan(String value) {
            addCriterion("examId >", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidGreaterThanOrEqualTo(String value) {
            addCriterion("examId >=", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidLessThan(String value) {
            addCriterion("examId <", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidLessThanOrEqualTo(String value) {
            addCriterion("examId <=", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidLike(String value) {
            addCriterion("examId like", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidNotLike(String value) {
            addCriterion("examId not like", value, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidIn(List<String> values) {
            addCriterion("examId in", values, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidNotIn(List<String> values) {
            addCriterion("examId not in", values, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidBetween(String value1, String value2) {
            addCriterion("examId between", value1, value2, "examid");
            return (Criteria) this;
        }

        public Criteria andExamidNotBetween(String value1, String value2) {
            addCriterion("examId not between", value1, value2, "examid");
            return (Criteria) this;
        }

        public Criteria andExamnameIsNull() {
            addCriterion("examName is null");
            return (Criteria) this;
        }

        public Criteria andExamnameIsNotNull() {
            addCriterion("examName is not null");
            return (Criteria) this;
        }

        public Criteria andExamnameEqualTo(String value) {
            addCriterion("examName =", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameNotEqualTo(String value) {
            addCriterion("examName <>", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameGreaterThan(String value) {
            addCriterion("examName >", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameGreaterThanOrEqualTo(String value) {
            addCriterion("examName >=", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameLessThan(String value) {
            addCriterion("examName <", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameLessThanOrEqualTo(String value) {
            addCriterion("examName <=", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameLike(String value) {
            addCriterion("examName like", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameNotLike(String value) {
            addCriterion("examName not like", value, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameIn(List<String> values) {
            addCriterion("examName in", values, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameNotIn(List<String> values) {
            addCriterion("examName not in", values, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameBetween(String value1, String value2) {
            addCriterion("examName between", value1, value2, "examname");
            return (Criteria) this;
        }

        public Criteria andExamnameNotBetween(String value1, String value2) {
            addCriterion("examName not between", value1, value2, "examname");
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

        public Criteria andStarttimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("startTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("startTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("startTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("startTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("startTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("startTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("startTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("startTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("startTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andTraincontentIsNull() {
            addCriterion("traincontent is null");
            return (Criteria) this;
        }

        public Criteria andTraincontentIsNotNull() {
            addCriterion("traincontent is not null");
            return (Criteria) this;
        }

        public Criteria andTraincontentEqualTo(String value) {
            addCriterion("traincontent =", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentNotEqualTo(String value) {
            addCriterion("traincontent <>", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentGreaterThan(String value) {
            addCriterion("traincontent >", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentGreaterThanOrEqualTo(String value) {
            addCriterion("traincontent >=", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentLessThan(String value) {
            addCriterion("traincontent <", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentLessThanOrEqualTo(String value) {
            addCriterion("traincontent <=", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentLike(String value) {
            addCriterion("traincontent like", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentNotLike(String value) {
            addCriterion("traincontent not like", value, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentIn(List<String> values) {
            addCriterion("traincontent in", values, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentNotIn(List<String> values) {
            addCriterion("traincontent not in", values, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentBetween(String value1, String value2) {
            addCriterion("traincontent between", value1, value2, "traincontent");
            return (Criteria) this;
        }

        public Criteria andTraincontentNotBetween(String value1, String value2) {
            addCriterion("traincontent not between", value1, value2, "traincontent");
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

        public Criteria andXueshiIsNull() {
            addCriterion("xueshi is null");
            return (Criteria) this;
        }

        public Criteria andXueshiIsNotNull() {
            addCriterion("xueshi is not null");
            return (Criteria) this;
        }

        public Criteria andXueshiEqualTo(String value) {
            addCriterion("xueshi =", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiNotEqualTo(String value) {
            addCriterion("xueshi <>", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiGreaterThan(String value) {
            addCriterion("xueshi >", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiGreaterThanOrEqualTo(String value) {
            addCriterion("xueshi >=", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiLessThan(String value) {
            addCriterion("xueshi <", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiLessThanOrEqualTo(String value) {
            addCriterion("xueshi <=", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiLike(String value) {
            addCriterion("xueshi like", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiNotLike(String value) {
            addCriterion("xueshi not like", value, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiIn(List<String> values) {
            addCriterion("xueshi in", values, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiNotIn(List<String> values) {
            addCriterion("xueshi not in", values, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiBetween(String value1, String value2) {
            addCriterion("xueshi between", value1, value2, "xueshi");
            return (Criteria) this;
        }

        public Criteria andXueshiNotBetween(String value1, String value2) {
            addCriterion("xueshi not between", value1, value2, "xueshi");
            return (Criteria) this;
        }

        public Criteria andBigidIsNull() {
            addCriterion("bigId is null");
            return (Criteria) this;
        }

        public Criteria andBigidIsNotNull() {
            addCriterion("bigId is not null");
            return (Criteria) this;
        }

        public Criteria andBigidEqualTo(String value) {
            addCriterion("bigId =", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotEqualTo(String value) {
            addCriterion("bigId <>", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThan(String value) {
            addCriterion("bigId >", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThanOrEqualTo(String value) {
            addCriterion("bigId >=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThan(String value) {
            addCriterion("bigId <", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThanOrEqualTo(String value) {
            addCriterion("bigId <=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLike(String value) {
            addCriterion("bigId like", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotLike(String value) {
            addCriterion("bigId not like", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidIn(List<String> values) {
            addCriterion("bigId in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotIn(List<String> values) {
            addCriterion("bigId not in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidBetween(String value1, String value2) {
            addCriterion("bigId between", value1, value2, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotBetween(String value1, String value2) {
            addCriterion("bigId not between", value1, value2, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigstatusIsNull() {
            addCriterion("bigStatus is null");
            return (Criteria) this;
        }

        public Criteria andBigstatusIsNotNull() {
            addCriterion("bigStatus is not null");
            return (Criteria) this;
        }

        public Criteria andBigstatusEqualTo(String value) {
            addCriterion("bigStatus =", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusNotEqualTo(String value) {
            addCriterion("bigStatus <>", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusGreaterThan(String value) {
            addCriterion("bigStatus >", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusGreaterThanOrEqualTo(String value) {
            addCriterion("bigStatus >=", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusLessThan(String value) {
            addCriterion("bigStatus <", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusLessThanOrEqualTo(String value) {
            addCriterion("bigStatus <=", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusLike(String value) {
            addCriterion("bigStatus like", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusNotLike(String value) {
            addCriterion("bigStatus not like", value, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusIn(List<String> values) {
            addCriterion("bigStatus in", values, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusNotIn(List<String> values) {
            addCriterion("bigStatus not in", values, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusBetween(String value1, String value2) {
            addCriterion("bigStatus between", value1, value2, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andBigstatusNotBetween(String value1, String value2) {
            addCriterion("bigStatus not between", value1, value2, "bigstatus");
            return (Criteria) this;
        }

        public Criteria andExamlevelIsNull() {
            addCriterion("examLevel is null");
            return (Criteria) this;
        }

        public Criteria andExamlevelIsNotNull() {
            addCriterion("examLevel is not null");
            return (Criteria) this;
        }

        public Criteria andExamlevelEqualTo(String value) {
            addCriterion("examLevel =", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelNotEqualTo(String value) {
            addCriterion("examLevel <>", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelGreaterThan(String value) {
            addCriterion("examLevel >", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelGreaterThanOrEqualTo(String value) {
            addCriterion("examLevel >=", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelLessThan(String value) {
            addCriterion("examLevel <", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelLessThanOrEqualTo(String value) {
            addCriterion("examLevel <=", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelLike(String value) {
            addCriterion("examLevel like", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelNotLike(String value) {
            addCriterion("examLevel not like", value, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelIn(List<String> values) {
            addCriterion("examLevel in", values, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelNotIn(List<String> values) {
            addCriterion("examLevel not in", values, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelBetween(String value1, String value2) {
            addCriterion("examLevel between", value1, value2, "examlevel");
            return (Criteria) this;
        }

        public Criteria andExamlevelNotBetween(String value1, String value2) {
            addCriterion("examLevel not between", value1, value2, "examlevel");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIsNull() {
            addCriterion("employeeName is null");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIsNotNull() {
            addCriterion("employeeName is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeenameEqualTo(String value) {
            addCriterion("employeeName =", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotEqualTo(String value) {
            addCriterion("employeeName <>", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameGreaterThan(String value) {
            addCriterion("employeeName >", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameGreaterThanOrEqualTo(String value) {
            addCriterion("employeeName >=", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLessThan(String value) {
            addCriterion("employeeName <", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLessThanOrEqualTo(String value) {
            addCriterion("employeeName <=", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLike(String value) {
            addCriterion("employeeName like", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotLike(String value) {
            addCriterion("employeeName not like", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIn(List<String> values) {
            addCriterion("employeeName in", values, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotIn(List<String> values) {
            addCriterion("employeeName not in", values, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameBetween(String value1, String value2) {
            addCriterion("employeeName between", value1, value2, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotBetween(String value1, String value2) {
            addCriterion("employeeName not between", value1, value2, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIsNull() {
            addCriterion("employeeNum is null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIsNotNull() {
            addCriterion("employeeNum is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumEqualTo(Integer value) {
            addCriterion("employeeNum =", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotEqualTo(Integer value) {
            addCriterion("employeeNum <>", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumGreaterThan(Integer value) {
            addCriterion("employeeNum >", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("employeeNum >=", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumLessThan(Integer value) {
            addCriterion("employeeNum <", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumLessThanOrEqualTo(Integer value) {
            addCriterion("employeeNum <=", value, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumIn(List<Integer> values) {
            addCriterion("employeeNum in", values, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotIn(List<Integer> values) {
            addCriterion("employeeNum not in", values, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumBetween(Integer value1, Integer value2) {
            addCriterion("employeeNum between", value1, value2, "employeenum");
            return (Criteria) this;
        }

        public Criteria andEmployeenumNotBetween(Integer value1, Integer value2) {
            addCriterion("employeeNum not between", value1, value2, "employeenum");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andExamtypeIsNull() {
            addCriterion("examType is null");
            return (Criteria) this;
        }

        public Criteria andExamtypeIsNotNull() {
            addCriterion("examType is not null");
            return (Criteria) this;
        }

        public Criteria andExamtypeEqualTo(String value) {
            addCriterion("examType =", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeNotEqualTo(String value) {
            addCriterion("examType <>", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeGreaterThan(String value) {
            addCriterion("examType >", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeGreaterThanOrEqualTo(String value) {
            addCriterion("examType >=", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeLessThan(String value) {
            addCriterion("examType <", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeLessThanOrEqualTo(String value) {
            addCriterion("examType <=", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeLike(String value) {
            addCriterion("examType like", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeNotLike(String value) {
            addCriterion("examType not like", value, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeIn(List<String> values) {
            addCriterion("examType in", values, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeNotIn(List<String> values) {
            addCriterion("examType not in", values, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeBetween(String value1, String value2) {
            addCriterion("examType between", value1, value2, "examtype");
            return (Criteria) this;
        }

        public Criteria andExamtypeNotBetween(String value1, String value2) {
            addCriterion("examType not between", value1, value2, "examtype");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIsNull() {
            addCriterion("AnswerTime is null");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIsNotNull() {
            addCriterion("AnswerTime is not null");
            return (Criteria) this;
        }

        public Criteria andAnswertimeEqualTo(Integer value) {
            addCriterion("AnswerTime =", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotEqualTo(Integer value) {
            addCriterion("AnswerTime <>", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeGreaterThan(Integer value) {
            addCriterion("AnswerTime >", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("AnswerTime >=", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeLessThan(Integer value) {
            addCriterion("AnswerTime <", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeLessThanOrEqualTo(Integer value) {
            addCriterion("AnswerTime <=", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIn(List<Integer> values) {
            addCriterion("AnswerTime in", values, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotIn(List<Integer> values) {
            addCriterion("AnswerTime not in", values, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeBetween(Integer value1, Integer value2) {
            addCriterion("AnswerTime between", value1, value2, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("AnswerTime not between", value1, value2, "answertime");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNull() {
            addCriterion("departmentId is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNotNull() {
            addCriterion("departmentId is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidEqualTo(String value) {
            addCriterion("departmentId =", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotEqualTo(String value) {
            addCriterion("departmentId <>", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThan(String value) {
            addCriterion("departmentId >", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThanOrEqualTo(String value) {
            addCriterion("departmentId >=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThan(String value) {
            addCriterion("departmentId <", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThanOrEqualTo(String value) {
            addCriterion("departmentId <=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLike(String value) {
            addCriterion("departmentId like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotLike(String value) {
            addCriterion("departmentId not like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIn(List<String> values) {
            addCriterion("departmentId in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotIn(List<String> values) {
            addCriterion("departmentId not in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidBetween(String value1, String value2) {
            addCriterion("departmentId between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotBetween(String value1, String value2) {
            addCriterion("departmentId not between", value1, value2, "departmentid");
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