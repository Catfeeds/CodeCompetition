package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;

public interface IMenuManageService {
	
	List<MenuInfo> getMenuListByRoleId(Integer roleId);

	String saveMenuByRoleId(Integer roleId, List<MenuInfo> menuInfos);

	List<MenuInfo> getMenus();
}
