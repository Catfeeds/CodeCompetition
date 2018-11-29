package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

public class SysStaffbaseinfo {
    private int staffISSTNum;
    private String hwNumber;
    private String staffName;
    private String staffisstAccount;
    private String staffState;
    private String createTime;
    private Date updateTime;
    private String intoDate;
    private String outDate;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getStaffISSTNum() {

        return staffISSTNum;
    }

    public String getHwNumber() {

        return hwNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffisstAccount() {
        return staffisstAccount;
    }

    public String getStaffState() {
        return staffState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getIntoDate() {
        return intoDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setStaffISSTNum(int staffISSTNum) {

        this.staffISSTNum = staffISSTNum;
    }

    public void setHwNumber(String hwNumber) {
        this.hwNumber = hwNumber;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStaffisstAccount(String staffisstAccount) {
        this.staffisstAccount = staffisstAccount;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIntoDate(String intoDate) {
        this.intoDate = intoDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return "SysStaffbaseinfo{" +
                "staffISSTNum=" + staffISSTNum +
                ", hwNumber='" + hwNumber + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffisstAccount='" + staffisstAccount + '\'' +
                ", staffState='" + staffState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                ", intoDate='" + intoDate + '\'' +
                ", outDate='" + outDate + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
