package com.isoftstone.pmit.project.hrbp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.model.TeamParam;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;
import com.isoftstone.pmit.project.hrbp.service.IProjectTeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hrbp/TeamManage")
@Api(value="群组管理", tags={"项目群组管理"})
public class ProjectTeamController extends AbstractController {
	
	@Autowired
	private IProjectTeamService projectTeamService;
	
	@ApiOperation("查询区域和成本中心")
	@PostMapping("/queryAreaAndCuBycondition")
	public String queryAreaAndCuBycondition(@RequestBody TeamParam param) {
		List<Map<String, String>> list = null;
		try {
			list = projectTeamService.queryAreaAndCuByCondition(param);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, list);
	}
	
	@ApiOperation("查询资源池人员信息")
	@PostMapping("/searchEmployeeInfos")
	public String searchEmployeeInfos(@RequestBody TeamParam param) {
		List<TeamInfo> teamData = null;
		try {
			teamData = projectTeamService.searchEmployeeInfos(param);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, teamData);
	}
	
	@ApiOperation("查询项目组人员信息")
	@PostMapping("/getTeamInfos")
	public String getProjectTeamData(@RequestBody TeamParam param) {
		List<TeamInfo> teamData = null;
		try {
			teamData = projectTeamService.getProjectTeamData(param);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, teamData);
	}
	
	@ApiOperation("保存项目组人员信息")
	@PostMapping("/saveTeamInfos")
	public String saveProjectTeamData(@RequestBody List<TeamInfo> teamInfos) {
		if (ListUtils.isEmpty(teamInfos)) {
			return AjaxResult.returnToResult(false, "fail"); 
		}
		String status = null;
		try {
			status = projectTeamService.saveProjectTeamData(teamInfos);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, status);
	}
	
	@ApiOperation("保存项目人员角色信息")
	@PostMapping("/saveProjectRole")
	public String saveProjectTeamRole(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		try {
			projectTeamService.saveProjectRole(paramMap);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, "success");
	}
	
	@ApiOperation("项目组关联项目")
	@PostMapping("/teamRelatedPo")
	public String teamRelatedPo(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		String teamId = (String) paramMap.get("teamId");
		String projectId = (String) paramMap.get("projectId");
		if (null != teamId && null != projectId) {
			try {
				projectTeamService.teamRelatedPo(teamId, projectId);
			} catch (Exception e) {
				return AjaxResult.returnToResult(false, "fail");
			}
			return AjaxResult.returnToResult(true, "success");
		} else {
			return AjaxResult.returnToResult(false, "error param");
		}
	}

	@ApiOperation("查询PO信息")
	@PostMapping("/queryProjectInformation")
	public String queryProjectInformation(@RequestBody String param) {

		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);

		List<Map<String, Object>> result = projectTeamService.queryProjectInformation(paramMap);

		return AjaxResult.returnToResult(false, result);
	}

	@ApiOperation("查询PO角色信息")
	@PostMapping("/queryProjectRole")
	public String queryProjectRole() {

		List<Map<String, Object>> result = projectTeamService.queryProjectRole();

		return AjaxResult.returnToResult(false, result);
	}
}
