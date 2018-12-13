package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.service.ITeamLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/hrbp/teamLevel/")
@Api(value = "项目组层级关系", tags = {"项目组层级关系"})
public class TeamLevelController {

    @Autowired
    private ITeamLevelService service;

    @PostMapping(value = "/queryLevel")
    @ApiOperation(value = "查询级联", notes = "查询级联")
    private String queryLevel(Map<String, Object> params) {
        List<String> result;

        try {
            // TODO: 2018/12/11 通过用户信息查询有权限的项目组/PO，全部项目组/PO权限返回null
            result = service.queryLevel(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @PostMapping(value = "/queryLevelTree")
    @ApiOperation(value = "查询关系树", notes = "查询关系树")
    private String queryLevelTree(@RequestBody Map<String, Object> params) {
        List<LevelTreeNode> result;

        try {
            // TODO: 2018/12/11 通过用户信息查询有权限的项目组/PO，全部项目组/PO权限返回null

            Set<Integer> authorityIDs = null;
            params.put("authorityIDs", authorityIDs);
            result = service.queryLevelTree(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @PostMapping(value = "/addLevelTreeNode")
    @ApiOperation(value = "关系树添加节点", notes = "关系树添加节点")
    public String addLevelTreeNode(@RequestBody Map<String, Object> params) {
        try {
            Long authorityID = service.addLevelTreeNode(params);
            System.out.println(authorityID);
            // TODO: 2018/12/11 更新用户权限
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Add Node Success");
    }


    public String deleteLevelTreeNode(@RequestBody Map<String, Object> params) {
        try {
            service.deleteLevelTreeNode(params);
            // TODO: 2018/12/11 更新用户权限
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Delete Node Success");

    }
}
