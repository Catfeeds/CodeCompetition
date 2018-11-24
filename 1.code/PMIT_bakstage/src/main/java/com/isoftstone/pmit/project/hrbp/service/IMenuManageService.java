package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;

import com.isoftstone.pmit.system.menu.entity.Menu;

public interface IMenuManageService {
	
	List<Menu> getMenuListByRoleId(String roleId);
	
}
