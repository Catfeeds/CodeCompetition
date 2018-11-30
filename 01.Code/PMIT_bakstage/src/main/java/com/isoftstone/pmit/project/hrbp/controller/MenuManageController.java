package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.entity.RoleMenu;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.service.IMenuManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hrbp/menuManage")
@Api(value = "菜单管理" ,tags = {"菜单管理模块"})
public class MenuManageController extends AbstractController {
	
	@Autowired
	private IMenuManageService menuManageService;
	
	@ApiOperation("查询的所有菜单列表")
    @PostMapping(value = "/getAllMenuList")
	public String getAllMenuList() {
		logger.info("getAllMenuList start");
		List<MenuInfo> menuList = null;
		try {
			menuList = menuManageService.getMenus();
		} catch (Exception e) {
			logger.info("QueryAllMenuList ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, menuList);
	}
	
	@ApiOperation("根据用户角色查询菜单列表")
	@PostMapping(value = "/getMenuTreeByRoleId")
	public String getMenuTreeByRoleId(@RequestBody String parameter) {
		SysRole sysRole = JsonUtils.readValue(parameter, SysRole.class);
		logger.info("getMenuTreeByRoleId" + sysRole.getRoleId());
		List<MenuInfo> menuList = null;
		try {
			menuList = menuManageService.getMenuListByRoleId(sysRole.getRoleId());
		} catch (Exception e) {
			logger.info("getMenuTreeByEmpID ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, menuList);
	}

	@ApiOperation("根据用户账号查询菜单列表")
	@PostMapping(value = "/getMenuTreeByEmpID")
	public String getMenuTreeByEmpID(@RequestBody String parameter) {
		LoginInformation loginInformation = JsonUtils.readValue(parameter, LoginInformation.class);
		logger.info("getMenuTreeByEmpID" + loginInformation.getEmployeeID());
		List<MenuInfo> menuList = null;
		try {
			menuList = menuManageService.getMenuTreeByEmpID(loginInformation.getEmployeeID());
		} catch (Exception e) {
			logger.info("getMenuTreeByEmpID ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, menuList);
	}
}
