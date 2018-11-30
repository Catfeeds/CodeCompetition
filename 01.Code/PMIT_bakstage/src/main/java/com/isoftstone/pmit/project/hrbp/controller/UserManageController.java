package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.service.IUserManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        Map<String,String> paramMap = JsonUtils.readValue(parameter, Map.class);
        int pageNum= Integer.parseInt(String.valueOf(paramMap.get("pageNum")));
        int pageSize= Integer.parseInt(String.valueOf(paramMap.get("pageSize")));
        PageInfo resultList;
        try {
            resultList = userManageService.findEmpInformation(pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,resultList);
    }

}
