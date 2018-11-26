package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.service.IMenuManageService;
import com.isoftstone.pmit.system.menu.entity.Menu;

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
		List<Menu> menuList = null;
		try {
			menuList = menuManageService.getMenuListByRoleId("0");
		} catch (Exception e) {
			logger.info("QueryAllMenuList ERROR" + e.getMessage());
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(false, menuList);
	}
	
	public String getMenuTreeByRoleId(String roleId) {
		
		return null;
	}
	
}
