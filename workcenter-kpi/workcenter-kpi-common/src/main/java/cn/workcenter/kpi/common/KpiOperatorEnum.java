package cn.workcenter.kpi.common;

public enum KpiOperatorEnum {
	
	write(2, "起草"),
	verify(3, "审批"),
	selfrating(4, "自评"),
	evaluation(5, "审评"),
	
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

	public static String getOperatorByAssessStatus(Integer assessStatus) {
		switch(assessStatus){
		case 2:
			return "起草";
		case 3:
			return "审批";
		case 4:
			return "自评";
		case 5:
			return "审评";
			
		case 6:
			return "查看";
		}
		return "";
	}
	public static String getassessStatusByAssessStatus(Integer assessStatus) {
		switch(assessStatus){
		case 2:
			return "起草";
		case 3:
			return "审批";
		case 4:
			return "自评";
		case 5:
			return "审评";
			
		case 6:
			return "完成";
		}
		return "";
	}
}
