package com.isoftstone.pmit.project.hrbp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lf
 */
public class ExaminationInfo {
    /** 考试ID */
    private Integer examinationID;
    /** 所属系列 */
    private String series;
    /** 所属产品线 */
    private String bu;
    /** 课程类别,公开课/定制/受限 */
    private String trainingType;
    /** 培训名称 */
    private String trainingName;
    /** 语言,1为中文,0为英文 */
    private Integer language;
    /** 考试时间形式,时间点/时间段/截止日期/不限 */
    private String timeType;
    /** 考试开始时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date examinationStartTime;
    /** 考试结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date examinationEndTime;
    /** 考试类型,开卷/闭卷 */
    private String examinationType;
    /** 考试形式,在线/线下 */
    private String examinationStyle;
    /** 考试地址 */
    private String address;
    /** 合格标准 */
    private String standard;
    /** 发布人/接口人 */
    public String publisher;
    /** 考试须知 */
    public String notice;
    /** 考试状态,1为未开始,2为进行中,3已结束 */
    private Integer examinationStatus;

    public Integer getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(Integer examinationID) {
        this.examinationID = examinationID;
    }

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

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getExaminationStartTime() {
        return examinationStartTime;
    }

    public void setExaminationStartTime(Date examinationStartTime) {
        this.examinationStartTime = examinationStartTime;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getExaminationEndTime() {
        return examinationEndTime;
    }

    public void setExaminationEndTime(Date examinationEndTime) {
        this.examinationEndTime = examinationEndTime;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

    public String getExaminationStyle() {
        return examinationStyle;
    }

    public void setExaminationStyle(String examinationStyle) {
        this.examinationStyle = examinationStyle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(Integer examinationStatus) {
        this.examinationStatus = examinationStatus;
    }
}
