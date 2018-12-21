package com.isoftstone.pmit.project.hrbp.entity;

/**
 * @author lf
 */
public class PersonalAffairdimension {
    private String employeeId;
    private Integer transactionDimensionId;
    private int dimensionId;
    private String dimensionName;
    private Double score;
    private String evaluation;
    private String explanation;

    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTransactionDimensionId() {
        return transactionDimensionId;
    }

    public void setTransactionDimensionId(Integer transactionDimensionId) {
        this.transactionDimensionId = transactionDimensionId;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
