package com.isoftstone.pmit.project.hrbp.common;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.entity.SysStaffbaseinfo;
import com.isoftstone.pmit.project.hrbp.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyRealm extends AuthorizingRealm {
    private Logger log = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private LoginService loginService;

    /**
     * 为当前subject授权
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Map<String, Object> params = new HashMap<>();
        params.put("staffisstAccount", (String) super.getAvailablePrincipal(principalCollection));
        List<SysRole> userRoleInfos = loginService.getUserRoleInfos(params);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(!userRoleInfos.isEmpty()) {
            for(SysRole role : userRoleInfos) {
                info.addRole(String.valueOf(role.getRoleId()));
            }
        }
        return info;
    }

    /**
     * 认证登陆subject身份
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        Map<String, Object> params = new HashMap<>();
        params.put("staffisstAccount", (String)authenticationToken.getPrincipal());
        List<SysStaffbaseinfo> userInfos = loginService.getUserInfos(params);
        if (userInfos.isEmpty()) {
            throw new UnknownAccountException();
        } else if(userInfos.size() > 1) {
            throw new DisabledAccountException();
        } else {
            SysStaffbaseinfo user = userInfos.get(0);
            // 校验密码
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), user.getPassword(), ByteSource.Util.bytes("2w@W"),  getName());
        }
    }

}
