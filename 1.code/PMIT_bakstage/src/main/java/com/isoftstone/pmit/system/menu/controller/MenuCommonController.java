package com.isoftstone.pmit.system.menu.controller;


import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.system.menu.entity.Menu;
import com.isoftstone.pmit.system.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yihur
 * 项目公共菜单管理
 */
@RestController
@RequestMapping("/menu")
@Api(value = "首页管理" ,tags = {"模块首页管理"})
public class MenuCommonController extends AbstractController {


    @Autowired
    private MenuService menuService;



    @ApiOperation(value="首页模块", notes="查询的首页各模块的列表")
    @GetMapping(value = "/getAllMenuResultList")
    public String getAllMenuResultList(){
        List<Menu> resultList;
        try {
            //所有主模块的根节点ID为"0"
            String menuId = "0";
            resultList = menuService.getMenuResultListByMenuId(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getKPIbyPrjID error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }


    @ApiOperation(value="首页跳转左树页面", notes="首页跳转左树,左树全部为子节点，按照传入的menuId为根节点的ID，返回该根节点下所有的子节点ID")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getMenuResultListByMenuId/{menuId}")
    public String getMenuResultListByMenuId(@PathVariable String menuId){
        List<Menu> resultList;
        try {
            resultList = menuService.getMenuResultListByMenuId(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getMenuResultListByMenuId error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }


}
