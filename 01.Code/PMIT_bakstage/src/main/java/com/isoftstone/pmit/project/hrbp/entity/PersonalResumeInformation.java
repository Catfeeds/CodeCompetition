package com.isoftstone.pmit.project.hrbp.entity;

public class PersonalResumeInformation {
    /** 项目组ID */
    private String projectTeamId;

    /** 项目组名称  */
    private String projectTeamName;

    /** 成员姓名  */
    private String employeeName;

    /** 成员ID  */
    private String employeeID;

    /** 担任角色  */
    private String position;

    /**入项时间*/
    private  String entryTime;

    /**出项时间*/
    private String outTime;

    public String getProjectTeamId() {
        return projectTeamId;
    }

    public void setProjectTeamId(String projectTeamId) {
        this.projectTeamId = projectTeamId;
    }

    public String getProjectTeamName() {
        return projectTeamName;
    }

    public void setProjectTeamName(String projectTeamName) {
        this.projectTeamName = projectTeamName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
