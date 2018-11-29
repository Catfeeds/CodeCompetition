package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;

/**
 * @author lf
 */
public interface IRoleMenuService {
    /**
     * 更新角色菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    int updateSystemRole(Integer roleId, Integer[] menuIds);

    /**
     * 删除角色菜单
     * @param roleId
     */
    void deleteSystemRole(Integer roleId);

    /**
     * 添加角色并授权
     * @param role
     * @param menuIds
     * @return
     */
    void addRole(SysRole role, Integer[] menuIds);
}
