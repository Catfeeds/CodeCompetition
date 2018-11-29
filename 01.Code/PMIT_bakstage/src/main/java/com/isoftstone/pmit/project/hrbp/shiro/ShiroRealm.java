package com.isoftstone.pmit.project.hrbp.shiro;

import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.mapper.BaseStaffInfoMapper;
import com.isoftstone.pmit.project.hrbp.mapper.LoginMapper;
import com.isoftstone.pmit.project.hrbp.service.IAuthService;
import com.isoftstone.pmit.system.menu.entity.Menu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 认证授权的具体实现
 */
@Service
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    private IAuthService authService;

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 授予角色与权限
     *
     * @param principalCollection
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 添加权限 和 角色信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        // 获取当前登陆用户
//        Subject subject = SecurityUtils.getSubject();
//        BaseStaffInfo StaffInfo = (BaseStaffInfo) subject.getPrincipal();
//        //获取当前用户角色
//        List<SysRole> roles = this.authService.getRolesByEmployeeID(StaffInfo.getEmployeeID());
//        if (null != roles && roles.size() > 0) {
//            for (SysRole sysRole : roles) {
//                authorizationInfo.addRole(sysRole.getRoleKey());
//                //角色对应权限数据
//                List<MenuInfo> menus = this.authService.getMenusBySysRoleId(sysRole.getRoleId());
//                if (null != menus && menus.size() > 0) {
//                    for (MenuInfo menu : menus) {
//                        authorizationInfo.addStringPermission(menu.getMenuName());
//                    }
//                }
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 获取登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
//        //UsernamePasswordToken用于存放提交的登录信息
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        // 调用数据层
//        LoginInformation loginInformation = loginMapper.findStaffByEmployeeId(token.getUsername());
//        if (loginInformation == null) {
//            // 用户不存在
//            return null;
//        } else {
//            // 密码存在
//            return new SimpleAuthenticationInfo(loginInformation, loginInformation.getPassword(), getName());
//        }
        return null;
    }
}
