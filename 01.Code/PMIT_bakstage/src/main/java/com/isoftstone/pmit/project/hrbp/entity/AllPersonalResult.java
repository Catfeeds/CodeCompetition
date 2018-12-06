package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class AllPersonalResult {
    private List<BaseStaffInfo> baseStaffInfos;
    private int listSize;

    public List<BaseStaffInfo> getBaseStaffInfos() {
        return baseStaffInfos;
    }

    public void setBaseStaffInfos(List<BaseStaffInfo> baseStaffInfos) {
        this.baseStaffInfos = baseStaffInfos;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
}
