package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.entity.RoleMenu;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.service.IRoleMenuService;
import com.isoftstone.pmit.project.hrbp.service.ISystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hrbp/system")
@Api(value = "系统角色模块", tags = {"系统角色管理模块"})
public class SystemRoleController extends AbstractController {

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @ApiOperation(value = "查询系统角色", notes = "查询所有角色")
    @PostMapping(value = "/queryAllRoles")
    public String queryAllRoles() {
        List<SysRole> roleList;
        try {
            roleList = systemRoleService.queryAllRoles();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========queryAllRoles error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, roleList);
    }

    @ApiOperation(value = "删除角色菜单", notes = "删除角色菜单")
    @PostMapping(value = "/deleteSystemRole")
    public String deleteSystemRole(@RequestBody String parameter) {
        SysRole sysRole = JsonUtils.readValue(parameter, SysRole.class);
        try {
            roleMenuService.deleteSystemRole(sysRole.getRoleId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteSystemRole error=============" + e);
            return AjaxResult.returnToMessage(false, "删除角色菜单失败");
        }
        return AjaxResult.returnToMessage(true, "删除角色菜单成功");
    }

    @ApiOperation(value = "更新角色菜单", notes = "更新角色菜单")
    @PostMapping(value = "/updateSystemRole")
    public String updateSystemRole(@RequestBody String parameter) {
        RoleMenu roleMenu = JsonUtils.readValue(parameter, RoleMenu.class);
        SysRole sysRole = roleMenu.getSysRole();
        List<MenuInfo> menuInfoList = roleMenu.getMenuInfoList();
        logger.debug("更新角色菜单！sysRole：" + sysRole + "，菜单数据menuInfoList" + menuInfoList);
        try {
            if (null == sysRole) {
                return AjaxResult.returnToMessage(false, "请选择角色！");
            }
            if (menuInfoList.size() == 0) {
                return AjaxResult.returnToMessage(false, "未授权，请您给该角色授权");
            }
            roleMenuService.updateSystemRole(sysRole, menuInfoList);
            return AjaxResult.returnToMessage(true, "更新角色菜单ok");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新角色菜单！异常！", e);
        }
        return AjaxResult.returnToMessage(false, "操作错误,请稍后重试");

    }

    @ApiOperation(value = "添加角色并授权", notes = "添加角色并授权")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(@RequestBody String parameter) {
        RoleMenu roleMenu = JsonUtils.readValue(parameter, RoleMenu.class);
        SysRole role = roleMenu.getSysRole();
        List<MenuInfo> menuInfoList = roleMenu.getMenuInfoList();
        logger.debug("添加角色并授权！角色数据role：" + role + "，权限数据permIds：" + menuInfoList);
        //获取输入角色名称
        String insertRoleName = role.getRoleName();
        List<SysRole> sysRoles = systemRoleService.queryAllRoles();
        for (SysRole sysRole : sysRoles) {
            //获取数据
            String tempRoleName = sysRole.getRoleName();
            if(insertRoleName.equals(tempRoleName)){
                return AjaxResult.returnToMessage(false, "该角色已存在");
            }
        }
        try {
            if (null == role) {
                return AjaxResult.returnToMessage(false, "请您填写完整的角色数据");
            }
            if (menuInfoList.size() == 0) {
                return AjaxResult.returnToMessage(false, "未授权，请您给该角色授权");
            }
            role.setCreateTime(new Date());
            roleMenuService.addRole(role, menuInfoList);
            return AjaxResult.returnToMessage(true, "添加角色并授权ok");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加角色并授权！异常！", e);
        }
        return AjaxResult.returnToMessage(false, "操作错误,请稍后重试");
    }
}
