package com.isoftstone.pmit.project.hrbp.entity;


import java.util.Date;

public class ScoreTransaction {

    /**软通工号*/
    private String employee_id;
    /**事务ID*/
    private String affair_id;
    /**维度ID*/
    private String dimension_id;
    /**分数*/
    private float score;
    /**类型*/
    private String type;
    /**评价人ID*/
    private String evaluator;
    /**当前用户*/
    private String modifier;
    /**当前时间*/
    private String change_time;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getAffair_id() {
        return affair_id;
    }

    public void setAffair_id(String affair_id) {
        this.affair_id = affair_id;
    }

    public String getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(String dimension_id) {
        this.dimension_id = dimension_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }
}
