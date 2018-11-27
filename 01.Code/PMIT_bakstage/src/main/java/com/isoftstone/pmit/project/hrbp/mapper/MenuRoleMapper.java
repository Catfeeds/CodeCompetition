package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author lf
 */
@Mapper
public interface MenuRoleMapper {

    /**
     * 根据角色id删除已有菜单
     * @param roleId
     */
    void deleteMenuByRoleId(Long roleId);

    /**
     *根据角色id添加选择菜单
     * @param roleId
     * @param mids
     * @return
     */
    int addMenu(Long roleId, Long[] mids);

}
