package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.MenuManageMapper;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.IAuthService;
import com.isoftstone.pmit.system.menu.entity.Menu;
import com.isoftstone.pmit.system.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private MenuManageMapper menuManageMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getRolesByEmployeeID(String employeeID) {
        return sysRoleMapper.getRolesByEmployeeID(employeeID);
    }

    @Override
    public List<MenuInfo> getMenusBySysRoleId(Integer roleId) {
        return menuManageMapper.getMenuListByRoleId(roleId);
    }
}
