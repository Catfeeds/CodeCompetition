package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
public interface ISystemRoleService {

    /**
     * 根据用户获取角色列表
     * @param employeeID
     * @return
     */
    SysRole getRolesByEmployeeID(String employeeID);

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> queryAllRoles();


    /**
     * 更新角色信息
     * @param sysRole
     */
    void updateSystemRole(SysRole sysRole);

    /**
     * 根据roleId获取角色
     * @param roleId
     */
    SysRole getRoleByRoleId(Integer roleId);
}
