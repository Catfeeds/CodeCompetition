package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.pmit.common.model.TeamParam;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectTeamMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectTeamService;

@Service
public class ProjectTeamServiceImpl implements IProjectTeamService {
	
	@Autowired
	private ProjectTeamMapper projectTeamMapper;

	@Override
	public List<TeamInfo> getProjectTeamData(Map<String, Object> queryParam) {
		List<TeamInfo> teamInfos = projectTeamMapper.queryProjectTeamData(queryParam);
		return teamInfos;
	}

	@Override
	public List<Map<String, Object>> searchEmployeeInfos(Map<String, Object> queryMap) {
		List<Map<String, Object>> teamInfos = projectTeamMapper.queryResourcePoolData(queryMap);
		return teamInfos;
	}

	@Override
	public List<Map<String, String>> queryAreaAndCuByCondition(TeamParam param) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("pdu", param.getPdu());
		List<Map<String, String>> result = projectTeamMapper.queryAreaAndCuByCondition(queryMap);
		return result;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public String saveProjectTeamData(List<TeamInfo> teamInfos) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("teamId", teamInfos.get(0).getTeamId());
		List<TeamInfo> oldTeamInfos = projectTeamMapper.queryProjectTeamData(queryMap);
		if (ListUtils.isEmpty(oldTeamInfos)) {
			projectTeamMapper.addTeamInfo(teamInfos);
		} else {
			Map<String, List<TeamInfo>> addAndDeleteTeamInfos = getAddAndDeleteTeamInfos(teamInfos,oldTeamInfos);
			List<TeamInfo> addTeamInfos = addAndDeleteTeamInfos.get("addTeamInfos");
			List<TeamInfo> deleteTeamInfos = addAndDeleteTeamInfos.get("deleteTeamInfos");
			projectTeamMapper.addTeamInfo(addTeamInfos);
			projectTeamMapper.deleteTeamInfo(deleteTeamInfos);
		}
		return "success";
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveProjectRole(List<Map<String, Object>> paramMap) {
		projectTeamMapper.deleteProjectRole(paramMap.get(0));
		projectTeamMapper.saveProjectTeamRole(paramMap);
	}

	private Map<String, List<TeamInfo>> getAddAndDeleteTeamInfos(List<TeamInfo> teamInfos,
			List<TeamInfo> oldTeamInfos) {
		Map<String, List<TeamInfo>> result = new HashMap<>();
		List<TeamInfo> temp = new ArrayList<>();
		temp.addAll(teamInfos);
		temp.removeAll(oldTeamInfos);
		oldTeamInfos.removeAll(teamInfos);
		result.put("addTeamInfos", temp);
		result.put("deleteTeamInfos", oldTeamInfos);
		return result;
	}

	@Override
	public void teamRelatedPo(String teamId, String projectId) {
		Map<String, Object> map = new HashMap<>();
		map.put("teamId", teamId);
		map.put("projectId", projectId);
		projectTeamMapper.teamRelatedPo(map);
	}

	@Override
	public List<Map<String, Object>> queryProjectInformation(Map<String, Object> paramMap) {
		List<Map<String, Object>> result = projectTeamMapper.queryProjectInformation(paramMap);
		return result;
	}

	@Override
	public List<Map<String, Object>> queryProjectRole() {
		return projectTeamMapper.queryProjectRole();
	}

	@Override
	public List<Map<String, Object>> queryProjectInformationByProjectTime(Map<String, Object> paramMap) {
		
		return projectTeamMapper.queryProjectInformationByProjectTime(paramMap);
	}

	@Override
	public List<Map<String, Object>> queryPoStaffInfo(Map<String, Object> paramMap) {
		
		return projectTeamMapper.queryPoStaffInfo(paramMap);
	}

}
