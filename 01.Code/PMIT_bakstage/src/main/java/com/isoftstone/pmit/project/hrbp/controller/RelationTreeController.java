package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.service.IRelationTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrbp/LevelTree")
@Api(value = "层级树", tags = {"层级树"})
public class RelationTreeController {



    @Autowired
    private IRelationTreeService service;

    @ApiOperation(value = "添加层级树节点", notes = "添加层级树节点")
    @PostMapping(value = "/addTeamLevelTreeNode")
    public String addTeamLevelTreeNode(@RequestBody Map<String, Object> params) {
        try {
            service.addTeamLevelNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToMessage(true, "Add Node Success");
    }

    @ApiOperation(value = "查询层级树", notes = "查询层级树")
    @PostMapping(value = "/queryTeamLevelTree")
    public String queryTeamLevelTree() {
        List<RelationTreeNode> result;
        try {
            result = service.queryTeamLevelTree();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }
}
