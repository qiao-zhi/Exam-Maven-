package cn.xm.exam.bean.haul;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HaulinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HaulinfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBignameIsNull() {
            addCriterion("bigName is null");
            return (Criteria) this;
        }

        public Criteria andBignameIsNotNull() {
            addCriterion("bigName is not null");
            return (Criteria) this;
        }

        public Criteria andBignameEqualTo(String value) {
            addCriterion("bigName =", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameNotEqualTo(String value) {
            addCriterion("bigName <>", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameGreaterThan(String value) {
            addCriterion("bigName >", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameGreaterThanOrEqualTo(String value) {
            addCriterion("bigName >=", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameLessThan(String value) {
            addCriterion("bigName <", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameLessThanOrEqualTo(String value) {
            addCriterion("bigName <=", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameLike(String value) {
            addCriterion("bigName like", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameNotLike(String value) {
            addCriterion("bigName not like", value, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameIn(List<String> values) {
            addCriterion("bigName in", values, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameNotIn(List<String> values) {
            addCriterion("bigName not in", values, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameBetween(String value1, String value2) {
            addCriterion("bigName between", value1, value2, "bigname");
            return (Criteria) this;
        }

        public Criteria andBignameNotBetween(String value1, String value2) {
            addCriterion("bigName not between", value1, value2, "bigname");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionIsNull() {
            addCriterion("bigdescription is null");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionIsNotNull() {
            addCriterion("bigdescription is not null");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionEqualTo(String value) {
            addCriterion("bigdescription =", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionNotEqualTo(String value) {
            addCriterion("bigdescription <>", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionGreaterThan(String value) {
            addCriterion("bigdescription >", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("bigdescription >=", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionLessThan(String value) {
            addCriterion("bigdescription <", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionLessThanOrEqualTo(String value) {
            addCriterion("bigdescription <=", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionLike(String value) {
            addCriterion("bigdescription like", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionNotLike(String value) {
            addCriterion("bigdescription not like", value, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionIn(List<String> values) {
            addCriterion("bigdescription in", values, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionNotIn(List<String> values) {
            addCriterion("bigdescription not in", values, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionBetween(String value1, String value2) {
            addCriterion("bigdescription between", value1, value2, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigdescriptionNotBetween(String value1, String value2) {
            addCriterion("bigdescription not between", value1, value2, "bigdescription");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateIsNull() {
            addCriterion("bigCreateDate is null");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateIsNotNull() {
            addCriterion("bigCreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateEqualTo(Date value) {
            addCriterionForJDBCDate("bigCreateDate =", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("bigCreateDate <>", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("bigCreateDate >", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigCreateDate >=", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateLessThan(Date value) {
            addCriterionForJDBCDate("bigCreateDate <", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigCreateDate <=", value, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateIn(List<Date> values) {
            addCriterionForJDBCDate("bigCreateDate in", values, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("bigCreateDate not in", values, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigCreateDate between", value1, value2, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigcreatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigCreateDate not between", value1, value2, "bigcreatedate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateIsNull() {
            addCriterion("bigBeginDate is null");
            return (Criteria) this;
        }

        public Criteria andBigbegindateIsNotNull() {
            addCriterion("bigBeginDate is not null");
            return (Criteria) this;
        }

        public Criteria andBigbegindateEqualTo(Date value) {
            addCriterionForJDBCDate("bigBeginDate =", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateNotEqualTo(Date value) {
            addCriterionForJDBCDate("bigBeginDate <>", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateGreaterThan(Date value) {
            addCriterionForJDBCDate("bigBeginDate >", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigBeginDate >=", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateLessThan(Date value) {
            addCriterionForJDBCDate("bigBeginDate <", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigBeginDate <=", value, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateIn(List<Date> values) {
            addCriterionForJDBCDate("bigBeginDate in", values, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateNotIn(List<Date> values) {
            addCriterionForJDBCDate("bigBeginDate not in", values, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigBeginDate between", value1, value2, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigbegindateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigBeginDate not between", value1, value2, "bigbegindate");
            return (Criteria) this;
        }

        public Criteria andBigenddateIsNull() {
            addCriterion("bigEndDate is null");
            return (Criteria) this;
        }

        public Criteria andBigenddateIsNotNull() {
            addCriterion("bigEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andBigenddateEqualTo(Date value) {
            addCriterionForJDBCDate("bigEndDate =", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("bigEndDate <>", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateGreaterThan(Date value) {
            addCriterionForJDBCDate("bigEndDate >", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigEndDate >=", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateLessThan(Date value) {
            addCriterionForJDBCDate("bigEndDate <", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bigEndDate <=", value, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateIn(List<Date> values) {
            addCriterionForJDBCDate("bigEndDate in", values, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("bigEndDate not in", values, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigEndDate between", value1, value2, "bigenddate");
            return (Criteria) this;
        }

        public Criteria andBigenddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bigEndDate not between", value1, value2, "bigenddate");
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