package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
public interface SystemRoleService {
    /**
     * 获取所有系统角色
     * @return
     */
    List<SysRole> selectAllSystemRole();

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
}
