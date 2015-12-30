package cn.workcenter.model;

import java.util.ArrayList;
import java.util.List;

public class FlowNodeExample implements Cloneable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowNodeExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdIsNull() {
            addCriterion("processdefinition_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdIsNotNull() {
            addCriterion("processdefinition_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdEqualTo(Long value) {
            addCriterion("processdefinition_id =", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdNotEqualTo(Long value) {
            addCriterion("processdefinition_id <>", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdGreaterThan(Long value) {
            addCriterion("processdefinition_id >", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("processdefinition_id >=", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdLessThan(Long value) {
            addCriterion("processdefinition_id <", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdLessThanOrEqualTo(Long value) {
            addCriterion("processdefinition_id <=", value, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdIn(List<Long> values) {
            addCriterion("processdefinition_id in", values, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdNotIn(List<Long> values) {
            addCriterion("processdefinition_id not in", values, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdBetween(Long value1, Long value2) {
            addCriterion("processdefinition_id between", value1, value2, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andProcessdefinitionIdNotBetween(Long value1, Long value2) {
            addCriterion("processdefinition_id not between", value1, value2, "processdefinitionId");
            return (Criteria) this;
        }

        public Criteria andIndexNumIsNull() {
            addCriterion("index_num is null");
            return (Criteria) this;
        }

        public Criteria andIndexNumIsNotNull() {
            addCriterion("index_num is not null");
            return (Criteria) this;
        }

        public Criteria andIndexNumEqualTo(Integer value) {
            addCriterion("index_num =", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumNotEqualTo(Integer value) {
            addCriterion("index_num <>", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumGreaterThan(Integer value) {
            addCriterion("index_num >", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("index_num >=", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumLessThan(Integer value) {
            addCriterion("index_num <", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumLessThanOrEqualTo(Integer value) {
            addCriterion("index_num <=", value, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumIn(List<Integer> values) {
            addCriterion("index_num in", values, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumNotIn(List<Integer> values) {
            addCriterion("index_num not in", values, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumBetween(Integer value1, Integer value2) {
            addCriterion("index_num between", value1, value2, "indexNum");
            return (Criteria) this;
        }

        public Criteria andIndexNumNotBetween(Integer value1, Integer value2) {
            addCriterion("index_num not between", value1, value2, "indexNum");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameIsNull() {
            addCriterion("enter_class_name is null");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameIsNotNull() {
            addCriterion("enter_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameEqualTo(String value) {
            addCriterion("enter_class_name =", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameNotEqualTo(String value) {
            addCriterion("enter_class_name <>", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameGreaterThan(String value) {
            addCriterion("enter_class_name >", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("enter_class_name >=", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameLessThan(String value) {
            addCriterion("enter_class_name <", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameLessThanOrEqualTo(String value) {
            addCriterion("enter_class_name <=", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameLike(String value) {
            addCriterion("enter_class_name like", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameNotLike(String value) {
            addCriterion("enter_class_name not like", value, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameIn(List<String> values) {
            addCriterion("enter_class_name in", values, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameNotIn(List<String> values) {
            addCriterion("enter_class_name not in", values, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameBetween(String value1, String value2) {
            addCriterion("enter_class_name between", value1, value2, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andEnterClassNameNotBetween(String value1, String value2) {
            addCriterion("enter_class_name not between", value1, value2, "enterClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameIsNull() {
            addCriterion("leave_class_name is null");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameIsNotNull() {
            addCriterion("leave_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameEqualTo(String value) {
            addCriterion("leave_class_name =", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameNotEqualTo(String value) {
            addCriterion("leave_class_name <>", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameGreaterThan(String value) {
            addCriterion("leave_class_name >", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("leave_class_name >=", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameLessThan(String value) {
            addCriterion("leave_class_name <", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameLessThanOrEqualTo(String value) {
            addCriterion("leave_class_name <=", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameLike(String value) {
            addCriterion("leave_class_name like", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameNotLike(String value) {
            addCriterion("leave_class_name not like", value, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameIn(List<String> values) {
            addCriterion("leave_class_name in", values, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameNotIn(List<String> values) {
            addCriterion("leave_class_name not in", values, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameBetween(String value1, String value2) {
            addCriterion("leave_class_name between", value1, value2, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andLeaveClassNameNotBetween(String value1, String value2) {
            addCriterion("leave_class_name not between", value1, value2, "leaveClassName");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNull() {
            addCriterion("taskid is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskid is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(Long value) {
            addCriterion("taskid =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(Long value) {
            addCriterion("taskid <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(Long value) {
            addCriterion("taskid >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(Long value) {
            addCriterion("taskid >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(Long value) {
            addCriterion("taskid <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(Long value) {
            addCriterion("taskid <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<Long> values) {
            addCriterion("taskid in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<Long> values) {
            addCriterion("taskid not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(Long value1, Long value2) {
            addCriterion("taskid between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(Long value1, Long value2) {
            addCriterion("taskid not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdIsNull() {
            addCriterion("decision_delegation_id is null");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdIsNotNull() {
            addCriterion("decision_delegation_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdEqualTo(Long value) {
            addCriterion("decision_delegation_id =", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdNotEqualTo(Long value) {
            addCriterion("decision_delegation_id <>", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdGreaterThan(Long value) {
            addCriterion("decision_delegation_id >", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("decision_delegation_id >=", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdLessThan(Long value) {
            addCriterion("decision_delegation_id <", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdLessThanOrEqualTo(Long value) {
            addCriterion("decision_delegation_id <=", value, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdIn(List<Long> values) {
            addCriterion("decision_delegation_id in", values, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdNotIn(List<Long> values) {
            addCriterion("decision_delegation_id not in", values, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdBetween(Long value1, Long value2) {
            addCriterion("decision_delegation_id between", value1, value2, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionDelegationIdNotBetween(Long value1, Long value2) {
            addCriterion("decision_delegation_id not between", value1, value2, "decisionDelegationId");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionIsNull() {
            addCriterion("decision_expression is null");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionIsNotNull() {
            addCriterion("decision_expression is not null");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionEqualTo(String value) {
            addCriterion("decision_expression =", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionNotEqualTo(String value) {
            addCriterion("decision_expression <>", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionGreaterThan(String value) {
            addCriterion("decision_expression >", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("decision_expression >=", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionLessThan(String value) {
            addCriterion("decision_expression <", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionLessThanOrEqualTo(String value) {
            addCriterion("decision_expression <=", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionLike(String value) {
            addCriterion("decision_expression like", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionNotLike(String value) {
            addCriterion("decision_expression not like", value, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionIn(List<String> values) {
            addCriterion("decision_expression in", values, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionNotIn(List<String> values) {
            addCriterion("decision_expression not in", values, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionBetween(String value1, String value2) {
            addCriterion("decision_expression between", value1, value2, "decisionExpression");
            return (Criteria) this;
        }

        public Criteria andDecisionExpressionNotBetween(String value1, String value2) {
            addCriterion("decision_expression not between", value1, value2, "decisionExpression");
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