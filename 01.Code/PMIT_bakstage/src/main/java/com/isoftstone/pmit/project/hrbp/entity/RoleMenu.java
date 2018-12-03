package com.isoftstone.pmit.project.hrbp.entity;

import com.isoftstone.pmit.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lf
 */
public class RoleMenu {
    private SysRole sysRole;
    private List<MenuInfo> menuInfoList;

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<MenuInfo> getMenuInfoList() {
        return menuInfoList;
    }

    public void setMenuInfoList(List<MenuInfo> menuInfoList) {
        this.menuInfoList = menuInfoList;
    }

}
