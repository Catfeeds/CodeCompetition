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

    public static void main(String[] args) {
        RoleMenu roleMenu = new RoleMenu();
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(5);
        sysRole.setRoleName("xiaoli");
        sysRole.setDescription("jiadi");
        roleMenu.setSysRole(sysRole);
        MenuInfo menuInfo1 = new MenuInfo();
        menuInfo1.setMenuId(1);
        menuInfo1.setMenuName("buzhiming");
        System.out.println(JsonUtils.toJSON(menuInfo1));
        MenuInfo menuInfo2 = new MenuInfo();
        menuInfo2.setMenuId(2);
        List<MenuInfo> ll = new ArrayList<MenuInfo>();
        ll.add(menuInfo1);
        ll.add(menuInfo2);
        roleMenu.setMenuInfoList(ll);
        System.out.println(JsonUtils.toJSON(roleMenu));
    }

}
