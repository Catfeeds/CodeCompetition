package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.RoleMenuMapper;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public int updateSystemRole(Integer roleId, Integer[] menuIds) {
        roleMenuMapper.deleteMenuByRid(roleId);
        if (menuIds.length == 0) {
            return 0;
        }
        return roleMenuMapper.addMenu(roleId, menuIds);
    }

    @Override
    public void deleteSystemRole(Integer roleId) {
        roleMenuMapper.deleteMenuByRid(roleId);
        roleMenuMapper.deleteRole(roleId);
    }

    @Override
    public void addRole(SysRole role, Integer[] menuIds) {
        sysRoleMapper.insertSystemRole(role);
        int roleId=role.getRoleId();
        roleMenuMapper.addMenu(roleId, menuIds);
    }
}
