package cn.xm.exam.bean.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionbankExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionbankExample() {
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

        public Criteria andQuestionbanknameIsNull() {
            addCriterion("questionBankName is null");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameIsNotNull() {
            addCriterion("questionBankName is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameEqualTo(String value) {
            addCriterion("questionBankName =", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameNotEqualTo(String value) {
            addCriterion("questionBankName <>", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameGreaterThan(String value) {
            addCriterion("questionBankName >", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameGreaterThanOrEqualTo(String value) {
            addCriterion("questionBankName >=", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameLessThan(String value) {
            addCriterion("questionBankName <", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameLessThanOrEqualTo(String value) {
            addCriterion("questionBankName <=", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameLike(String value) {
            addCriterion("questionBankName like", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameNotLike(String value) {
            addCriterion("questionBankName not like", value, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameIn(List<String> values) {
            addCriterion("questionBankName in", values, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameNotIn(List<String> values) {
            addCriterion("questionBankName not in", values, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameBetween(String value1, String value2) {
            addCriterion("questionBankName between", value1, value2, "questionbankname");
            return (Criteria) this;
        }

        public Criteria andQuestionbanknameNotBetween(String value1, String value2) {
            addCriterion("questionBankName not between", value1, value2, "questionbankname");
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

        public Criteria andIsuseIsNull() {
            addCriterion("isUse is null");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNotNull() {
            addCriterion("isUse is not null");
            return (Criteria) this;
        }

        public Criteria andIsuseEqualTo(String value) {
            addCriterion("isUse =", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotEqualTo(String value) {
            addCriterion("isUse <>", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThan(String value) {
            addCriterion("isUse >", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThanOrEqualTo(String value) {
            addCriterion("isUse >=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThan(String value) {
            addCriterion("isUse <", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThanOrEqualTo(String value) {
            addCriterion("isUse <=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLike(String value) {
            addCriterion("isUse like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotLike(String value) {
            addCriterion("isUse not like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseIn(List<String> values) {
            addCriterion("isUse in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotIn(List<String> values) {
            addCriterion("isUse not in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseBetween(String value1, String value2) {
            addCriterion("isUse between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotBetween(String value1, String value2) {
            addCriterion("isUse not between", value1, value2, "isuse");
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

        public Criteria andCategorynameidIsNull() {
            addCriterion("categoryNameId is null");
            return (Criteria) this;
        }

        public Criteria andCategorynameidIsNotNull() {
            addCriterion("categoryNameId is not null");
            return (Criteria) this;
        }

        public Criteria andCategorynameidEqualTo(String value) {
            addCriterion("categoryNameId =", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidNotEqualTo(String value) {
            addCriterion("categoryNameId <>", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidGreaterThan(String value) {
            addCriterion("categoryNameId >", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidGreaterThanOrEqualTo(String value) {
            addCriterion("categoryNameId >=", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidLessThan(String value) {
            addCriterion("categoryNameId <", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidLessThanOrEqualTo(String value) {
            addCriterion("categoryNameId <=", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidLike(String value) {
            addCriterion("categoryNameId like", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidNotLike(String value) {
            addCriterion("categoryNameId not like", value, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidIn(List<String> values) {
            addCriterion("categoryNameId in", values, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidNotIn(List<String> values) {
            addCriterion("categoryNameId not in", values, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidBetween(String value1, String value2) {
            addCriterion("categoryNameId between", value1, value2, "categorynameid");
            return (Criteria) this;
        }

        public Criteria andCategorynameidNotBetween(String value1, String value2) {
            addCriterion("categoryNameId not between", value1, value2, "categorynameid");
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