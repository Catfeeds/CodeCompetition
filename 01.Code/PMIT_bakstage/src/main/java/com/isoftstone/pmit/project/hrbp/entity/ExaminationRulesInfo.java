package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

public class ExaminationRulesInfo {
    // 规则Id
    private int ruleId;
    // 角色Id
    private int roleId;
    //角色名称
    private String roleName;
    // 体系
    private String system;
    // 级别
    private String roleLevel;
    // 创建者工号
    private String creatorId;
    // 创建者姓名
    private String creatorName;
    // 创建时间
    private Date createTime;
    // 更新人员工号
    private String updateStaffId;
    // 更新人员姓名
    private String updateStaffName;
    // 更新时间
    private Date updateTime;
    // 及格分数
    private int passScore;
    // 及格规则 0:> 1:≥ 2:< 3:≤
    private int passRule;
    // 卓越分数
    private int excellentScore;
    // 卓越规则 0:> 1:≥ 2:< 3:≤
    private int excellentRule;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId;
    }

    public String getUpdateStaffName() {
        return updateStaffName;
    }

    public void setUpdateStaffName(String updateStaffName) {
        this.updateStaffName = updateStaffName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPassScore() {
        return passScore;
    }

    public void setPassScore(int passScore) {
        this.passScore = passScore;
    }

    public int getPassRule() {
        return passRule;
    }

    public void setPassRule(int passRule) {
        this.passRule = passRule;
    }

    public int getExcellentScore() {
        return excellentScore;
    }

    public void setExcellentScore(int excellentScore) {
        this.excellentScore = excellentScore;
    }

    public int getExcellentRule() {
        return excellentRule;
    }

    public void setExcellentRule(int excellentRule) {
        this.excellentRule = excellentRule;
    }
}
