package cn.workcenter.kpi.model;

public class EnactmentSelfWithBLOBs extends EnactmentSelf {
    private String selfGoal;

    private String selfEvaluate;

    private String leaderEvaluation;

    public String getSelfGoal() {
        return selfGoal;
    }

    public void setSelfGoal(String selfGoal) {
        this.selfGoal = selfGoal;
    }

    public String getSelfEvaluate() {
        return selfEvaluate;
    }

    public void setSelfEvaluate(String selfEvaluate) {
        this.selfEvaluate = selfEvaluate;
    }

    public String getLeaderEvaluation() {
        return leaderEvaluation;
    }

    public void setLeaderEvaluation(String leaderEvaluation) {
        this.leaderEvaluation = leaderEvaluation;
    }

}