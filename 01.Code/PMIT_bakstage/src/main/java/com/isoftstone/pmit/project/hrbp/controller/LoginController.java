package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInfo;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.service.ILoginService;
import com.isoftstone.pmit.project.hrbp.util.IConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/auth")
@Api(value = "登陆模块", tags = {"模块登陆页面"})
@CrossOrigin
public class LoginController extends AbstractController {

    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/loginIn" )
    public String login() {
        return "/loginIn";
    }

    @RequestMapping(value = "/logout" )
    public String loginOut() {
        return "/logout";
    }
    @RequestMapping(value = "/loginIn", method = { RequestMethod.POST })
    @ApiOperation(value="登陆模块", notes="查询用户信息登陆")
    public LoginInfo loginIn(@RequestBody String parameter ) {
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        String employeeID = loginInformation.getEmployeeID();
        String password = loginInformation.getPassword();
        LoginInfo reJson = new LoginInfo();
        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        //MD5Utils加密
        String md5HexPassword = DigestUtils.md5Hex(password);
        UsernamePasswordToken token = new UsernamePasswordToken(employeeID, md5HexPassword);
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
        reJson.setMessage("登录成功");
        String res = subject.getPrincipals().toString();
        if (subject.hasRole("admin")) {
            res = res + "----------你拥有admin权限";
        }
        if (subject.hasRole("guest")) {
            res = res + "----------你拥有guest权限";
        }
        return reJson;
    }

    @RequestMapping(value = "/getEmployee", method = { RequestMethod.POST })
    @ApiOperation(value="获取用户信息做标示", notes="获取用户信息做标示")
    public String getEmployee(@RequestBody String parameter){
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        String employeeID = loginInformation.getEmployeeID();
        LoginInformation Information;
        try {
            Information = loginService.getEmployee(employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,Information);
    }

    @RequestMapping(value = "/findEmpInformationByEmpId", method = { RequestMethod.POST })
    @ApiOperation(value="根据employeeID获取用户信息", notes="根据employeeID获取用户信息")
    public String findEmpInformationByEmpId(@RequestBody String parameter){
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        String employeeID = loginInformation.getEmployeeID();
        EmpInformationResult information;
        try {
            information = loginService.findEmpInformationByEmpId(employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,information);
    }

    @RequestMapping(value = "/findEmpInformation", method = { RequestMethod.POST })
    @ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
    public String findEmpInformation(){
        List<EmpInformationResult> information;
        try {
            information = loginService.findEmpInformation();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,information);
    }


}
