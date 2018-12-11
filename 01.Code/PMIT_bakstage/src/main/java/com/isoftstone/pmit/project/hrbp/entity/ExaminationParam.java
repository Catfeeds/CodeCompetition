package com.isoftstone.pmit.project.hrbp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author lf
 */
public class ExaminationParam {

    /** 所属系列 */
    private String series;
    /** 所属产品线 */
    private String bu;
    /** 课程类别,公开课/定制/受限 */
    private String trainingType;
    /** 培训名称 */
    private String trainingName;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 条件结束时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /** 分页参数 */
    private PageParam pageInfo;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PageParam getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageParam pageInfo) {
        this.pageInfo = pageInfo;
    }
}
