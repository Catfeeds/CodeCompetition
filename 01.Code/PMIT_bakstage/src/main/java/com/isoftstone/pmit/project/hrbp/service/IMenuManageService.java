package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;

public interface IMenuManageService {
	
	List<MenuInfo> getMenuListByRoleId(String roleId);
	
}
