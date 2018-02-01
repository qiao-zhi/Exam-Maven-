package cn.xm.exam.bean.haul;

import java.util.ArrayList;
import java.util.List;

public class HaulemployeeoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HaulemployeeoutExample() {
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

        public Criteria andBigemployeeoutidIsNull() {
            addCriterion("BigEmployeeoutId is null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIsNotNull() {
            addCriterion("BigEmployeeoutId is not null");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidEqualTo(String value) {
            addCriterion("BigEmployeeoutId =", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotEqualTo(String value) {
            addCriterion("BigEmployeeoutId <>", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThan(String value) {
            addCriterion("BigEmployeeoutId >", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidGreaterThanOrEqualTo(String value) {
            addCriterion("BigEmployeeoutId >=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThan(String value) {
            addCriterion("BigEmployeeoutId <", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLessThanOrEqualTo(String value) {
            addCriterion("BigEmployeeoutId <=", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidLike(String value) {
            addCriterion("BigEmployeeoutId like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotLike(String value) {
            addCriterion("BigEmployeeoutId not like", value, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidIn(List<String> values) {
            addCriterion("BigEmployeeoutId in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotIn(List<String> values) {
            addCriterion("BigEmployeeoutId not in", values, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidBetween(String value1, String value2) {
            addCriterion("BigEmployeeoutId between", value1, value2, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andBigemployeeoutidNotBetween(String value1, String value2) {
            addCriterion("BigEmployeeoutId not between", value1, value2, "bigemployeeoutid");
            return (Criteria) this;
        }

        public Criteria andUnitidIsNull() {
            addCriterion("unitId is null");
            return (Criteria) this;
        }

        public Criteria andUnitidIsNotNull() {
            addCriterion("unitId is not null");
            return (Criteria) this;
        }

        public Criteria andUnitidEqualTo(String value) {
            addCriterion("unitId =", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotEqualTo(String value) {
            addCriterion("unitId <>", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidGreaterThan(String value) {
            addCriterion("unitId >", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidGreaterThanOrEqualTo(String value) {
            addCriterion("unitId >=", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLessThan(String value) {
            addCriterion("unitId <", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLessThanOrEqualTo(String value) {
            addCriterion("unitId <=", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidLike(String value) {
            addCriterion("unitId like", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotLike(String value) {
            addCriterion("unitId not like", value, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidIn(List<String> values) {
            addCriterion("unitId in", values, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotIn(List<String> values) {
            addCriterion("unitId not in", values, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidBetween(String value1, String value2) {
            addCriterion("unitId between", value1, value2, "unitid");
            return (Criteria) this;
        }

        public Criteria andUnitidNotBetween(String value1, String value2) {
            addCriterion("unitId not between", value1, value2, "unitid");
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

        public Criteria andEmpoutidcardIsNull() {
            addCriterion("empoutIdcard is null");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardIsNotNull() {
            addCriterion("empoutIdcard is not null");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardEqualTo(String value) {
            addCriterion("empoutIdcard =", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotEqualTo(String value) {
            addCriterion("empoutIdcard <>", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardGreaterThan(String value) {
            addCriterion("empoutIdcard >", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardGreaterThanOrEqualTo(String value) {
            addCriterion("empoutIdcard >=", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLessThan(String value) {
            addCriterion("empoutIdcard <", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLessThanOrEqualTo(String value) {
            addCriterion("empoutIdcard <=", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardLike(String value) {
            addCriterion("empoutIdcard like", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotLike(String value) {
            addCriterion("empoutIdcard not like", value, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardIn(List<String> values) {
            addCriterion("empoutIdcard in", values, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotIn(List<String> values) {
            addCriterion("empoutIdcard not in", values, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardBetween(String value1, String value2) {
            addCriterion("empoutIdcard between", value1, value2, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andEmpoutidcardNotBetween(String value1, String value2) {
            addCriterion("empoutIdcard not between", value1, value2, "empoutidcard");
            return (Criteria) this;
        }

        public Criteria andTrainstatusIsNull() {
            addCriterion("trainStatus is null");
            return (Criteria) this;
        }

        public Criteria andTrainstatusIsNotNull() {
            addCriterion("trainStatus is not null");
            return (Criteria) this;
        }

        public Criteria andTrainstatusEqualTo(String value) {
            addCriterion("trainStatus =", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusNotEqualTo(String value) {
            addCriterion("trainStatus <>", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusGreaterThan(String value) {
            addCriterion("trainStatus >", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusGreaterThanOrEqualTo(String value) {
            addCriterion("trainStatus >=", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusLessThan(String value) {
            addCriterion("trainStatus <", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusLessThanOrEqualTo(String value) {
            addCriterion("trainStatus <=", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusLike(String value) {
            addCriterion("trainStatus like", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusNotLike(String value) {
            addCriterion("trainStatus not like", value, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusIn(List<String> values) {
            addCriterion("trainStatus in", values, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusNotIn(List<String> values) {
            addCriterion("trainStatus not in", values, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusBetween(String value1, String value2) {
            addCriterion("trainStatus between", value1, value2, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andTrainstatusNotBetween(String value1, String value2) {
            addCriterion("trainStatus not between", value1, value2, "trainstatus");
            return (Criteria) this;
        }

        public Criteria andEmptypeIsNull() {
            addCriterion("empType is null");
            return (Criteria) this;
        }

        public Criteria andEmptypeIsNotNull() {
            addCriterion("empType is not null");
            return (Criteria) this;
        }

        public Criteria andEmptypeEqualTo(String value) {
            addCriterion("empType =", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotEqualTo(String value) {
            addCriterion("empType <>", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeGreaterThan(String value) {
            addCriterion("empType >", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeGreaterThanOrEqualTo(String value) {
            addCriterion("empType >=", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLessThan(String value) {
            addCriterion("empType <", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLessThanOrEqualTo(String value) {
            addCriterion("empType <=", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLike(String value) {
            addCriterion("empType like", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotLike(String value) {
            addCriterion("empType not like", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeIn(List<String> values) {
            addCriterion("empType in", values, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotIn(List<String> values) {
            addCriterion("empType not in", values, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeBetween(String value1, String value2) {
            addCriterion("empType between", value1, value2, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotBetween(String value1, String value2) {
            addCriterion("empType not between", value1, value2, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneIsNull() {
            addCriterion("empOutphone is null");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneIsNotNull() {
            addCriterion("empOutphone is not null");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneEqualTo(String value) {
            addCriterion("empOutphone =", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneNotEqualTo(String value) {
            addCriterion("empOutphone <>", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneGreaterThan(String value) {
            addCriterion("empOutphone >", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneGreaterThanOrEqualTo(String value) {
            addCriterion("empOutphone >=", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneLessThan(String value) {
            addCriterion("empOutphone <", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneLessThanOrEqualTo(String value) {
            addCriterion("empOutphone <=", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneLike(String value) {
            addCriterion("empOutphone like", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneNotLike(String value) {
            addCriterion("empOutphone not like", value, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneIn(List<String> values) {
            addCriterion("empOutphone in", values, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneNotIn(List<String> values) {
            addCriterion("empOutphone not in", values, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneBetween(String value1, String value2) {
            addCriterion("empOutphone between", value1, value2, "empoutphone");
            return (Criteria) this;
        }

        public Criteria andEmpoutphoneNotBetween(String value1, String value2) {
            addCriterion("empOutphone not between", value1, value2, "empoutphone");
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