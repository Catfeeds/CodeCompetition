package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;
import java.util.List;

/**
 *  考核事务设置类
 * @author lf
 */
public class ExaminationAffair {
    /** 考核事务ID */
    private int affairID;
    /** 事务名称 */
    private String affairName;
    /** 所属体系 */
    private String system;
    /** 所属系列 */
    private String series;
    /** 所属产业线 */
    private String bu;
    /** 操作人ID */
    private String employeeID;
    /** 创建人 */
    private String createBy;
    /** 更新人 */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;
    /** 考核维度集合 */
    private List<ExaminationDimension>  examinationDimensionList;

    public List<ExaminationDimension> getExaminationDimensionList() {
        return examinationDimensionList;
    }

    public void setExaminationDimensionList(List<ExaminationDimension> examinationDimensionList) {
        this.examinationDimensionList = examinationDimensionList;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public int getAffairID() {
        return affairID;
    }

    public void setAffairID(int affairID) {
        this.affairID = affairID;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
