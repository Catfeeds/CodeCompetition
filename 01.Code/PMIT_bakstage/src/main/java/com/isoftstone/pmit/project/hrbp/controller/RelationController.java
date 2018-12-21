package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.constant.CommonConst;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.service.IRelationService;
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
@Api(value = "项目组层级树", tags = {"项目组层级树"})
public class RelationController {
    @Autowired
    private IRelationService service;


    @ApiOperation(value = "查询全部层级", notes = "查询全部层级")
    @PostMapping(value = "/queryAllLevel")
    public String queryAllLevel(@RequestBody Map<String, Object> params) {
        List<Map<String, Object>> result;
        try {
            params.put("tableName", "mms_relation_team");
            result = service.queryAllLevel(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询需要挂载的项目组", notes = "查询需要挂载的项目组")
    @PostMapping(value = "/queryTeamInfo")
    public String queryTeamInfo() {
        List<RelationTreeNode> result;
        try {
            result = service.queryTeamInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询需要挂载的PO", notes = "查询需要挂载的PO")
    @PostMapping(value = "/queryPOInfo")
    public String queryPOInfo() {
        List<RelationTreeNode> result;
        try {
            result = service.queryPOInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询层级树", notes = "查询层级树")
    @PostMapping(value = "/queryAllTree")
    public String queryAllTree(@RequestBody Map<String, Object> params) {
        List<RelationTreeNode> result;
        try {
            result = service.queryAllTree(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "添加层级树节点", notes = "添加层级树节点")
    @PostMapping(value = "/addTreeNode")
    public String addTreeNode(@RequestBody Map<String, Object> params) {
        try {
            service.addTeamNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToMessage(true, "Add Node Success");
    }

    @ApiOperation(value = "删除层级树", notes = "删除层级树")
    @PostMapping(value = "/deleteTreeNode")
    public String deleteTreeNode(@RequestBody Map<String, Object> params) {
        try {
            if ((Boolean) params.get("deleteChildren")) {
                service.deleteNodeAndChildren(params);
            } else {
                service.deleteNode(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToMessage(true, "Delete Tree Node Success");
    }

    @ApiOperation(value = "更改节点信息", notes = "更改节点信息")
    @PostMapping(value = "/updateTreeNode")
    public String updateTreeNode(@RequestBody Map<String, Object> params) {
        try {
            service.updateTreeNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToMessage(true, "Upsate Tree Node Success");
    }

    @ApiOperation(value = "移动节点", notes = "移动节点")
    @PostMapping(value = "/moveTreeNode")
    public String moveTreeNode(@RequestBody Map<String, Object> params) {
        try {
            service.moveTreeNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToMessage(true, "Move Tree Node Success");
    }

    @ApiOperation(value = "通过节点向上查询树", notes = "通过节点向上查询树")
    @PostMapping(value = "/queryParentTreesByNode")
    public String queryParentTreesByNode(@RequestBody Map<String, Object> params) {
        List<RelationTreeNode> result;
        try {
            result = service.queryParentTreesByNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "通过节点向下查询树", notes = "通过节点向下查询树")
    @PostMapping(value = "/queryChildTreesByNode")
    public String queryChildTreesByNode(@RequestBody Map<String, Object> params) {
        List<RelationTreeNode> result;
        try {
            result = service.queryChildTreesByNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询级联信息", notes = "查询级联信息")
    @PostMapping(value = "/queryCascadeInfo")
    public String queryCascadeInfo(Map<String, Object> params) {
        Map<String, Object> result;
        try {
            result = service.queryCascadeInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }
}
