package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.AuthService;
import com.isoftstone.pmit.system.menu.entity.Menu;
import com.isoftstone.pmit.system.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getRolesByEmployeeID(String employeeID) {
        return sysRoleMapper.getRolesByEmployeeID(employeeID);
    }

    @Override
    public List<Menu> findMenusBySysRoleId(Integer roleId) {
        return menuMapper.findMenusBySysRoleId(roleId);
    }

}