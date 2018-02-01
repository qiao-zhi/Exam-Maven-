package cn.xm.exam.bean.employee.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmployeeOutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeOutExample() {
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

        public Criteria andEmployeenumberIsNull() {
            addCriterion("employeeNumber is null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberIsNotNull() {
            addCriterion("employeeNumber is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberEqualTo(String value) {
            addCriterion("employeeNumber =", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberNotEqualTo(String value) {
            addCriterion("employeeNumber <>", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberGreaterThan(String value) {
            addCriterion("employeeNumber >", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberGreaterThanOrEqualTo(String value) {
            addCriterion("employeeNumber >=", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberLessThan(String value) {
            addCriterion("employeeNumber <", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberLessThanOrEqualTo(String value) {
            addCriterion("employeeNumber <=", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberLike(String value) {
            addCriterion("employeeNumber like", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberNotLike(String value) {
            addCriterion("employeeNumber not like", value, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberIn(List<String> values) {
            addCriterion("employeeNumber in", values, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberNotIn(List<String> values) {
            addCriterion("employeeNumber not in", values, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberBetween(String value1, String value2) {
            addCriterion("employeeNumber between", value1, value2, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andEmployeenumberNotBetween(String value1, String value2) {
            addCriterion("employeeNumber not between", value1, value2, "employeenumber");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdcodeIsNull() {
            addCriterion("idCode is null");
            return (Criteria) this;
        }

        public Criteria andIdcodeIsNotNull() {
            addCriterion("idCode is not null");
            return (Criteria) this;
        }

        public Criteria andIdcodeEqualTo(String value) {
            addCriterion("idCode =", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeNotEqualTo(String value) {
            addCriterion("idCode <>", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeGreaterThan(String value) {
            addCriterion("idCode >", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeGreaterThanOrEqualTo(String value) {
            addCriterion("idCode >=", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeLessThan(String value) {
            addCriterion("idCode <", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeLessThanOrEqualTo(String value) {
            addCriterion("idCode <=", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeLike(String value) {
            addCriterion("idCode like", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeNotLike(String value) {
            addCriterion("idCode not like", value, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeIn(List<String> values) {
            addCriterion("idCode in", values, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeNotIn(List<String> values) {
            addCriterion("idCode not in", values, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeBetween(String value1, String value2) {
            addCriterion("idCode between", value1, value2, "idcode");
            return (Criteria) this;
        }

        public Criteria andIdcodeNotBetween(String value1, String value2) {
            addCriterion("idCode not between", value1, value2, "idcode");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andFingerIsNull() {
            addCriterion("finger is null");
            return (Criteria) this;
        }

        public Criteria andFingerIsNotNull() {
            addCriterion("finger is not null");
            return (Criteria) this;
        }

        public Criteria andFingerEqualTo(String value) {
            addCriterion("finger =", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotEqualTo(String value) {
            addCriterion("finger <>", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerGreaterThan(String value) {
            addCriterion("finger >", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerGreaterThanOrEqualTo(String value) {
            addCriterion("finger >=", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLessThan(String value) {
            addCriterion("finger <", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLessThanOrEqualTo(String value) {
            addCriterion("finger <=", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLike(String value) {
            addCriterion("finger like", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotLike(String value) {
            addCriterion("finger not like", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerIn(List<String> values) {
            addCriterion("finger in", values, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotIn(List<String> values) {
            addCriterion("finger not in", values, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerBetween(String value1, String value2) {
            addCriterion("finger between", value1, value2, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotBetween(String value1, String value2) {
            addCriterion("finger not between", value1, value2, "finger");
            return (Criteria) this;
        }

        public Criteria andHeadaddressIsNull() {
            addCriterion("headaddress is null");
            return (Criteria) this;
        }

        public Criteria andHeadaddressIsNotNull() {
            addCriterion("headaddress is not null");
            return (Criteria) this;
        }

        public Criteria andHeadaddressEqualTo(String value) {
            addCriterion("headaddress =", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressNotEqualTo(String value) {
            addCriterion("headaddress <>", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressGreaterThan(String value) {
            addCriterion("headaddress >", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressGreaterThanOrEqualTo(String value) {
            addCriterion("headaddress >=", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressLessThan(String value) {
            addCriterion("headaddress <", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressLessThanOrEqualTo(String value) {
            addCriterion("headaddress <=", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressLike(String value) {
            addCriterion("headaddress like", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressNotLike(String value) {
            addCriterion("headaddress not like", value, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressIn(List<String> values) {
            addCriterion("headaddress in", values, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressNotIn(List<String> values) {
            addCriterion("headaddress not in", values, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressBetween(String value1, String value2) {
            addCriterion("headaddress between", value1, value2, "headaddress");
            return (Criteria) this;
        }

        public Criteria andHeadaddressNotBetween(String value1, String value2) {
            addCriterion("headaddress not between", value1, value2, "headaddress");
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