package com.isoftstone.pmit.project.hrbp.service.serviceimpl;


import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.RoleMenuMapper;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author lf
 */
@Service
@Transactional
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void updateSystemRole(SysRole sysRole, List<MenuInfo> menuInfoList) {
        Integer roleId = sysRole.getRoleId();
        roleMenuMapper.deleteMenuByRid(roleId);
        sysRoleMapper.updateSystemRole(sysRole);
        for (MenuInfo menuInfo : menuInfoList) {
            Integer menuId = menuInfo.getMenuId();
            roleMenuMapper.addMenu(roleId, menuId);
        }
    }

    @Override
    public void deleteSystemRole(Integer roleId) {
        roleMenuMapper.deleteMenuByRid(roleId);
        roleMenuMapper.deleteRole(roleId);
    }

    @Override
    public void addRole(SysRole role, List<MenuInfo> menuInfoList) {
        sysRoleMapper.insertSystemRole(role);
        for (MenuInfo menuInfo : menuInfoList) {
            Integer menuId = menuInfo.getMenuId();
            roleMenuMapper.addMenu(role.getRoleId(), menuId);
        }
    }
}
