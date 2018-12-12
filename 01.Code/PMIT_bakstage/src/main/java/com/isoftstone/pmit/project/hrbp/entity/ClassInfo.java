package com.isoftstone.pmit.project.hrbp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 开课模型
 * @author lf
 */
public class ClassInfo {
    /** 开班id */
    private int openingID;
    /** 所属体系 */
    private String system;
    /** 所属产品线 */
    private String bu;
    /** 所属系列 */
    private String series;
    /** 培训名称 */
    private String trainingName;
    /** 开班系列名称 */
    private String openingName;
    /** 讲师 */
    private String lecturer;
    /** 培训时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date trainingTime;
    /** 所属类别,公开课/定制/受限 */
    private String trainingType;
    /** 语言,1为中文,0为英文 */
    private int language;
    /** 开班形式,面授/在线 */
    private String classType;
    /** 开班地点 */
    private String address;
    /** 课件下载地址 */
    private String download;
    /** 在线培训地址 */
    private String online;
    /** 可容纳人数 */
    private int galleryful;
    /** 是否考试,1为需要,0为不需要 */
    private int isExam;
    /** 发布人 */
    private String publisher;
    /** 咨询电话 */
    private String telephone;
    /** 报名方式,1为线上,0为线下 */
    private int enrollMethod;
    /** 报名须知 */
    private String notice;
    /** 班级状态,1为可报名,2为已报满,3已结束 */
    private Integer openingStatus;

    public int getOpeningID() {
        return openingID;
    }

    public void setOpeningID(int openingID) {
        this.openingID = openingID;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public String getOpeningName() {
        return openingName;
    }

    public void setOpeningName(String openingName) {
        this.openingName = openingName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(Date trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public int getGalleryful() {
        return galleryful;
    }

    public void setGalleryful(int galleryful) {
        this.galleryful = galleryful;
    }

    public int getIsExam() {
        return isExam;
    }

    public void setIsExam(int isExam) {
        this.isExam = isExam;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getEnrollMethod() {
        return enrollMethod;
    }

    public void setEnrollMethod(int enrollMethod) {
        this.enrollMethod = enrollMethod;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(Integer openingStatus) {
        this.openingStatus = openingStatus;
    }

}
