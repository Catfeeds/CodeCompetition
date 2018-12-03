package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.paramresolver.JsonParam;
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

    //------------------测试代码------------------
    private static Map<String, List<Map<String, Object>>> testMap;

    {
        Map<String, Object> param;

        testMap = new HashMap<String, List<Map<String, Object>>>();

        List<Map<String, Object>> list01 = new ArrayList<Map<String, Object>>();
        param = new HashMap<String, Object>();
        param.put("nodeID", 1);
        param.put("nodePath", null);
        list01.add(param);
        testMap.put("all", list01);

        List<Map<String, Object>> list02 = new ArrayList<Map<String, Object>>();
        param = new HashMap<String, Object>();
        param.put("nodeID", 6);
        param.put("nodePath", ":1:");
        list02.add(param);
        param = new HashMap<String, Object>();
        param.put("nodeID", 7);
        param.put("nodePath", ":1:");
        list02.add(param);
        param = new HashMap<String, Object>();
        param.put("nodeID", 8);
        param.put("nodePath", ":1:");
        list02.add(param);
        testMap.put("triblePart", list02);
    }
    //------------------测试代码------------------


    /**
     * 通过登录用户信息查询层级树结构
     * @param params
     * @return 层级树结构，可能有多棵
     */
    @ApiOperation(value = "层级关系查询BY用户", notes = "层级关系查询BY用户")
    @PostMapping(value = "/queryLevelRlaNodeByUser")
    public String queryLevelRlaNodeByUser(@RequestBody Map<String,Object> params) {
        // TODO: 2018/11/27  通过下发参数获取员工对应层级关系树中的节点权限nodeList

        List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();

        //------------------测试代码------------------
        String user = String.valueOf(params.get("loginUser"));
        nodeList= testMap.get(user);
        //------------------测试代码------------------

        List<LevelTreeNode> treeList = null;
        try {
            treeList = service.queryLevelRlaNode(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, treeList);
    }

    /**
     * 通过下发的节点ID，节点路径查询层级树结构
     * @param nodeList
     * @return 层级树结构，可能有多棵
     */
    @ApiOperation(value = "层级关系查询BY节点", notes = "层级关系查询BY节点")
    @PostMapping(value = "/queryLevelRlaNodeByNode")
    public String queryLevelRlaNodeByNode(@JsonParam(value = "nodeList") List<Map<String,Object>> nodeList) {
        List<LevelTreeNode> treeList;
        try {
            treeList = service.queryLevelRlaNode(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, treeList);
    }

    /**
     * 添加节点，只能添加叶子节点，节点信息选填
     * @param params
     * @return
     */
    @ApiOperation(value = "层级关系节点添加", notes = "层级关系节点添加")
    @PostMapping(value = "/addLevelRlaNode")
    public String addLevelRlaNode(@RequestBody Map<String,Object> params) {
        try {
            service.addLevelRlaNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Add Node Success");
    }

    /**
     * 节点删除，同时删除节点的所有子节点，需要前台提示
     * @param params
     * @return
     */
    @ApiOperation(value = "层级关系节点更新", notes = "层级关系节点删除")
    @PostMapping(value = "/deleteLevelRlaNode")
    public String deleteLevelRlaNode(@RequestBody Map<String,Object> params) {
        String nodePath = (String) params.get("nodePath");
        Integer nodeID = (Integer) params.get("nodeID");

        try {
            service.deleteLevelRlaNode(nodePath, nodeID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Delete Node Success");
    }

    /**
     * 节点信息更新，支持更新部分内容
     * @param params
     * @return
     */
    @ApiOperation(value = "层级关系节点更新", notes = "层级关系节点更新")
    @PostMapping(value = "/updateLevelRlaNode")
    public String updateLevelRlaNode(@RequestBody Map<String,Object> params) {

        try {
            service.updateLevelRlaNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Update Node Success");
    }

    /**
     *
     * @param parameter
     * @return
     */
    @ApiOperation(value = "层级关系节点移动", notes = "层级关系节点移动")
    @PostMapping(value = "/moveLevelRlaNode")
    public String moveLevelRlaNode(@RequestBody Map<String,Object> params) {
        try {
            service.moveLevelRlaNode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Move Node Success");
    }
}
