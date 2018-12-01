package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;

import java.util.List;

/**
 * @author lf
 */
public interface ISystemRoleService {

    /**
     * 根据用户获取角色列表
     *
     * @param employeeID
     * @return
     */
    SysRole getRolesByEmployeeID(String employeeID);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<SysRole> queryAllRoles();
    
}
