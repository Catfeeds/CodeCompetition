package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;

import java.util.List;

/**
 * @author lf
 */
public interface IRoleMenuService {
    /**
     * 更新角色菜单
     * @param sysRole
     * @param menuInfoList
     * @return
     */
    void updateSystemRole(SysRole sysRole, List<MenuInfo> menuInfoList );

    /**
     * 删除角色菜单
     * @param roleId
     */
    void deleteSystemRole(Integer roleId);

    /**
     * 添加角色并授权
     * @param role
     * @param menuInfoList
     * @return
     */
    void addRole(SysRole role, List<MenuInfo> menuInfoList);
}
