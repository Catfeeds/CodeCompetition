package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.service.SystemRoleService;
import com.isoftstone.pmit.project.hrbp.service.serviceimpl.MenuRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrbp/system")
@Api(value = "系统角色模块" ,tags = {"系统角色管理模块"})
public class SystemRoleController extends AbstractController {

    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value="获取所有系统角色", notes="查询系统角色")
    @PostMapping(value = "/selectAllSystemRole")
    public String selectAllSystemRole(){
        List<SysRole> sysRoleList;
        try {
            sysRoleList = systemRoleService.selectAllSystemRole();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========selectAllSystemRole error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, sysRoleList);
    }

    @ApiOperation(value="查询字典类型", notes="查询字典类型,可选查询参数role_id ,role_name，role_key")
    @PostMapping(value = "/getAllRoles")
    public String getAllRoles(@RequestBody String param){
        Map<String, String> sysRoleParam = JsonUtils.readValue(param, Map.class);
        List<SysRole> resultList;
        try {
            resultList = systemRoleService.getAllRoles(sysRoleParam);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getAllRoles error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }


    @ApiOperation(value="添加系统角色", notes="添加系统角色")
    @PostMapping(value = "/insertSystemRole")
    public AjaxResult insertSystemRole(@RequestBody String parameter){
        SysRole sysRole = JsonUtils.readValue(parameter, SysRole.class);
        try {
            sysRole.setCreateBy("admin");
            sysRole.setCreateTime(new Date());
            systemRoleService.insertSystemRole(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertSystemRole error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value="删除系统角色", notes="删除系统角色")
    @PostMapping(value = "/deleteSystemRole")
    public AjaxResult deleteSystemRole(@RequestBody String parameter){
        List<Map<String, String>> paramMap = JsonUtils.readValue(parameter, List.class);
        try {
            for(Map<String, String> map:paramMap){
                String str = String.valueOf(map.get("roleId"));
                systemRoleService.deleteSystemRole(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteSystemRole error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value="更新系统角色", notes="更新系统角色")
    @PostMapping(value = "/updateSystemRole")
    public AjaxResult updateSystemRole(@RequestBody String parameter){
        SysRole sysRole = JsonUtils.readValue(parameter, SysRole.class);
        try {
            systemRoleService.updateSystemRole(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateSystemRole error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value="更新角色菜单", notes="更新角色菜单")
    @PostMapping(value = "/updateMenuRole")
    public AjaxResult updateMenuRole(Long roleId, Long[] mids) {
        if (menuRoleService.updateMenuRole(roleId, mids) == mids.length) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

}
