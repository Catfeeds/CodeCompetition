package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lf
 */
@Service
@Transactional
public class MenuRoleService {
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public int updateMenuRole(Long roleId, Long[] mids) {
        menuRoleMapper.deleteMenuByRoleId(roleId);
        if (mids.length == 0) {
            return 0;
        }
        return menuRoleMapper.addMenu(roleId, mids);
    }
}
