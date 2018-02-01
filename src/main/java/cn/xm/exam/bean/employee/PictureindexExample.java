package cn.xm.exam.bean.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PictureindexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PictureindexExample() {
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

        public Criteria andPictureidIsNull() {
            addCriterion("pictureId is null");
            return (Criteria) this;
        }

        public Criteria andPictureidIsNotNull() {
            addCriterion("pictureId is not null");
            return (Criteria) this;
        }

        public Criteria andPictureidEqualTo(String value) {
            addCriterion("pictureId =", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidNotEqualTo(String value) {
            addCriterion("pictureId <>", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidGreaterThan(String value) {
            addCriterion("pictureId >", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidGreaterThanOrEqualTo(String value) {
            addCriterion("pictureId >=", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidLessThan(String value) {
            addCriterion("pictureId <", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidLessThanOrEqualTo(String value) {
            addCriterion("pictureId <=", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidLike(String value) {
            addCriterion("pictureId like", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidNotLike(String value) {
            addCriterion("pictureId not like", value, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidIn(List<String> values) {
            addCriterion("pictureId in", values, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidNotIn(List<String> values) {
            addCriterion("pictureId not in", values, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidBetween(String value1, String value2) {
            addCriterion("pictureId between", value1, value2, "pictureid");
            return (Criteria) this;
        }

        public Criteria andPictureidNotBetween(String value1, String value2) {
            addCriterion("pictureId not between", value1, value2, "pictureid");
            return (Criteria) this;
        }

        public Criteria andOriginalnameIsNull() {
            addCriterion("originalName is null");
            return (Criteria) this;
        }

        public Criteria andOriginalnameIsNotNull() {
            addCriterion("originalName is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalnameEqualTo(String value) {
            addCriterion("originalName =", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameNotEqualTo(String value) {
            addCriterion("originalName <>", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameGreaterThan(String value) {
            addCriterion("originalName >", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameGreaterThanOrEqualTo(String value) {
            addCriterion("originalName >=", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameLessThan(String value) {
            addCriterion("originalName <", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameLessThanOrEqualTo(String value) {
            addCriterion("originalName <=", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameLike(String value) {
            addCriterion("originalName like", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameNotLike(String value) {
            addCriterion("originalName not like", value, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameIn(List<String> values) {
            addCriterion("originalName in", values, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameNotIn(List<String> values) {
            addCriterion("originalName not in", values, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameBetween(String value1, String value2) {
            addCriterion("originalName between", value1, value2, "originalname");
            return (Criteria) this;
        }

        public Criteria andOriginalnameNotBetween(String value1, String value2) {
            addCriterion("originalName not between", value1, value2, "originalname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIsNull() {
            addCriterion("currentName is null");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIsNotNull() {
            addCriterion("currentName is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentnameEqualTo(String value) {
            addCriterion("currentName =", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotEqualTo(String value) {
            addCriterion("currentName <>", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameGreaterThan(String value) {
            addCriterion("currentName >", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameGreaterThanOrEqualTo(String value) {
            addCriterion("currentName >=", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLessThan(String value) {
            addCriterion("currentName <", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLessThanOrEqualTo(String value) {
            addCriterion("currentName <=", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameLike(String value) {
            addCriterion("currentName like", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotLike(String value) {
            addCriterion("currentName not like", value, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameIn(List<String> values) {
            addCriterion("currentName in", values, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotIn(List<String> values) {
            addCriterion("currentName not in", values, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameBetween(String value1, String value2) {
            addCriterion("currentName between", value1, value2, "currentname");
            return (Criteria) this;
        }

        public Criteria andCurrentnameNotBetween(String value1, String value2) {
            addCriterion("currentName not between", value1, value2, "currentname");
            return (Criteria) this;
        }

        public Criteria andPicturesizeIsNull() {
            addCriterion("pictureSize is null");
            return (Criteria) this;
        }

        public Criteria andPicturesizeIsNotNull() {
            addCriterion("pictureSize is not null");
            return (Criteria) this;
        }

        public Criteria andPicturesizeEqualTo(String value) {
            addCriterion("pictureSize =", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeNotEqualTo(String value) {
            addCriterion("pictureSize <>", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeGreaterThan(String value) {
            addCriterion("pictureSize >", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeGreaterThanOrEqualTo(String value) {
            addCriterion("pictureSize >=", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeLessThan(String value) {
            addCriterion("pictureSize <", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeLessThanOrEqualTo(String value) {
            addCriterion("pictureSize <=", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeLike(String value) {
            addCriterion("pictureSize like", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeNotLike(String value) {
            addCriterion("pictureSize not like", value, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeIn(List<String> values) {
            addCriterion("pictureSize in", values, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeNotIn(List<String> values) {
            addCriterion("pictureSize not in", values, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeBetween(String value1, String value2) {
            addCriterion("pictureSize between", value1, value2, "picturesize");
            return (Criteria) this;
        }

        public Criteria andPicturesizeNotBetween(String value1, String value2) {
            addCriterion("pictureSize not between", value1, value2, "picturesize");
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