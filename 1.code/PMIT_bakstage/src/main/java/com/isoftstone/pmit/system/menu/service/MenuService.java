package com.isoftstone.pmit.system.menu.service;

import com.isoftstone.pmit.system.menu.entity.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根据菜单查询左树
     * @param menuId 菜单ID
     * @return
     */
    List<Menu> getMenuResultListByMenuId(String menuId);
}
