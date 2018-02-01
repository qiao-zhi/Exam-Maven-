package cn.xm.exam.bean.employee.out;

import java.util.ArrayList;
import java.util.List;

public class EmployeeoutdistributeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeoutdistributeExample() {
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

        public Criteria andBigidIsNull() {
            addCriterion("bigid is null");
            return (Criteria) this;
        }

        public Criteria andBigidIsNotNull() {
            addCriterion("bigid is not null");
            return (Criteria) this;
        }

        public Criteria andBigidEqualTo(String value) {
            addCriterion("bigid =", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotEqualTo(String value) {
            addCriterion("bigid <>", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThan(String value) {
            addCriterion("bigid >", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThanOrEqualTo(String value) {
            addCriterion("bigid >=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThan(String value) {
            addCriterion("bigid <", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThanOrEqualTo(String value) {
            addCriterion("bigid <=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLike(String value) {
            addCriterion("bigid like", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotLike(String value) {
            addCriterion("bigid not like", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidIn(List<String> values) {
            addCriterion("bigid in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotIn(List<String> values) {
            addCriterion("bigid not in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidBetween(String value1, String value2) {
            addCriterion("bigid between", value1, value2, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotBetween(String value1, String value2) {
            addCriterion("bigid not between", value1, value2, "bigid");
            return (Criteria) this;
        }

        public Criteria andHaulempidIsNull() {
            addCriterion("haulempid is null");
            return (Criteria) this;
        }

        public Criteria andHaulempidIsNotNull() {
            addCriterion("haulempid is not null");
            return (Criteria) this;
        }

        public Criteria andHaulempidEqualTo(String value) {
            addCriterion("haulempid =", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidNotEqualTo(String value) {
            addCriterion("haulempid <>", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidGreaterThan(String value) {
            addCriterion("haulempid >", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidGreaterThanOrEqualTo(String value) {
            addCriterion("haulempid >=", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidLessThan(String value) {
            addCriterion("haulempid <", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidLessThanOrEqualTo(String value) {
            addCriterion("haulempid <=", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidLike(String value) {
            addCriterion("haulempid like", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidNotLike(String value) {
            addCriterion("haulempid not like", value, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidIn(List<String> values) {
            addCriterion("haulempid in", values, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidNotIn(List<String> values) {
            addCriterion("haulempid not in", values, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidBetween(String value1, String value2) {
            addCriterion("haulempid between", value1, value2, "haulempid");
            return (Criteria) this;
        }

        public Criteria andHaulempidNotBetween(String value1, String value2) {
            addCriterion("haulempid not between", value1, value2, "haulempid");
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

        public Criteria andOutempnameIsNull() {
            addCriterion("outempname is null");
            return (Criteria) this;
        }

        public Criteria andOutempnameIsNotNull() {
            addCriterion("outempname is not null");
            return (Criteria) this;
        }

        public Criteria andOutempnameEqualTo(String value) {
            addCriterion("outempname =", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameNotEqualTo(String value) {
            addCriterion("outempname <>", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameGreaterThan(String value) {
            addCriterion("outempname >", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameGreaterThanOrEqualTo(String value) {
            addCriterion("outempname >=", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameLessThan(String value) {
            addCriterion("outempname <", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameLessThanOrEqualTo(String value) {
            addCriterion("outempname <=", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameLike(String value) {
            addCriterion("outempname like", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameNotLike(String value) {
            addCriterion("outempname not like", value, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameIn(List<String> values) {
            addCriterion("outempname in", values, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameNotIn(List<String> values) {
            addCriterion("outempname not in", values, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameBetween(String value1, String value2) {
            addCriterion("outempname between", value1, value2, "outempname");
            return (Criteria) this;
        }

        public Criteria andOutempnameNotBetween(String value1, String value2) {
            addCriterion("outempname not between", value1, value2, "outempname");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardIsNull() {
            addCriterion("empOutIdCard is null");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardIsNotNull() {
            addCriterion("empOutIdCard is not null");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardEqualTo(String value) {
            addCriterion("empOutIdCard =", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotEqualTo(String value) {
            addCriterion("empOutIdCard <>", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardGreaterThan(String value) {
            addCriterion("empOutIdCard >", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardGreaterThanOrEqualTo(String value) {
            addCriterion("empOutIdCard >=", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLessThan(String value) {
            addCriterion("empOutIdCard <", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLessThanOrEqualTo(String value) {
            addCriterion("empOutIdCard <=", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLike(String value) {
            addCriterion("empOutIdCard like", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotLike(String value) {
            addCriterion("empOutIdCard not like", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardIn(List<String> values) {
            addCriterion("empOutIdCard in", values, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotIn(List<String> values) {
            addCriterion("empOutIdCard not in", values, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardBetween(String value1, String value2) {
            addCriterion("empOutIdCard between", value1, value2, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotBetween(String value1, String value2) {
            addCriterion("empOutIdCard not between", value1, value2, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusIsNull() {
            addCriterion("empOutexamStatus is null");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusIsNotNull() {
            addCriterion("empOutexamStatus is not null");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusEqualTo(String value) {
            addCriterion("empOutexamStatus =", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusNotEqualTo(String value) {
            addCriterion("empOutexamStatus <>", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusGreaterThan(String value) {
            addCriterion("empOutexamStatus >", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusGreaterThanOrEqualTo(String value) {
            addCriterion("empOutexamStatus >=", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusLessThan(String value) {
            addCriterion("empOutexamStatus <", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusLessThanOrEqualTo(String value) {
            addCriterion("empOutexamStatus <=", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusLike(String value) {
            addCriterion("empOutexamStatus like", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusNotLike(String value) {
            addCriterion("empOutexamStatus not like", value, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusIn(List<String> values) {
            addCriterion("empOutexamStatus in", values, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusNotIn(List<String> values) {
            addCriterion("empOutexamStatus not in", values, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusBetween(String value1, String value2) {
            addCriterion("empOutexamStatus between", value1, value2, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpoutexamstatusNotBetween(String value1, String value2) {
            addCriterion("empOutexamStatus not between", value1, value2, "empoutexamstatus");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeIsNull() {
            addCriterion("empOutTrainGrade is null");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeIsNotNull() {
            addCriterion("empOutTrainGrade is not null");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeEqualTo(String value) {
            addCriterion("empOutTrainGrade =", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeNotEqualTo(String value) {
            addCriterion("empOutTrainGrade <>", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeGreaterThan(String value) {
            addCriterion("empOutTrainGrade >", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeGreaterThanOrEqualTo(String value) {
            addCriterion("empOutTrainGrade >=", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeLessThan(String value) {
            addCriterion("empOutTrainGrade <", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeLessThanOrEqualTo(String value) {
            addCriterion("empOutTrainGrade <=", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeLike(String value) {
            addCriterion("empOutTrainGrade like", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeNotLike(String value) {
            addCriterion("empOutTrainGrade not like", value, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeIn(List<String> values) {
            addCriterion("empOutTrainGrade in", values, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeNotIn(List<String> values) {
            addCriterion("empOutTrainGrade not in", values, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeBetween(String value1, String value2) {
            addCriterion("empOutTrainGrade between", value1, value2, "empouttraingrade");
            return (Criteria) this;
        }

        public Criteria andEmpouttraingradeNotBetween(String value1, String value2) {
            addCriterion("empOutTrainGrade not between", value1, value2, "empouttraingrade");
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