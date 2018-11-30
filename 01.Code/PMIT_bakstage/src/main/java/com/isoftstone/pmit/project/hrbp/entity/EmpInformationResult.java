package com.isoftstone.pmit.project.hrbp.entity;

/**
 * @author lf
 */
public class EmpInformationResult {
    /** 用户名 */
    private String employeeName;
    /** 登录账号 */
    private String employeeID;
    /** pdu部门 */
    private String pdu;
    /** 岗位 */
    private String positionRole;
    /** 系统角色 */
    private String roleName;

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

    public String getPdu() {
        return pdu;
    }

    public void setPdu(String pdu) {
        this.pdu = pdu;
    }

    public String getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(String positionRole) {
        this.positionRole = positionRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
