package cn.workcenter.kpi.common;

public enum KpiOperatorEnum {
	
	write(1, "起草"),
	verify(2, "审批"),
	selfrating(3, "自评"),
	evaluation(4, "审评"),
	
	view(6, "查看"),
	;
	KpiOperatorEnum(int assessStatus, String operator) {
		this.assessStatus = assessStatus;
		this.operator = operator;
	}
	
	private int assessStatus;
	private String operator;
	public int getAssessStatus() {
		return assessStatus;
	}
	public void setAssessStatus(int assessStatus) {
		this.assessStatus = assessStatus;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public static KpiOperatorEnum getByAssessStatus(Integer assessStatus) {
		switch(assessStatus){
		case 1:
			return KpiOperatorEnum.valueOf("write");
		case 2:
			return KpiOperatorEnum.valueOf("verify");
		case 3:
			return KpiOperatorEnum.valueOf("selfrating");
		case 4:
			return KpiOperatorEnum.valueOf("evaluation");
			
		case 6:
			return KpiOperatorEnum.valueOf("view");
		}
		return null;
	}
}
