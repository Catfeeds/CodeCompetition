package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/hrbp/projectManage")
@Api(value = "项目组设置", tags = {"项目组设置"})
public class ProjectManageController {

    @Autowired
    private IProjectManageService projectManageService;

    private Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
    private Map<String, List<String>> tempLevelMap = new HashMap<String, List<String>>();
    {
        tempMap.put("ALL", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)));
        tempMap.put("西安2012成本中心", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        tempMap.put("成都2012成本中心", new ArrayList<Integer>(Arrays.asList(9, 10, 11, 12, 13)));


        tempLevelMap.put("ALL", new ArrayList<String>(Arrays.asList("2012与企业IT")));
        tempLevelMap.put("2012与企业IT", new ArrayList<String>(Arrays.asList("2012")));
        tempLevelMap.put("2012", new ArrayList<String>(Arrays.asList("西安2012成本中心", "成都2012成本中心")));
    }

    private void getTeamID(Map<String, Object> parameter){
        Object o = parameter.get("CU");
        if(o== null){
            o = "ALL";
        }
        parameter.put("teamIDs",tempMap.get(String.valueOf(o)));
    }

    @PostMapping(value = "/queryProjectLevel")
    public String queryProjectLevel(@RequestBody Map<String, Object> params) {
        List<String> resulr = null;
        if (params.get("BD") == null) {
            resulr = tempLevelMap.get("ALL");
            return AjaxResult.returnToResult(true, resulr);
        }

        if (params.get("BD") != null && params.get("BU") == null) {
            resulr = tempLevelMap.get(String.valueOf(String.valueOf(params.get("bu"))));
            return AjaxResult.returnToResult(true, resulr);
        }

        if (params.get("BU") != null) {
            resulr = tempLevelMap.get(String.valueOf(String.valueOf(params.get("BU"))));
            return AjaxResult.returnToResult(true, resulr);
        }

        return AjaxResult.returnToResult(true, resulr);
    }

    @ApiOperation(value = "项目组查询接口", notes = "项目组级查询接口")
    @PostMapping(value = "/queryProjects")
    public String queryProjects(@RequestBody Map<String, Object> parameter) {
        getTeamID(parameter);
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            result = projectManageService.queryProjects(parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "项目组删除接口", notes = "项目组删除接口")
    @PostMapping(value = "/deleteProject")
    public String deleteProject(@RequestBody Map<String, Object> params) {
        getTeamID(params);
        try {
            projectManageService.deleteProject(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, "Delete Project Success");
    }

    @ApiOperation(value = "项目经理查询", notes = "项目经理查询")
    @PostMapping(value = "/queryPMs")
    public String queryPMs(@RequestBody Map<String, Object> params) {
        List<Map<String, String>> result;
        try {
            result = projectManageService.queryPMs(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "项目组添加接口", notes = "项目组添加接口")
    @PostMapping(value = "/addProjectNode")
    public String addProjectNode(@RequestBody Map<String, Object> params) {
        getTeamID(params);
        try {
            Long teamID = projectManageService.addProjectNode(params);
            params.put("teamID",teamID);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }


        return AjaxResult.returnToResult(true, "Add Project Success");
    }
}
