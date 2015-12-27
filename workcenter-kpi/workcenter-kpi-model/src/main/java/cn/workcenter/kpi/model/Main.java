package cn.workcenter.kpi.model;

import java.math.BigDecimal;

public class Main {
    private Long id;

    private Long processinstanceId;

    private Long rootTokenId;

    private Long waitAssessmentPersonId;
    
    private String waitAssessmentPersonName;

    private Long assessmentPersonId;
    
    private String assessmentPersonName;

    private String year;

    private String remark;

    /**
     * 0.发起
     * 1.起草
     * 2.审批
     * 3.自评
     * 4.审评
     * 5.完成
     */
    private Integer assessStatus;

    private String grade;

    private BigDecimal totalScore;

    private Integer status;

    /**
     * 1.起草
     * 2.审批
     * 3.自评
     * 4.审评
     * 5.完成
     * 6.查看
     */
    private String operator;
    /**
     * kpilist:enter
     * kpilist:view
     */
    private String method;
    
    private String taskinstanceId;
    
	public String getTaskinstanceId() {
		return taskinstanceId;
	}

	public void setTaskinstanceId(String taskinstanceId) {
		this.taskinstanceId = taskinstanceId;
	}

	public String getWaitAssessmentPersonName() {
		return waitAssessmentPersonName;
	}

	public void setWaitAssessmentPersonName(String waitAssessmentPersonName) {
		this.waitAssessmentPersonName = waitAssessmentPersonName;
	}

	public String getAssessmentPersonName() {
		return assessmentPersonName;
	}

	public void setAssessmentPersonName(String assessmentPersonName) {
		this.assessmentPersonName = assessmentPersonName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessinstanceId() {
        return processinstanceId;
    }

    public void setProcessinstanceId(Long processinstanceId) {
        this.processinstanceId = processinstanceId;
    }

    public Long getRootTokenId() {
        return rootTokenId;
    }

    public void setRootTokenId(Long rootTokenId) {
        this.rootTokenId = rootTokenId;
    }

    public Long getWaitAssessmentPersonId() {
        return waitAssessmentPersonId;
    }

    public void setWaitAssessmentPersonId(Long waitAssessmentPersonId) {
        this.waitAssessmentPersonId = waitAssessmentPersonId;
    }

    public Long getAssessmentPersonId() {
        return assessmentPersonId;
    }

    public void setAssessmentPersonId(Long assessmentPersonId) {
        this.assessmentPersonId = assessmentPersonId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAssessStatus() {
        return assessStatus;
    }

    public void setAssessStatus(Integer assessStatus) {
        this.assessStatus = assessStatus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}