package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;

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
    @GetMapping(value = "/getAllMenuList")
	public String getAllMenuList() {
		logger.info("getAllMenuList start");
		List<MenuInfo> menuList = null;
		try {
			menuList = menuManageService.getMenuListByRoleId(1);
		} catch (Exception e) {
			logger.info("QueryAllMenuList ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, menuList);
	}
	
	@ApiOperation("根据系统角色查询菜单列表")
//    @GetMapping(value = "/getMenuTreeByRoleId")
	@PostMapping(value = "/getMenuTreeByRoleId")
	public String getMenuTreeByRoleId(@RequestBody Integer roleId) {
		logger.info("getMenuTreeByRoleId" + roleId);
		List<MenuInfo> menuList = null;
		try {
			menuList = menuManageService.getMenuListByRoleId(roleId);
		} catch (Exception e) {
			logger.info("QueryAllMenuList ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, menuList);
	}
	
	@ApiOperation("保存系统角色的菜单列表")
//    @GetMapping(value = "/saveMenuByRoleId")
	@PostMapping(value = "/saveMenuByRoleId")
	public String saveMenuByRoleId(@RequestBody Integer roleId, @RequestBody List<MenuInfo> menuInfos) {
		String message = null;
		try {
			message = menuManageService.saveMenuByRoleId(roleId, menuInfos);
		} catch (Exception e) {
			logger.info("QueryAllMenuList ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, message);
	}
}
