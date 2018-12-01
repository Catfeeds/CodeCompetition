package com.isoftstone.pmit.project.hrbp.entity;

public class TechnicalInformation {

    /*软通工号*/
    private String employeeID;
    /*技术平台*/
    private String technologyPlatform;
    /*技术等级*/
    private String technologyGrade;
    /*擅长领域*/
    private String areasOfExpertise;


    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getTechnologyPlatform() {
        return technologyPlatform;
    }

    public void setTechnologyPlatform(String technologyPlatform) {
        this.technologyPlatform = technologyPlatform;
    }

    public String getTechnologyGrade() {
        return technologyGrade;
    }

    public void setTechnologyGrade(String technologyGrade) {
        this.technologyGrade = technologyGrade;
    }

    public String getAreasOfExpertise() {
        return areasOfExpertise;
    }

    public void setAreasOfExpertise(String areasOfExpertise) {
        this.areasOfExpertise = areasOfExpertise;
    }
}
