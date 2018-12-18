package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;
import java.util.List;

public class PersonalScore {
    /**员工ID*/
    private String employeeID;
    /**员工姓名*/
    private String employeeName;
    /**所属产品线*/
    private String bu;
    /**成本中心*/
    private String costCenter;
    /**类型(培训成绩/考试成绩/评价成绩)*/
    private String types;
    /**得分时间*/
    private Date scoreTime;
    /**名称(培训名称/评价名称)*/
    private String affairName;
    /**所属系列*/
    private String series;
    /**成绩*/
    private float score;
    /**最后修改人*/
    private String modifier;
    /**唯一的ID*/
    private int scoresID;
    /**所属类型的名称的ID*/
    private int nameID;

    private List<PersonalTranAndDimeScore> personalTranAndDimeScores;

    public List<PersonalTranAndDimeScore> getPersonalTranAndDimeScores() {
        return personalTranAndDimeScores;
    }

    public void setPersonalTranAndDimeScores(List<PersonalTranAndDimeScore> personalTranAndDimeScores) {
        this.personalTranAndDimeScores = personalTranAndDimeScores;
    }

    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
    }

    public int getScoresID() {
        return scoresID;
    }

    public void setScoresID(int scoresID) {
        this.scoresID = scoresID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
