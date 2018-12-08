package com.isoftstone.pmit.project.hrbp.entity;


public class ScoreTransaction {

    /**软通工号*/
    private String employeeID;
    /**事务名*/
    private String transactionName;
    /**事务维度*/
    private String dimensionName;
    /**维度分数*/
    private String dimensionScore;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionScore() {
        return dimensionScore;
    }

    public void setDimensionScore(String dimensionScore) {
        this.dimensionScore = dimensionScore;
    }
}
