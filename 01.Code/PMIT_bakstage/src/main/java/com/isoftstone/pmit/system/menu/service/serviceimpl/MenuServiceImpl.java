package com.isoftstone.pmit.system.menu.service.serviceimpl;

import com.isoftstone.pmit.system.menu.entity.Menu;
import com.isoftstone.pmit.system.menu.mapper.MenuMapper;
import com.isoftstone.pmit.system.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yihur
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuResultListByMenuId(String menuId) {
        return menuMapper.getMenuResultListByMenuId(menuId);
    }
}
