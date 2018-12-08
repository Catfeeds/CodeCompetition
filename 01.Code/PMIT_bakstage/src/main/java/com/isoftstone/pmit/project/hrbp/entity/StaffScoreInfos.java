package com.isoftstone.pmit.project.hrbp.entity;


import java.util.List;
import java.util.Map;

public class StaffScoreInfos {
    private List<Map<String, Object>> allCourseScoreList;
    private List<Train> columnNameMap;
    private int size;
    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Train> getColumnNameMap() {
        return columnNameMap;
    }

    public void setColumnNameMap(List<Train> columnNameMap) {
        this.columnNameMap = columnNameMap;
    }

    public List<Map<String, Object>> getAllCourseScoreList() {
        return allCourseScoreList;
    }

    public void setAllCourseScoreList(List<Map<String, Object>> allCourseScoreList) {
        this.allCourseScoreList = allCourseScoreList;
    }


}

