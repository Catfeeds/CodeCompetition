package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isoftstone.pmit.common.model.QueryParam;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectTeamMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectTeamService;

@Service
public class ProjectTeamServiceImpl implements IProjectTeamService {
	
	@Autowired
	private ProjectTeamMapper projectTeamMapper;

	@Override
	public List<TeamInfo> getProjectTeamData(QueryParam param) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("teamId", param.getTeamId());
		List<TeamInfo> teamInfos = projectTeamMapper.queryProjectTeamData(queryMap);
		return teamInfos;
	}

	@Override
	public List<TeamInfo> searchEmployeeInfos(QueryParam param) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("pdu", param.getPdu());
		queryMap.put("gender", param.getGender());
		queryMap.put("area", param.getArea());
		queryMap.put("cu", param.getCu());
		queryMap.put("name", param.getName());
		queryMap.put("staffId", param.getStaffId());
		List<TeamInfo> teamInfos = projectTeamMapper.queryResourcePoolData(queryMap);
		return teamInfos;
	}

	@Override
	public List<Map<String, String>> queryAreaAndCuBycondition(QueryParam param) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("pdu", param.getPdu());
		List<Map<String, String>> result = projectTeamMapper.queryAreaAndCuBycondition(queryMap);
		return result;
	}
	
//	@Transactional(rollbackFor=Exception.class)
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
		return null;
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

}
