package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.entity.EmpAndPageInfo;
import com.isoftstone.pmit.project.hrbp.service.IUserManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
@RestController
@RequestMapping("/hrbp/user")
@Api(value = "用户管理模块", tags = {"用户管理模块"})
public class UserManageController extends AbstractController {

    @Autowired
    private IUserManageService userManageService;

    @RequestMapping(value = "/getEmployee", method = { RequestMethod.POST })
    @ApiOperation(value="获取用户信息做标示", notes="获取用户信息做标示")
    public String getEmployee(@RequestBody String parameter){
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        String employeeID = loginInformation.getEmployeeID();
        LoginInformation Information;
        try {
            Information = userManageService.getEmployee(employeeID);
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
            information = userManageService.findEmpInformationByEmpId(employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,information);
    }

    @RequestMapping(value = "/findEmpInformation", method = { RequestMethod.POST })
    @ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
    public String findEmpInformation(@RequestBody String parameter){
        EmpAndPageInfo empAndPageInfo = JsonUtils.readValue(parameter, EmpAndPageInfo.class);
        EmpInformationResult empInformationResult = empAndPageInfo.getEmpInformationResult();
        com.isoftstone.pmit.project.hrbp.entity.PageInfo pageInfo = empAndPageInfo.getPageInfo();
        PageInfo resultList;
        try {
            resultList = userManageService.findEmpInformation(pageInfo,empInformationResult);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,resultList);
    }

    @RequestMapping(value = "/queryUserByKeyword", method = { RequestMethod.POST })
    @ApiOperation(value="模糊查询用户信息", notes="模糊查询用户信息,可选employeeID,employeeName")
    public String queryUserByKeyword(@RequestBody String parameter){
        Map<String,String> mapParam = JsonUtils.readValue(parameter, Map.class);
        String keyword = mapParam.get("keyword");
        List<EmpInformationResult> information;
        try {
            information = userManageService.queryUserByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,information);
    }

    @ApiOperation(value = "删除用户角色", notes = "删除用户角色")
    @PostMapping(value = "/deleteUserRole")
    public String deleteUserRole(@RequestBody String parameter) {
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        try {
            userManageService.deleteUserRole(loginInformation.getEmployeeID());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteUserRole error=============" + e);
            return AjaxResult.returnToMessage(false, "删除失败");
        }
        return AjaxResult.returnToMessage(false, "删除成功");
    }

    @ApiOperation(value = "添加用户角色", notes = "添加用户角色")
    @PostMapping(value = "/insertUserRole")
    public String insertUserRole(@RequestBody String parameter) {
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        //获得前台传递过来的employeeID;
        String insertEmployeeID = loginInformation.getEmployeeID();
        List<EmpInformationResult> loginInformationList =  userManageService.findEmpInformation();
        for (EmpInformationResult empInformationResult : loginInformationList) {
            //获得数据库所有employeeID
            String employeeID = empInformationResult.getEmployeeID();
            if(insertEmployeeID.equals(employeeID)){
                return AjaxResult.returnToMessage(false, "用户已存在");
            }
        }
        try {
            loginInformation.setPassword(DigestUtils.md5Hex("123456"));
            userManageService.insertUserRole(loginInformation);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertUserRole error=============" + e);
            return AjaxResult.returnToMessage(false, "添加失败");
        }
        return AjaxResult.returnToMessage(true, "添加成功");
    }

    @ApiOperation(value = "更新用户角色", notes = "更新用户角色")
    @PostMapping(value = "/updateUserRole")
    public String updateUserRole(@RequestBody String parameter) {
        LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
        try {
            userManageService.updateUserRole(loginInformation);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateUserRole error=============" + e);
            return AjaxResult.returnToMessage(false, "更新失败");
        }
        return AjaxResult.returnToMessage(true, "更新成功");
    }



}
