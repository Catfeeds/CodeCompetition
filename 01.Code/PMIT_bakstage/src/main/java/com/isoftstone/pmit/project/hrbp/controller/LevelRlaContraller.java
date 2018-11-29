package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.service.ILevelRlaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/hrbp/levelRelationship")
@Api(value = "层级关系设置", tags = {"层级关系设置"})
public class LevelRlaContraller {
    @Autowired
    private ILevelRlaService service;

    @ApiOperation(value = "层级关系查询BY用户", notes = "层级关系查询BY用户")
    @PostMapping(value = "/queryLevelRlaNodeByUser")
    public String queryLevelRlaNodeByUser(@RequestBody String parameter) {
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);

        // TODO: 2018/11/27  通过下发参数获取员工对应层级关系树中的节点权限nodeList

        List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();

        //---------测试代码
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nodeID", 1);
        map.put("nodePath", null);
        nodeList.add(map);
        //---------测试代码


        List<LevelTreeNode> treeList;
        try {
            treeList = service.queryLevelRlaNode(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, treeList);
    }

    @ApiOperation(value = "层级关系查询BY节点", notes = "层级关系查询BY节点")
    @PostMapping(value = "/queryLevelRlaNodeByNode")
    public String queryLevelRlaNodeByNode(@RequestBody String parameter) {
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);
        List<Map<String, Object>> nodeList = (List<Map<String, Object>>) paramMap.get("nodeList");

        List<LevelTreeNode> treeList;
        try {
            treeList = service.queryLevelRlaNode(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, treeList);
    }

    @ApiOperation(value = "层级关系节点添加", notes = "层级关系节点添加")
    @PostMapping(value = "/addLevelRlaNode")
    public String addLevelRlaNode(@RequestBody String parameter) {
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);

        try {
            service.addLevelRlaNode(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Add Node Success");
    }

    @ApiOperation(value = "层级关系节点删除", notes = "层级关系节点删除")
    @PostMapping(value = "/deleteLevelRlaNode")
    public String deleteLevelRlaNode(@RequestBody String parameter) {
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);
        String nodePath = (String) paramMap.get("nodePath");
        Integer nodeID = (Integer) paramMap.get("nodeID");

        try {
            service.deleteLevelRlaNode(nodePath, nodeID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Delete Node Success");
    }

    @ApiOperation(value = "层级关系节点移动", notes = "层级关系节点移动")
    @PostMapping(value = "/moveLevelRlaNode")
    public String moveLevelRlaNode(@RequestBody String parameter){
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);

        try {
            service.moveLevelRlaNode(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Move Node Success");
    }
}
