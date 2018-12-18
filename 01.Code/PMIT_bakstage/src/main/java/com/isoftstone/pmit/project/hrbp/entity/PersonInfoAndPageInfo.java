package com.isoftstone.pmit.project.hrbp.entity;

public class PersonInfoAndPageInfo {
    private PageParam pageInfo;
    private String bu;
    private String pdu;
    private String du;
    private String employeeID;
    private String employeeName;

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getPdu() {
        return pdu;
    }

    public void setPdu(String pdu) {
        this.pdu = pdu;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
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

    public PageParam getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageParam pageInfo) {
        this.pageInfo = pageInfo;
    }

}
