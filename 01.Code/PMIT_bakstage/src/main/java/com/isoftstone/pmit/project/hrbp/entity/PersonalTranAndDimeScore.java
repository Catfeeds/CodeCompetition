package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

public class PersonalTranAndDimeScore {
    private int tranAndDieId;
    private String employeeID;
    private String dimensionName;
    private int affairId;
    private float score;
    private String evaluation;
    private String evaluator;
    private String modifier;
    private Date changeTime;
    private String types;

    public int getAffairId() {
        return affairId;
    }

    public void setAffairId(int affairId) {
        this.affairId = affairId;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public int getTranAndDieId() {
        return tranAndDieId;
    }

    public void setTranAndDieId(int tranAndDieId) {
        this.tranAndDieId = tranAndDieId;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
