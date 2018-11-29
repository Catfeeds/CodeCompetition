package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;

public interface IMenuManageService {

	List<MenuInfo> getMenus();

	List<MenuInfo> getMenuListByRoleId(Integer roleId);

	List<MenuInfo> getMenuTreeByEmpID(String employeeId);
}
