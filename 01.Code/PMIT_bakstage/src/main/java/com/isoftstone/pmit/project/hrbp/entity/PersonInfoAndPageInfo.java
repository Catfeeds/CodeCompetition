package com.isoftstone.pmit.project.hrbp.entity;

public class PersonInfoAndPageInfo {
    private PageParam pageInfo;
    private BaseStaffInfo baseStaffInfo;

    public PageParam getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageParam pageInfo) {
        this.pageInfo = pageInfo;
    }

    public BaseStaffInfo getBaseStaffInfo() {
        return baseStaffInfo;
    }

    public void setBaseStaffInfo(BaseStaffInfo baseStaffInfo) {
        this.baseStaffInfo = baseStaffInfo;
    }
}
