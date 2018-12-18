package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import com.isoftstone.pmit.project.hrbp.service.ITeamLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrbp/projectManage")
@Api(value = "项目组设置", tags = {"项目组设置"})
public class ProjectManageController {

    @Autowired
    private IProjectManageService projectManageService;

    @Autowired
    private ITeamLevelService teamLevelService;

    @ApiOperation(value = "项目组查询接口", notes = "项目组级查询接口")
    @PostMapping(value = "/queryProjects")
    public String queryProjects(@RequestBody Map<String, Object> parameter) {
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
}
