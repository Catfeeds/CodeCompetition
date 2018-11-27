package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.system.menu.entity.Menu;


import java.util.List;

public interface IAuthService {
	/**
	 * 根据用户获取角色列表
	 * @param employeeID
	 * @return
	 */
	List<SysRole> getRolesByEmployeeID(String employeeID);

	/**
	 * 根据角色id获取权限数据
	 * @param roleId
	 * @return
	 */
	List<Menu> findMenusBySysRoleId(Integer roleId);



}
