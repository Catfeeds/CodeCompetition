package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.model.QueryParam;
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
	public String queryAreaAndCuBycondition(@RequestBody QueryParam param) {
		List<Map<String, String>> list = null;
		try {
			list = projectTeamService.queryAreaAndCuBycondition(param);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, list);
	}
	
	@ApiOperation("查询资源池人员信息")
	@PostMapping("/searchEmployeeInfos")
	public String searchEmployeeInfos(@RequestBody QueryParam param) {
		List<TeamInfo> teamData = null;
		try {
			teamData = projectTeamService.searchEmployeeInfos(param);
		} catch (Exception e) {
			
		}
		return AjaxResult.returnToResult(false, teamData);
	}
	
	@ApiOperation("查询项目组人员信息")
	@PostMapping("/getTeamInfos")
	public String getProjectTeamData(@RequestBody QueryParam param) {
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
}
