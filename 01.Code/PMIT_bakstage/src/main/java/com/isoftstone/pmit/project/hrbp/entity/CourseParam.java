package com.isoftstone.pmit.project.hrbp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author lf
 */
public class CourseParam {
    /** 所属产品线 */
    private String bu;
    /** 所属系列 */
    private String series;
    /** 培训名称 */
    private String trainingName;
    /** 所属类别,公开课/定制/受限 */
    private String trainingType;
    /** 讲师 */
    private String lecturer;
    /** 条件开始时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 条件结束时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /** 分页参数 */
    private PageParam pageInfo;

    public PageParam getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageParam pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
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

    public void setEndTime(Date endTIme) {
        this.endTime = endTIme;
    }
}




