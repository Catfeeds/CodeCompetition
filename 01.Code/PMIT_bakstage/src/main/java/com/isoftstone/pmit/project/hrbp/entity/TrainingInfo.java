package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

/**
 * 培训列表展示实体类
 * 
 */
public class TrainingInfo {
	
	/** 培训ID  数据库自增主键  新增培训的时候无此参数 */
	private Integer trainingId;
	
	/** 培训名称  */
	private String trainingName;
	
	/** 所属系列  */
	private String series;
	
	/** 所属类别  */
	private String sort;
	
	/** 所属培训类型  */
	private String type;
	
	/** 所属产品线  */
	private String bu;
	
	/** 创建人工号  */
	private String creatorId;
	
	/** 创建人名字  */
	private String creatorName;
	
	/** 创建时间  */
	private Date createTime;
	
	/** 最后更新人工号  */
	private String updaterId;
	
	/** 最后更新人名称  */
	private String updateName;
	
	/** 培训简介 */
	private String trainingDes;
	
	/** 培训时长  */
	private String trainingDuration;
	
	public String getTrainingDuration() {
		return trainingDuration;
	}
	public void setTrainingDuration(String trainingDuration) {
		this.trainingDuration = trainingDuration;
	}
	
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getTrainingDes() {
		return trainingDes;
	}
	public void setTrainingDes(String trainingDes) {
		this.trainingDes = trainingDes;
	}
	
}
