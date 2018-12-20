package com.isoftstone.pmit.project.hrbp.entity;


public class ScoreTransaction {

    /**软通工号*/
    private String employeeID;
    /**事务ID*/
    private String transactionID;
    /**维度ID*/
    private String dimensionID;
    /**分数*/
    private float Score;
    /**导师ID*/
    private String tutorID;
    /**HeaderID*/
    private String headerID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String dimensionID) {
        this.dimensionID = dimensionID;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }
}
