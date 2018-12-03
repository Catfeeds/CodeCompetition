package com.isoftstone.pmit.common.model;

public class TrainingParam extends CommonParam {
	
	/** 所属体系 */
	private String sort;
	
	/** 课程类型 */
	private String classType;
	
	/** 培训类型 */
	private String type;
	
	/** 培训名称 */
	private String trainingName;
	
	/** 所属系列 */
	private String series;
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
