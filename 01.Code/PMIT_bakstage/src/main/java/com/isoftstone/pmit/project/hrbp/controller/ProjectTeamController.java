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

@SuppressWarnings("unchecked")
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
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, list);
	}

	@ApiOperation("查询资源池人员信息")
	@PostMapping("/searchEmployeeInfos")
	public String searchEmployeeInfos(@RequestBody String param) {
		Map<String, Object> queryMap = JSONObject.parseObject(param, HashMap.class);
		List<Map<String, Object>> teamData = null;
		try {
			teamData = projectTeamService.searchEmployeeInfos(queryMap);
		} catch (Exception e) {
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, teamData);
	}

	@ApiOperation("查询项目组人员信息")
	@PostMapping("/getTeamInfos")
	public String getProjectTeamData(@RequestBody String param) {
		Map<String, Object> queryParam = JSONObject.parseObject(param, HashMap.class);
		List<TeamInfo> teamData = null;
		try {
			teamData = projectTeamService.getProjectTeamData(queryParam);
		} catch (Exception e) {
			return AjaxResult.returnToResult(false, e.getMessage());
		}
		return AjaxResult.returnToResult(true, teamData);
	}

	@ApiOperation("保存项目组人员信息")
	@PostMapping("/saveTeamInfos")
	public String saveProjectTeamData(@RequestBody String param) {
		List<Map<String, Object>> teamInfoMap = (List<Map<String, Object>>) JSONObject.parseObject(param, HashMap.class).get("teamInfos");
		List<TeamInfo> teamInfos = JSONObject.parseArray(JSONObject.toJSONString(teamInfoMap), TeamInfo.class);
		if (ListUtils.isEmpty(teamInfos)) {
			System.out.println("ERROR");
			return AjaxResult.returnToResult(false, "fail");
		}
		String status = null;
		try {
			status = projectTeamService.saveProjectTeamData(teamInfos);
		} catch (Exception e) {
			logger.error("saveProjectTeamData ERROR"+e.getMessage());
			return AjaxResult.returnToMessage(false, "fail");
		}
		return AjaxResult.returnToMessage(true, status);
	}

	@ApiOperation("保存项目人员角色信息")
	@PostMapping("/saveProjectRole")
	public String saveProjectTeamRole(@RequestBody String param) {
		List<Map<String, Object>> paramMap = (List<Map<String, Object>>) JSONObject.parseObject(param, HashMap.class).get("projectRoles");
		if (ListUtils.isEmpty(paramMap)) {
			return AjaxResult.returnToMessage(false, "fail");
		}
		try {
			projectTeamService.saveProjectRole(paramMap);
		} catch (Exception e) {
			return AjaxResult.returnToMessage(false, "fail");
		}
		return AjaxResult.returnToMessage(true, "success");
	}

	@ApiOperation("项目组关联项目")
	@PostMapping("/teamRelatedPo")
	public String teamRelatedPo(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		String teamId = (String) paramMap.get("teamId");
		List<String> projectIds = (List<String>) paramMap.get("projectId");
		if (null != teamId && !ListUtils.isEmpty(projectIds)) {
			try {
				projectTeamService.teamRelatedPo(teamId, projectIds);
			} catch (Exception e) {
				return AjaxResult.returnToMessage(false, "fail");
			}
			return AjaxResult.returnToMessage(true, "success");
		} else {
			return AjaxResult.returnToMessage(false, "error param");
		}
	}

	@ApiOperation("查询PO信息")
	@PostMapping("/queryProjectInformation")
	public String queryProjectInformation(@RequestBody String param) {

		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);

		List<Map<String, Object>> result = projectTeamService.queryProjectInformation(paramMap);

		return AjaxResult.returnToResult(true, result);
	}

	@ApiOperation("查询PO角色信息")
	@PostMapping("/queryProjectRole")
	public String queryProjectRole() {

		List<Map<String, Object>> result = projectTeamService.queryProjectRole();

		return AjaxResult.returnToResult(true, result);
	}
	
	@ApiOperation("删除项目组关联PO")
	@PostMapping("/deleteteamRelatedPo")
	public String deleteteamRelatedPo(@RequestBody String param) {
		
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		
		try {
			projectTeamService.deleteteamRelatedPo(paramMap);
		} catch (Exception e) {
			
			return AjaxResult.returnToMessage(false, "fail");
		}
		return AjaxResult.returnToMessage(true, "success");
	}
	
	@ApiOperation("根据立项时间查询项目组关联的PO信息")
	@PostMapping("/queryteamRelatedPoByProjectTime")
	public String queryProjectInformationByProjectTime(@RequestBody String param) {

		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);

		List<Map<String, Object>> result = projectTeamService.queryProjectInformationByProjectTime(paramMap);

		return AjaxResult.returnToResult(true, result);
	}

	@ApiOperation("查询项目关键角色信息")
	@PostMapping("/queryPoStaffInfo")
	public String queryPoStaffInfo(@RequestBody String param) {

		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);

		List<Map<String, Object>> result = projectTeamService.queryPoStaffInfo(paramMap);

		return AjaxResult.returnToResult(true, result);
	}
	
	@ApiOperation("删除项目关键角色")
	@PostMapping("/deletePoStaffInfo")
	public String deletePoStaffInfo(@RequestBody String param) {

		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		try {
			projectTeamService.deletePoStaffInfo(paramMap);
		} catch (Exception e) {
			return AjaxResult.returnToMessage(false, "fail");
		}
		return AjaxResult.returnToMessage(true, "success");
	}
	
}
