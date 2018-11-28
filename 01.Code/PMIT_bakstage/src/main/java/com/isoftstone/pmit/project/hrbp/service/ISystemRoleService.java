package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
public interface ISystemRoleService {
    /**
     * 新增角色信息
     * @param sysRole
     */
    void insertSystemRole(SysRole sysRole);

    /**
     * 删除角色信息
     * @param str
     */
    void deleteSystemRole(String str);

    /**
     * 更新角色信息
     * @param sysRole
     */
    void updateSystemRole(SysRole sysRole);

    /**
     * 条件查询角色
     * @param sysRoleParam
     * @return
     */
    List<SysRole> getAllRoles(Map<String,String> sysRoleParam);

    /**
     * 根据用户获取角色列表
     * @param employeeID
     * @return
     */
    SysRole getRolesByEmployeeID(String employeeID);
}
