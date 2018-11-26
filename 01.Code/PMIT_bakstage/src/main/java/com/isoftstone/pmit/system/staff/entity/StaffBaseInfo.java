package com.isoftstone.pmit.system.staff.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yihur
 * @date 2018/11/2
 * 员工基本信息表
 */
public class StaffBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 员工工号 */
    private Integer staffISSTNum;

    /** 华为账号 */
    private String hwNumber;

    /** 员工姓名 */
    private String staffName;

    /** 员工域账号 */
    private String staffISSTAccount;

    /** 手机号码 */
    private String staffMobile;

    /** 状态（1在职，2转出，3,离职） */
    private Integer staffState;

    /** 更新时间 */
    private Date updateTime;

    /** 入项时间 */
    private String intoDate;

    /** 出项时间 */
    private String outDate;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStaffISSTNum() {
        return staffISSTNum;
    }

    public void setStaffISSTNum(Integer staffISSTNum) {
        this.staffISSTNum = staffISSTNum;
    }

    public String getHwNumber() {
        return hwNumber;
    }

    public void setHwNumber(String hwNumber) {
        this.hwNumber = hwNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffISSTAccount() {
        return staffISSTAccount;
    }

    public void setStaffISSTAccount(String staffISSTAccount) {
        this.staffISSTAccount = staffISSTAccount;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(String intoDate) {
        this.intoDate = intoDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
