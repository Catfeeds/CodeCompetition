package com.isoftstone.pmit.system.menu.mapper;

import com.isoftstone.pmit.system.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    /**
     * 根据菜单查询左树
     *
     * @param menuId 菜单ID
     * @return
     */
    List<Menu> getMenuResultListByMenuId(String menuId);

    /**
     * 根据角色查询菜单权限
     *
     * @param roleId
     * @return
     */
    List<Menu> findMenusBySysRoleId(Integer roleId);

    /**
     * 获取权限
     *
     * @param id
     * @return
     */
    Menu getMenu(int id);
}
