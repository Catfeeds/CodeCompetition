package com.isoftstone.pmit.project.hrbp.entity;

/**
 *  事务设置维度类
 * @author lf
 */
public class ExaminationDimension {
    /** 维度ID */
    private Integer dimensionID;
    /** 维度名称 */
    private String dimensionName;
    /** 分数 */
    private Double mark;
    /** 考核说明 */
    private String explanation;

    public Integer getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(Integer dimensionID) {
        this.dimensionID = dimensionID;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
