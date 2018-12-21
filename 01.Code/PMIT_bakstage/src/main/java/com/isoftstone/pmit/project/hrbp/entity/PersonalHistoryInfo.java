package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;
import java.util.List;


/**
 * @author lf
 */
public class PersonalHistoryInfo {
    /** 事务Id */
    private String staffId;
    private String staffName;
    private String affairId;
    private String affairName;
    private String evaluatorId;
    private String series;
    private String du;
    private Double sumScore;
    private Date ChangeTime;
    private List<PersonalAffairdimension> PersonalAffairdimensionList;


    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getChangeTime() {
        return ChangeTime;
    }

    public void setChangeTime(Date changeTime) {
        ChangeTime = changeTime;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAffairId() {
        return affairId;
    }

    public void setAffairId(String affairId) {
        this.affairId = affairId;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }

    public String getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(String evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }

    public List<PersonalAffairdimension> getPersonalAffairdimensionList() {
        return PersonalAffairdimensionList;
    }

    public void setPersonalAffairdimensionList(List<PersonalAffairdimension> personalAffairdimensionList) {
        PersonalAffairdimensionList = personalAffairdimensionList;
    }
}
