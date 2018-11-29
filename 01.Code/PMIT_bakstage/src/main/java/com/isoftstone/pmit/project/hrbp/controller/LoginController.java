package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.project.hrbp.common.LoginInfo;
import com.isoftstone.pmit.project.hrbp.util.IConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/auth")
@Api(value = "登陆", tags = {"模块登陆页面"})
@CrossOrigin
public class LoginController {


    private Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login" )
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/loginIn", method = { RequestMethod.POST })
    @ApiOperation(value="登陆模块", notes="查询用户信息登陆")
    public LoginInfo loginIn(@RequestParam(value = "staffisstAccount", required = true) String staffisstAccount,
                            @RequestParam(value = "password", required = true) String password) {
        LoginInfo reJson = new LoginInfo();
//        Map paramMap = ParamUtils.handleServletParameter(request);
//        String staffisstAccount = MapUtils.getString(paramMap, "staffisstAccount");
//        String password = MapUtils.getString(paramMap, "password");
        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(staffisstAccount, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            reJson.setMessage("账户不存在");
            return reJson;
        } catch (DisabledAccountException e) {
            reJson.setMessage("账户存在问题");
            return reJson;
        } catch (AuthenticationException e) {
            reJson.setMessage("密码错误");
            return reJson;
        } catch (Exception e) {
            log.info("登陆异常", e);
            reJson.setMessage("登陆异常");
            return reJson;
        }
        reJson.setStatus(IConstants.RESULT_INT_SUCCESS);
        String res = subject.getPrincipals().toString();
        if (subject.hasRole("admin")) {
            res = res + "----------你拥有admin权限";
        }
        if (subject.hasRole("guest")) {
            res = res + "----------你拥有guest权限";
        }
        return reJson;
    }

}
