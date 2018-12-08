package com.isoftstone.pmit.project.hrbp.entity;


public class ScoreCourse {

    /**软通工号*/
    private String employeeID;
    /**课程维度*/
    private String courseDimension;
    /**课程名*/
    private String courseName;
    /**课程成绩*/
    private String courseScore;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getCourseDimension() {
        return courseDimension;
    }

    public void setCourseDimension(String courseDimension) {
        this.courseDimension = courseDimension;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(String courseScore) {
        this.courseScore = courseScore;
    }
}
