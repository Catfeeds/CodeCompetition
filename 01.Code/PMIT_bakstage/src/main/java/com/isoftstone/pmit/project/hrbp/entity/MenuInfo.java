package com.isoftstone.pmit.project.hrbp.entity;

/**
 * 
 * 前端需要的菜单项参数
 * @author zhaoye
 *
 */
public class MenuInfo {
	
	/** 菜单ID */
	private Integer menuId;
	
	/** 菜单名称 */
	private String menuName;
	
	/** 描述、备注 */
	private String note;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
