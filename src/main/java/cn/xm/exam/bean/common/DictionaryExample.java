package cn.xm.exam.bean.common;

import java.util.ArrayList;
import java.util.List;

public class DictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryExample() {
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

        public Criteria andDictionaryidIsNull() {
            addCriterion("dictionaryId is null");
            return (Criteria) this;
        }

        public Criteria andDictionaryidIsNotNull() {
            addCriterion("dictionaryId is not null");
            return (Criteria) this;
        }

        public Criteria andDictionaryidEqualTo(String value) {
            addCriterion("dictionaryId =", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidNotEqualTo(String value) {
            addCriterion("dictionaryId <>", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidGreaterThan(String value) {
            addCriterion("dictionaryId >", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidGreaterThanOrEqualTo(String value) {
            addCriterion("dictionaryId >=", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidLessThan(String value) {
            addCriterion("dictionaryId <", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidLessThanOrEqualTo(String value) {
            addCriterion("dictionaryId <=", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidLike(String value) {
            addCriterion("dictionaryId like", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidNotLike(String value) {
            addCriterion("dictionaryId not like", value, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidIn(List<String> values) {
            addCriterion("dictionaryId in", values, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidNotIn(List<String> values) {
            addCriterion("dictionaryId not in", values, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidBetween(String value1, String value2) {
            addCriterion("dictionaryId between", value1, value2, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionaryidNotBetween(String value1, String value2) {
            addCriterion("dictionaryId not between", value1, value2, "dictionaryid");
            return (Criteria) this;
        }

        public Criteria andDictionarynameIsNull() {
            addCriterion("dictionaryName is null");
            return (Criteria) this;
        }

        public Criteria andDictionarynameIsNotNull() {
            addCriterion("dictionaryName is not null");
            return (Criteria) this;
        }

        public Criteria andDictionarynameEqualTo(String value) {
            addCriterion("dictionaryName =", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameNotEqualTo(String value) {
            addCriterion("dictionaryName <>", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameGreaterThan(String value) {
            addCriterion("dictionaryName >", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameGreaterThanOrEqualTo(String value) {
            addCriterion("dictionaryName >=", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameLessThan(String value) {
            addCriterion("dictionaryName <", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameLessThanOrEqualTo(String value) {
            addCriterion("dictionaryName <=", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameLike(String value) {
            addCriterion("dictionaryName like", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameNotLike(String value) {
            addCriterion("dictionaryName not like", value, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameIn(List<String> values) {
            addCriterion("dictionaryName in", values, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameNotIn(List<String> values) {
            addCriterion("dictionaryName not in", values, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameBetween(String value1, String value2) {
            addCriterion("dictionaryName between", value1, value2, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andDictionarynameNotBetween(String value1, String value2) {
            addCriterion("dictionaryName not between", value1, value2, "dictionaryname");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidIsNull() {
            addCriterion("upDictionaryId is null");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidIsNotNull() {
            addCriterion("upDictionaryId is not null");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidEqualTo(String value) {
            addCriterion("upDictionaryId =", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidNotEqualTo(String value) {
            addCriterion("upDictionaryId <>", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidGreaterThan(String value) {
            addCriterion("upDictionaryId >", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidGreaterThanOrEqualTo(String value) {
            addCriterion("upDictionaryId >=", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidLessThan(String value) {
            addCriterion("upDictionaryId <", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidLessThanOrEqualTo(String value) {
            addCriterion("upDictionaryId <=", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidLike(String value) {
            addCriterion("upDictionaryId like", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidNotLike(String value) {
            addCriterion("upDictionaryId not like", value, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidIn(List<String> values) {
            addCriterion("upDictionaryId in", values, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidNotIn(List<String> values) {
            addCriterion("upDictionaryId not in", values, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidBetween(String value1, String value2) {
            addCriterion("upDictionaryId between", value1, value2, "updictionaryid");
            return (Criteria) this;
        }

        public Criteria andUpdictionaryidNotBetween(String value1, String value2) {
            addCriterion("upDictionaryId not between", value1, value2, "updictionaryid");
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

        public Criteria andDiscriptionIsNull() {
            addCriterion("discription is null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionIsNotNull() {
            addCriterion("discription is not null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionEqualTo(String value) {
            addCriterion("discription =", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotEqualTo(String value) {
            addCriterion("discription <>", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionGreaterThan(String value) {
            addCriterion("discription >", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionGreaterThanOrEqualTo(String value) {
            addCriterion("discription >=", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLessThan(String value) {
            addCriterion("discription <", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLessThanOrEqualTo(String value) {
            addCriterion("discription <=", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionLike(String value) {
            addCriterion("discription like", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotLike(String value) {
            addCriterion("discription not like", value, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionIn(List<String> values) {
            addCriterion("discription in", values, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotIn(List<String> values) {
            addCriterion("discription not in", values, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionBetween(String value1, String value2) {
            addCriterion("discription between", value1, value2, "discription");
            return (Criteria) this;
        }

        public Criteria andDiscriptionNotBetween(String value1, String value2) {
            addCriterion("discription not between", value1, value2, "discription");
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