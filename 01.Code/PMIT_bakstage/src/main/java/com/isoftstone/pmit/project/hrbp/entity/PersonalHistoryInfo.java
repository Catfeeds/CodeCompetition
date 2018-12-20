package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

/**
 * @author lf
 */
public class PersonalHistoryInfo {
   /** 事务Id */
   private String affairId;
   private String affairName;
   private String evaluatorId;
   private String series;
   private String du;
   private Boolean sumScore;
   private List<PersonalAffairdimension> PersonalAffairdimensionList;

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

    public Boolean getSumScore() {
        return sumScore;
    }

    public void setSumScore(Boolean sumScore) {
        this.sumScore = sumScore;
    }

    public List<PersonalAffairdimension> getPersonalAffairdimensionList() {
        return PersonalAffairdimensionList;
    }

    public void setPersonalAffairdimensionList(List<PersonalAffairdimension> personalAffairdimensionList) {
        PersonalAffairdimensionList = personalAffairdimensionList;
    }
}
