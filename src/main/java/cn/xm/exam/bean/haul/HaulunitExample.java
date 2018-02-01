package cn.xm.exam.bean.haul;

import java.util.ArrayList;
import java.util.List;

public class HaulunitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HaulunitExample() {
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

        public Criteria andUnitbigidIsNull() {
            addCriterion("unitBigId is null");
            return (Criteria) this;
        }

        public Criteria andUnitbigidIsNotNull() {
            addCriterion("unitBigId is not null");
            return (Criteria) this;
        }

        public Criteria andUnitbigidEqualTo(String value) {
            addCriterion("unitBigId =", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidNotEqualTo(String value) {
            addCriterion("unitBigId <>", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidGreaterThan(String value) {
            addCriterion("unitBigId >", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidGreaterThanOrEqualTo(String value) {
            addCriterion("unitBigId >=", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidLessThan(String value) {
            addCriterion("unitBigId <", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidLessThanOrEqualTo(String value) {
            addCriterion("unitBigId <=", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidLike(String value) {
            addCriterion("unitBigId like", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidNotLike(String value) {
            addCriterion("unitBigId not like", value, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidIn(List<String> values) {
            addCriterion("unitBigId in", values, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidNotIn(List<String> values) {
            addCriterion("unitBigId not in", values, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidBetween(String value1, String value2) {
            addCriterion("unitBigId between", value1, value2, "unitbigid");
            return (Criteria) this;
        }

        public Criteria andUnitbigidNotBetween(String value1, String value2) {
            addCriterion("unitBigId not between", value1, value2, "unitbigid");
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

        public Criteria andUnitminismumIsNull() {
            addCriterion("unitMinisMum is null");
            return (Criteria) this;
        }

        public Criteria andUnitminismumIsNotNull() {
            addCriterion("unitMinisMum is not null");
            return (Criteria) this;
        }

        public Criteria andUnitminismumEqualTo(Integer value) {
            addCriterion("unitMinisMum =", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumNotEqualTo(Integer value) {
            addCriterion("unitMinisMum <>", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumGreaterThan(Integer value) {
            addCriterion("unitMinisMum >", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumGreaterThanOrEqualTo(Integer value) {
            addCriterion("unitMinisMum >=", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumLessThan(Integer value) {
            addCriterion("unitMinisMum <", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumLessThanOrEqualTo(Integer value) {
            addCriterion("unitMinisMum <=", value, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumIn(List<Integer> values) {
            addCriterion("unitMinisMum in", values, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumNotIn(List<Integer> values) {
            addCriterion("unitMinisMum not in", values, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumBetween(Integer value1, Integer value2) {
            addCriterion("unitMinisMum between", value1, value2, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andUnitminismumNotBetween(Integer value1, Integer value2) {
            addCriterion("unitMinisMum not between", value1, value2, "unitminismum");
            return (Criteria) this;
        }

        public Criteria andIsnewbigIsNull() {
            addCriterion("isNewBig is null");
            return (Criteria) this;
        }

        public Criteria andIsnewbigIsNotNull() {
            addCriterion("isNewBig is not null");
            return (Criteria) this;
        }

        public Criteria andIsnewbigEqualTo(String value) {
            addCriterion("isNewBig =", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigNotEqualTo(String value) {
            addCriterion("isNewBig <>", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigGreaterThan(String value) {
            addCriterion("isNewBig >", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigGreaterThanOrEqualTo(String value) {
            addCriterion("isNewBig >=", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigLessThan(String value) {
            addCriterion("isNewBig <", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigLessThanOrEqualTo(String value) {
            addCriterion("isNewBig <=", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigLike(String value) {
            addCriterion("isNewBig like", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigNotLike(String value) {
            addCriterion("isNewBig not like", value, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigIn(List<String> values) {
            addCriterion("isNewBig in", values, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigNotIn(List<String> values) {
            addCriterion("isNewBig not in", values, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigBetween(String value1, String value2) {
            addCriterion("isNewBig between", value1, value2, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andIsnewbigNotBetween(String value1, String value2) {
            addCriterion("isNewBig not between", value1, value2, "isnewbig");
            return (Criteria) this;
        }

        public Criteria andProjectnamesIsNull() {
            addCriterion("projectNames is null");
            return (Criteria) this;
        }

        public Criteria andProjectnamesIsNotNull() {
            addCriterion("projectNames is not null");
            return (Criteria) this;
        }

        public Criteria andProjectnamesEqualTo(String value) {
            addCriterion("projectNames =", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesNotEqualTo(String value) {
            addCriterion("projectNames <>", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesGreaterThan(String value) {
            addCriterion("projectNames >", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesGreaterThanOrEqualTo(String value) {
            addCriterion("projectNames >=", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesLessThan(String value) {
            addCriterion("projectNames <", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesLessThanOrEqualTo(String value) {
            addCriterion("projectNames <=", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesLike(String value) {
            addCriterion("projectNames like", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesNotLike(String value) {
            addCriterion("projectNames not like", value, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesIn(List<String> values) {
            addCriterion("projectNames in", values, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesNotIn(List<String> values) {
            addCriterion("projectNames not in", values, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesBetween(String value1, String value2) {
            addCriterion("projectNames between", value1, value2, "projectnames");
            return (Criteria) this;
        }

        public Criteria andProjectnamesNotBetween(String value1, String value2) {
            addCriterion("projectNames not between", value1, value2, "projectnames");
            return (Criteria) this;
        }

        public Criteria andManagerIsNull() {
            addCriterion("manager is null");
            return (Criteria) this;
        }

        public Criteria andManagerIsNotNull() {
            addCriterion("manager is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEqualTo(String value) {
            addCriterion("manager =", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotEqualTo(String value) {
            addCriterion("manager <>", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThan(String value) {
            addCriterion("manager >", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThanOrEqualTo(String value) {
            addCriterion("manager >=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThan(String value) {
            addCriterion("manager <", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThanOrEqualTo(String value) {
            addCriterion("manager <=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLike(String value) {
            addCriterion("manager like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotLike(String value) {
            addCriterion("manager not like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerIn(List<String> values) {
            addCriterion("manager in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotIn(List<String> values) {
            addCriterion("manager not in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerBetween(String value1, String value2) {
            addCriterion("manager between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotBetween(String value1, String value2) {
            addCriterion("manager not between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerphoneIsNull() {
            addCriterion("managerPhone is null");
            return (Criteria) this;
        }

        public Criteria andManagerphoneIsNotNull() {
            addCriterion("managerPhone is not null");
            return (Criteria) this;
        }

        public Criteria andManagerphoneEqualTo(String value) {
            addCriterion("managerPhone =", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneNotEqualTo(String value) {
            addCriterion("managerPhone <>", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneGreaterThan(String value) {
            addCriterion("managerPhone >", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneGreaterThanOrEqualTo(String value) {
            addCriterion("managerPhone >=", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneLessThan(String value) {
            addCriterion("managerPhone <", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneLessThanOrEqualTo(String value) {
            addCriterion("managerPhone <=", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneLike(String value) {
            addCriterion("managerPhone like", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneNotLike(String value) {
            addCriterion("managerPhone not like", value, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneIn(List<String> values) {
            addCriterion("managerPhone in", values, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneNotIn(List<String> values) {
            addCriterion("managerPhone not in", values, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneBetween(String value1, String value2) {
            addCriterion("managerPhone between", value1, value2, "managerphone");
            return (Criteria) this;
        }

        public Criteria andManagerphoneNotBetween(String value1, String value2) {
            addCriterion("managerPhone not between", value1, value2, "managerphone");
            return (Criteria) this;
        }

        public Criteria andSecureIsNull() {
            addCriterion("secure is null");
            return (Criteria) this;
        }

        public Criteria andSecureIsNotNull() {
            addCriterion("secure is not null");
            return (Criteria) this;
        }

        public Criteria andSecureEqualTo(String value) {
            addCriterion("secure =", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureNotEqualTo(String value) {
            addCriterion("secure <>", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureGreaterThan(String value) {
            addCriterion("secure >", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureGreaterThanOrEqualTo(String value) {
            addCriterion("secure >=", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureLessThan(String value) {
            addCriterion("secure <", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureLessThanOrEqualTo(String value) {
            addCriterion("secure <=", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureLike(String value) {
            addCriterion("secure like", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureNotLike(String value) {
            addCriterion("secure not like", value, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureIn(List<String> values) {
            addCriterion("secure in", values, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureNotIn(List<String> values) {
            addCriterion("secure not in", values, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureBetween(String value1, String value2) {
            addCriterion("secure between", value1, value2, "secure");
            return (Criteria) this;
        }

        public Criteria andSecureNotBetween(String value1, String value2) {
            addCriterion("secure not between", value1, value2, "secure");
            return (Criteria) this;
        }

        public Criteria andSecurephoneIsNull() {
            addCriterion("securePhone is null");
            return (Criteria) this;
        }

        public Criteria andSecurephoneIsNotNull() {
            addCriterion("securePhone is not null");
            return (Criteria) this;
        }

        public Criteria andSecurephoneEqualTo(String value) {
            addCriterion("securePhone =", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneNotEqualTo(String value) {
            addCriterion("securePhone <>", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneGreaterThan(String value) {
            addCriterion("securePhone >", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneGreaterThanOrEqualTo(String value) {
            addCriterion("securePhone >=", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneLessThan(String value) {
            addCriterion("securePhone <", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneLessThanOrEqualTo(String value) {
            addCriterion("securePhone <=", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneLike(String value) {
            addCriterion("securePhone like", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneNotLike(String value) {
            addCriterion("securePhone not like", value, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneIn(List<String> values) {
            addCriterion("securePhone in", values, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneNotIn(List<String> values) {
            addCriterion("securePhone not in", values, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneBetween(String value1, String value2) {
            addCriterion("securePhone between", value1, value2, "securephone");
            return (Criteria) this;
        }

        public Criteria andSecurephoneNotBetween(String value1, String value2) {
            addCriterion("securePhone not between", value1, value2, "securephone");
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