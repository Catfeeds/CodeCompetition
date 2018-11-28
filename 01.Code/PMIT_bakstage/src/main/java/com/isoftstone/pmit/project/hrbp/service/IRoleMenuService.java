package com.isoftstone.pmit.project.hrbp.service;

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
}
