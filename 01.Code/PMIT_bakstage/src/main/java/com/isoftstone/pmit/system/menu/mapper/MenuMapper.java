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

}
