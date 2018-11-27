package com.isoftstone.pmit.project.hrbp.entity;


public class LoginInformation {
    /*登陆id*/
    private int loginID;
    /*软通工号*/
    private String employeeID;
    /*员工姓名*/
    private String employeeName;
    /*密码*/
    private String password;
    /*角色ID*/
    private String roleID;

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
