package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.service.IRoleMenuService;
import com.isoftstone.pmit.project.hrbp.service.ISystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hrbp/system")
@Api(value = "系统角色模块" ,tags = {"系统角色管理模块"})
public class SystemRoleController extends AbstractController {

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @ApiOperation(value="查询系统角色", notes="查询所有角色")
    @PostMapping(value = "/queryAllRoles")
    public String queryAllRoles(){
        List<SysRole> roleList;
        try {
            roleList = systemRoleService.queryAllRoles();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========queryAllRoles error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,roleList);
    }
    @ApiOperation(value="根据用户id查询系统角色", notes="查询角色,根据用户id找角色")
    @PostMapping(value = "/getRoleByEmpID")
    public String getRoleByEmpID(@RequestBody String employeeID){
        SysRole sysRole;
        try {
            sysRole = systemRoleService.getRolesByEmployeeID(employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getRoleByEmpID error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,sysRole);
    }

    @ApiOperation(value="删除角色菜单", notes="删除角色菜单")
    @PostMapping(value = "/deleteSystemRole")
    public AjaxResult deleteSystemRole(@RequestBody Integer roleId){
        try {
            roleMenuService.deleteSystemRole(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteSystemRole error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value="更新角色菜单", notes="更新角色菜单")
    @PostMapping(value = "/updateSystemRole")
    public AjaxResult updateSystemRole(@RequestBody Integer roleId, Integer[] menuIds){
        if (roleMenuService.updateSystemRole(roleId, menuIds) == menuIds.length) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

}
