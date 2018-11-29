package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.mapper.MenuManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IMenuManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuManageServiceImpl implements IMenuManageService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuManageMapper menuManageMapper;

    @Override
    public List<MenuInfo> getMenus() {
        return menuManageMapper.getMenus();
    }

    @Override
    public List<MenuInfo> getMenuListByRoleId(Integer roleId) {
        return menuManageMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<MenuInfo> getMenuTreeByEmpID(String employeeId) {
        return menuManageMapper.getMenuTreeByEmpID(employeeId);
    }

}
