package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lf
 */

@Service
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getRoleByRoleId(Integer roleId) {
        return sysRoleMapper.getRoleByRoleId(roleId);
    }

    @Override
    public SysRole getRolesByEmployeeID(String employeeID) {
        return sysRoleMapper.getRolesByEmployeeID(employeeID);
    }

    @Override
    public List<SysRole> queryAllRoles() {
        return sysRoleMapper.queryAllRoles();
    }

}
