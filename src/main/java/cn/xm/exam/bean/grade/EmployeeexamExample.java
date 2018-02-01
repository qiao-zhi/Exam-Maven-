package cn.xm.exam.bean.grade;

import java.util.ArrayList;
import java.util.List;

public class EmployeeexamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeexamExample() {
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

        public Criteria andGradeidIsNull() {
            addCriterion("gradeId is null");
            return (Criteria) this;
        }

        public Criteria andGradeidIsNotNull() {
            addCriterion("gradeId is not null");
            return (Criteria) this;
        }

        public Criteria andGradeidEqualTo(Integer value) {
            addCriterion("gradeId =", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidNotEqualTo(Integer value) {
            addCriterion("gradeId <>", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidGreaterThan(Integer value) {
            addCriterion("gradeId >", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gradeId >=", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidLessThan(Integer value) {
            addCriterion("gradeId <", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidLessThanOrEqualTo(Integer value) {
            addCriterion("gradeId <=", value, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidIn(List<Integer> values) {
            addCriterion("gradeId in", values, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidNotIn(List<Integer> values) {
            addCriterion("gradeId not in", values, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidBetween(Integer value1, Integer value2) {
            addCriterion("gradeId between", value1, value2, "gradeid");
            return (Criteria) this;
        }

        public Criteria andGradeidNotBetween(Integer value1, Integer value2) {
            addCriterion("gradeId not between", value1, value2, "gradeid");
            return (Criteria) this;
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Float value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Float value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Float value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Float value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Float value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Float value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Float> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Float> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Float value1, Float value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Float value1, Float value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andExammethodIsNull() {
            addCriterion("examMethod is null");
            return (Criteria) this;
        }

        public Criteria andExammethodIsNotNull() {
            addCriterion("examMethod is not null");
            return (Criteria) this;
        }

        public Criteria andExammethodEqualTo(String value) {
            addCriterion("examMethod =", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodNotEqualTo(String value) {
            addCriterion("examMethod <>", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodGreaterThan(String value) {
            addCriterion("examMethod >", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodGreaterThanOrEqualTo(String value) {
            addCriterion("examMethod >=", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodLessThan(String value) {
            addCriterion("examMethod <", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodLessThanOrEqualTo(String value) {
            addCriterion("examMethod <=", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodLike(String value) {
            addCriterion("examMethod like", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodNotLike(String value) {
            addCriterion("examMethod not like", value, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodIn(List<String> values) {
            addCriterion("examMethod in", values, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodNotIn(List<String> values) {
            addCriterion("examMethod not in", values, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodBetween(String value1, String value2) {
            addCriterion("examMethod between", value1, value2, "exammethod");
            return (Criteria) this;
        }

        public Criteria andExammethodNotBetween(String value1, String value2) {
            addCriterion("examMethod not between", value1, value2, "exammethod");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeIsNull() {
            addCriterion("employeeType is null");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeIsNotNull() {
            addCriterion("employeeType is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeEqualTo(String value) {
            addCriterion("employeeType =", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeNotEqualTo(String value) {
            addCriterion("employeeType <>", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeGreaterThan(String value) {
            addCriterion("employeeType >", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeGreaterThanOrEqualTo(String value) {
            addCriterion("employeeType >=", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeLessThan(String value) {
            addCriterion("employeeType <", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeLessThanOrEqualTo(String value) {
            addCriterion("employeeType <=", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeLike(String value) {
            addCriterion("employeeType like", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeNotLike(String value) {
            addCriterion("employeeType not like", value, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeIn(List<String> values) {
            addCriterion("employeeType in", values, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeNotIn(List<String> values) {
            addCriterion("employeeType not in", values, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeBetween(String value1, String value2) {
            addCriterion("employeeType between", value1, value2, "employeetype");
            return (Criteria) this;
        }

        public Criteria andEmployeetypeNotBetween(String value1, String value2) {
            addCriterion("employeeType not between", value1, value2, "employeetype");
            return (Criteria) this;
        }

        public Criteria andUnitidIsNull() {
            addCriterion("unitid is null");
            return (Criteria) this;
        }

        public Criteria andUnitidIsNotNull() {
            addCriterion("unitid is not null");
            return (Criteria) this;
        }

        public Criteria andUnitidEqualTo(String value) {
            addCriterion("unitid =", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotEqualTo(String value) {
            addCriterion("unitid <>", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidGreaterThan(String value) {
            addCriterion("unitid >", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidGreaterThanOrEqualTo(String value) {
            addCriterion("unitid >=", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLessThan(String value) {
            addCriterion("unitid <", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLessThanOrEqualTo(String value) {
            addCriterion("unitid <=", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLike(String value) {
            addCriterion("unitid like", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotLike(String value) {
            addCriterion("unitid not like", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidIn(List<String> values) {
            addCriterion("unitid in", values, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotIn(List<String> values) {
            addCriterion("unitid not in", values, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidBetween(String value1, String value2) {
            addCriterion("unitid between", value1, value2, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotBetween(String value1, String value2) {
            addCriterion("unitid not between", value1, value2, "unitid");
            return (Criteria) this;
        }

        public Criteria andDistributeidIsNull() {
            addCriterion("distributeid is null");
            return (Criteria) this;
        }

        public Criteria andDistributeidIsNotNull() {
            addCriterion("distributeid is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeidEqualTo(Integer value) {
            addCriterion("distributeid =", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidNotEqualTo(Integer value) {
            addCriterion("distributeid <>", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidGreaterThan(Integer value) {
            addCriterion("distributeid >", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("distributeid >=", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidLessThan(Integer value) {
            addCriterion("distributeid <", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidLessThanOrEqualTo(Integer value) {
            addCriterion("distributeid <=", value, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidIn(List<Integer> values) {
            addCriterion("distributeid in", values, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidNotIn(List<Integer> values) {
            addCriterion("distributeid not in", values, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidBetween(Integer value1, Integer value2) {
            addCriterion("distributeid between", value1, value2, "distributeid");
            return (Criteria) this;
        }

        public Criteria andDistributeidNotBetween(Integer value1, Integer value2) {
            addCriterion("distributeid not between", value1, value2, "distributeid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIsNull() {
            addCriterion("bigEmployeeOutId is null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIsNotNull() {
            addCriterion("bigEmployeeOutId is not null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidEqualTo(String value) {
            addCriterion("bigEmployeeOutId =", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotEqualTo(String value) {
            addCriterion("bigEmployeeOutId <>", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThan(String value) {
            addCriterion("bigEmployeeOutId >", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThanOrEqualTo(String value) {
            addCriterion("bigEmployeeOutId >=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThan(String value) {
            addCriterion("bigEmployeeOutId <", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThanOrEqualTo(String value) {
            addCriterion("bigEmployeeOutId <=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLike(String value) {
            addCriterion("bigEmployeeOutId like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotLike(String value) {
            addCriterion("bigEmployeeOutId not like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIn(List<String> values) {
            addCriterion("bigEmployeeOutId in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotIn(List<String> values) {
            addCriterion("bigEmployeeOutId not in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidBetween(String value1, String value2) {
            addCriterion("bigEmployeeOutId between", value1, value2, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotBetween(String value1, String value2) {
            addCriterion("bigEmployeeOutId not between", value1, value2, "bigemployeeoutid");
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