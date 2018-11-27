package com.isoftstone.pmit.project.hrbp.entity;

/**
 * 
 * 项目组人员配置信息
 * @author zhaoye
 *
 */
public class TeamInfo {
	
	/** 项目组ID */
	private String teamId;
	
	/** 项目组名称  */
	private String teamName;
	
	/** 成员姓名  */
	private String staffName;
	
	/** 成员ID  */
	private String staffId;
	
	/** 组内角色  */
	private String position;
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
