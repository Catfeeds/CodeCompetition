package com.isoftstone.pmit.project.hrbp.entity;


public class ScoreCourse {

    /**软通工号*/
    private String employee_id;
    /**开班ID*/
    private String opening_id;
    /**类型*/
    private String type;
    /**课程成绩*/
    private float test_score;
    /**当前用户*/
    private String modifier;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getOpening_id() {
        return opening_id;
    }

    public void setOpening_id(String opening_id) {
        this.opening_id = opening_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTest_score() {
        return test_score;
    }

    public void setTest_score(float test_score) {
        this.test_score = test_score;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
