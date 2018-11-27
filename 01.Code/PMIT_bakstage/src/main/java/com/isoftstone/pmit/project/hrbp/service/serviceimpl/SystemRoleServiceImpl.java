package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.SysRoleMapper;
import com.isoftstone.pmit.project.hrbp.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */

@Service
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void insertSystemRole(SysRole sysRole) {
        sysRoleMapper.insertSystemRole(sysRole);
    }

    @Override
    public void deleteSystemRole(String str) {
        sysRoleMapper.deleteSystemRole(str);
    }

    @Override
    public void updateSystemRole(SysRole sysRole) {
        sysRoleMapper.updateSystemRole(sysRole);
    }

    @Override
    public List<SysRole> getAllRoles(Map<String, String> sysRoleParam) {
        return sysRoleMapper.getAllRoles(sysRoleParam);
    }

}
