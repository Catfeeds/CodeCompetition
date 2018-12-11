package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

public class DataBackUpInfo extends PageParam{

    /**
     * 主键自增id
     */
    private int backupId;
    /**
     * 员工工号
     */
    private String employeeId;
    /**
     * 员工名称
     */
    private String employeeName;
    /**
     * 备份时间
     */
    private Date backupTime;
    /**
     * 备份名称
     */
    private String dataName;
    /**
     * 备份原因
     */
    private String reasonRemark;



    public int getBackupId() {
        return backupId;
    }

    public void setBackupId(int backupId) {
        this.backupId = backupId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getReasonRemark() {
        return reasonRemark;
    }

    public void setReasonRemark(String reasonRemark) {
        this.reasonRemark = reasonRemark;
    }
}
