package com.isoftstone.pmit.project.hrbp.entity;


public class ScoreCourse {

    /**软通工号*/
    private String employeeID;
    /**开班ID*/
    private String openingID;
    /**类型*/
    private String type;
    /**课程成绩*/
    private float courseScore;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getOpeningID() {
        return openingID;
    }

    public void setOpeningID(String openingID) {
        this.openingID = openingID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(float courseScore) {
        this.courseScore = courseScore;
    }
}
