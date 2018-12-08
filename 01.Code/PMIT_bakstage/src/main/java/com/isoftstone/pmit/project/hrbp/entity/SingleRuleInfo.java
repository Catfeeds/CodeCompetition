package com.isoftstone.pmit.project.hrbp.entity;

public class SingleRuleInfo {
    // 规则Id
    private int ruleId;
    // 规则项Id （可以是培训或者事务，但必须是表中对应的Id）
    private int itemId;
    // 规则项类型(0:培训;1:事务)
    private int itemType;
    // 规则项名称
    private String itemName;
    // 学分
    private int credit;
    // pass
    private int pass;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }
}