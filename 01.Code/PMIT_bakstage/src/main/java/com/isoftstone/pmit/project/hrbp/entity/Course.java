package com.isoftstone.pmit.project.hrbp.entity;

public class Course {
    private String courseName;
    private String courseID;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Course() {
    }

    public Course(String courseName, String courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
    }
}
