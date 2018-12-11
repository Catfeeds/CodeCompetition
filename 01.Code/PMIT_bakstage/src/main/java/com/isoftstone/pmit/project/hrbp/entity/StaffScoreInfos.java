package com.isoftstone.pmit.project.hrbp.entity;


import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public class StaffScoreInfos {
    private PageInfo<Map<String, Object>> allCourseScoreList;
    private List<Train> columnNameMap;
    private int size;



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

    public PageInfo<Map<String, Object>> getAllCourseScoreList() {
        return allCourseScoreList;
    }

    public void setAllCourseScoreList(PageInfo<Map<String, Object>> allCourseScoreList) {
        this.allCourseScoreList = allCourseScoreList;
    }
}

