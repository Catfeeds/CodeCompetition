package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.Project;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrbp/projectManage")
@Api(value = "项目组设置", tags = {"项目组设置"})
public class ProjectManageController {

    @Autowired
    private IProjectManageService service;

    @ApiOperation(value = "项目组级联查询接口", notes = "项目组级联查询接口")
    @PostMapping(value = "/queryProjectTreeNode")
    public String queryProjectTreeNode(@RequestBody String parameter) {
        Map<String, Object> paramMap = JsonUtils.readValue(parameter, Map.class);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("parentID", paramMap.get("parentID"));
        queryMap.put("isLeafNode", paramMap.get("isLeafNode"));
        queryMap.put("projectName", paramMap.get("projectName"));
        queryMap.put("pmName", paramMap.get("pmName"));
        List<Project> resultList = new ArrayList<Project>();

        try {
            resultList = service.queryProjectTreeNode(queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }
}
