package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;
import java.util.Map;

public class SystemSeriesInfo {
    // 自增主键Id
    private Map<Integer, String> idAndSeriesName;
    // 体系和系列名称，一个体系包含多个系列
    private Map<String, List<String>> systemAndSeriesName;

    public Map<Integer, String> getIdAndSeriesName() {
        return idAndSeriesName;
    }

    public void setIdAndSeriesName(Map<Integer, String> idAndSeriesName) {
        this.idAndSeriesName = idAndSeriesName;
    }

    public Map<String, List<String>> getSystemAndSeriesName() {
        return systemAndSeriesName;
    }

    public void setSystemAndSeriesName(Map<String, List<String>> systemAndSeriesName) {
        this.systemAndSeriesName = systemAndSeriesName;
    }
}
