package mx.com.ebs.inter.data.model.facbanco;

import mx.com.ebs.inter.data.model.PaginatedResult;
import mx.com.ebs.inter.util.Variables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MPlantillaExample  extends PaginatedResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public MPlantillaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        private int resultLimitRows = Variables.DEFAULT_SIZE_RESULT;

        public int getResultLimitRows() {
            return resultLimitRows;
        }

        public boolean isValid() {
            return criteria.size() > 0;
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

        public Criteria andIDIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIDIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIDEqualTo(Long value) {
            addCriterion("ID =", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDNotEqualTo(Long value) {
            addCriterion("ID <>", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDGreaterThan(Long value) {
            addCriterion("ID >", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDLessThan(Long value) {
            addCriterion("ID <", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "ID");
            return (Criteria) this;
        }

        public Criteria andIDIn(List<Long> values) {
            addCriterion("ID in", values, "ID");
            return (Criteria) this;
        }

        public Criteria andIDNotIn(List<Long> values) {
            addCriterion("ID not in", values, "ID");
            return (Criteria) this;
        }

        public Criteria andIDBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "ID");
            return (Criteria) this;
        }

        public Criteria andIDNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "ID");
            return (Criteria) this;
        }

        public Criteria andESTATUSIsNull() {
            addCriterion("ESTATUS is null");
            return (Criteria) this;
        }

        public Criteria andESTATUSIsNotNull() {
            addCriterion("ESTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andESTATUSEqualTo(Long value) {
            addCriterion("ESTATUS =", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSNotEqualTo(Long value) {
            addCriterion("ESTATUS <>", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSGreaterThan(Long value) {
            addCriterion("ESTATUS >", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSGreaterThanOrEqualTo(Long value) {
            addCriterion("ESTATUS >=", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSLessThan(Long value) {
            addCriterion("ESTATUS <", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSLessThanOrEqualTo(Long value) {
            addCriterion("ESTATUS <=", value, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSIn(List<Long> values) {
            addCriterion("ESTATUS in", values, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSNotIn(List<Long> values) {
            addCriterion("ESTATUS not in", values, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSBetween(Long value1, Long value2) {
            addCriterion("ESTATUS between", value1, value2, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andESTATUSNotBetween(Long value1, Long value2) {
            addCriterion("ESTATUS not between", value1, value2, "ESTATUS");
            return (Criteria) this;
        }

        public Criteria andFECHAIsNull() {
            addCriterion("FECHA is null");
            return (Criteria) this;
        }

        public Criteria andFECHAIsNotNull() {
            addCriterion("FECHA is not null");
            return (Criteria) this;
        }

        public Criteria andFECHAEqualTo(Date value) {
            addCriterion("FECHA =", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHANotEqualTo(Date value) {
            addCriterion("FECHA <>", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHAGreaterThan(Date value) {
            addCriterion("FECHA >", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHAGreaterThanOrEqualTo(Date value) {
            addCriterion("FECHA >=", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHALessThan(Date value) {
            addCriterion("FECHA <", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHALessThanOrEqualTo(Date value) {
            addCriterion("FECHA <=", value, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHAIn(List<Date> values) {
            addCriterion("FECHA in", values, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHANotIn(List<Date> values) {
            addCriterion("FECHA not in", values, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHABetween(Date value1, Date value2) {
            addCriterion("trunc(FECHA) between", value1, value2, "FECHA");
            return (Criteria) this;
        }

        public Criteria andFECHANotBetween(Date value1, Date value2) {
            addCriterion("FECHA not between", value1, value2, "FECHA");
            return (Criteria) this;
        }

        public Criteria andNOMBREIsNull() {
            addCriterion("NOMBRE is null");
            return (Criteria) this;
        }

        public Criteria andNOMBREIsNotNull() {
            addCriterion("NOMBRE is not null");
            return (Criteria) this;
        }

        public Criteria andNOMBREEqualTo(String value) {
            addCriterion("NOMBRE =", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRENotEqualTo(String value) {
            addCriterion("NOMBRE <>", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBREGreaterThan(String value) {
            addCriterion("NOMBRE >", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBREGreaterThanOrEqualTo(String value) {
            addCriterion("NOMBRE >=", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRELessThan(String value) {
            addCriterion("NOMBRE <", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRELessThanOrEqualTo(String value) {
            addCriterion("NOMBRE <=", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRELike(String value) {
            addCriterion("NOMBRE like", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRENotLike(String value) {
            addCriterion("NOMBRE not like", value, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBREIn(List<String> values) {
            addCriterion("NOMBRE in", values, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRENotIn(List<String> values) {
            addCriterion("NOMBRE not in", values, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBREBetween(String value1, String value2) {
            addCriterion("NOMBRE between", value1, value2, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andNOMBRENotBetween(String value1, String value2) {
            addCriterion("NOMBRE not between", value1, value2, "NOMBRE");
            return (Criteria) this;
        }

        public Criteria andSERIEIsNull() {
            addCriterion("SERIE is null");
            return (Criteria) this;
        }

        public Criteria andSERIEIsNotNull() {
            addCriterion("SERIE is not null");
            return (Criteria) this;
        }

        public Criteria andSERIEEqualTo(String value) {
            addCriterion("SERIE =", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIENotEqualTo(String value) {
            addCriterion("SERIE <>", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIEGreaterThan(String value) {
            addCriterion("SERIE >", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIEGreaterThanOrEqualTo(String value) {
            addCriterion("SERIE >=", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIELessThan(String value) {
            addCriterion("SERIE <", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIELessThanOrEqualTo(String value) {
            addCriterion("SERIE <=", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIELike(String value) {
            addCriterion("SERIE like", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIENotLike(String value) {
            addCriterion("SERIE not like", value, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIEIn(List<String> values) {
            addCriterion("SERIE in", values, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIENotIn(List<String> values) {
            addCriterion("SERIE not in", values, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIEBetween(String value1, String value2) {
            addCriterion("SERIE between", value1, value2, "SERIE");
            return (Criteria) this;
        }

        public Criteria andSERIENotBetween(String value1, String value2) {
            addCriterion("SERIE not between", value1, value2, "SERIE");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCIsNull() {
            addCriterion("TIPO_DOC is null");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCIsNotNull() {
            addCriterion("TIPO_DOC is not null");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCEqualTo(String value) {
            addCriterion("TIPO_DOC =", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCNotEqualTo(String value) {
            addCriterion("TIPO_DOC <>", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCGreaterThan(String value) {
            addCriterion("TIPO_DOC >", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCGreaterThanOrEqualTo(String value) {
            addCriterion("TIPO_DOC >=", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCLessThan(String value) {
            addCriterion("TIPO_DOC <", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCLessThanOrEqualTo(String value) {
            addCriterion("TIPO_DOC <=", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCLike(String value) {
            addCriterion("TIPO_DOC like", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCNotLike(String value) {
            addCriterion("TIPO_DOC not like", value, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCIn(List<String> values) {
            addCriterion("TIPO_DOC in", values, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCNotIn(List<String> values) {
            addCriterion("TIPO_DOC not in", values, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCBetween(String value1, String value2) {
            addCriterion("TIPO_DOC between", value1, value2, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andTIPO_DOCNotBetween(String value1, String value2) {
            addCriterion("TIPO_DOC not between", value1, value2, "TIPO_DOC");
            return (Criteria) this;
        }

        public Criteria andVERSIONIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVERSIONIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVERSIONEqualTo(Double value) {
            addCriterion("VERSION =", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONNotEqualTo(Double value) {
            addCriterion("VERSION <>", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONGreaterThan(Double value) {
            addCriterion("VERSION >", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONGreaterThanOrEqualTo(Double value) {
            addCriterion("VERSION >=", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONLessThan(Double value) {
            addCriterion("VERSION <", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONLessThanOrEqualTo(Double value) {
            addCriterion("VERSION <=", value, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONIn(List<Double> values) {
            addCriterion("VERSION in", values, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONNotIn(List<Double> values) {
            addCriterion("VERSION not in", values, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONBetween(Double value1, Double value2) {
            addCriterion("VERSION between", value1, value2, "VERSION");
            return (Criteria) this;
        }

        public Criteria andVERSIONNotBetween(Double value1, Double value2) {
            addCriterion("VERSION not between", value1, value2, "VERSION");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDIsNull() {
            addCriterion("EMPRESA_ID is null");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDIsNotNull() {
            addCriterion("EMPRESA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDEqualTo(Long value) {
            addCriterion("EMPRESA_ID =", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDNotEqualTo(Long value) {
            addCriterion("EMPRESA_ID <>", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDGreaterThan(Long value) {
            addCriterion("EMPRESA_ID >", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDGreaterThanOrEqualTo(Long value) {
            addCriterion("EMPRESA_ID >=", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDLessThan(Long value) {
            addCriterion("EMPRESA_ID <", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDLessThanOrEqualTo(Long value) {
            addCriterion("EMPRESA_ID <=", value, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDIn(List<Long> values) {
            addCriterion("EMPRESA_ID in", values, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDNotIn(List<Long> values) {
            addCriterion("EMPRESA_ID not in", values, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDBetween(Long value1, Long value2) {
            addCriterion("EMPRESA_ID between", value1, value2, "EMPRESA_ID");
            return (Criteria) this;
        }

        public Criteria andEMPRESA_IDNotBetween(Long value1, Long value2) {
            addCriterion("EMPRESA_ID not between", value1, value2, "EMPRESA_ID");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated do_not_delete_during_merge Mon Jun 15 01:02:36 CDT 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table M_INTERACCIONES.M_PLANTILLA
     *
     * @mbggenerated Mon Jun 15 01:02:36 CDT 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}