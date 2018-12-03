package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.common.model.TeamParam;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;

public interface IProjectTeamService {

	List<TeamInfo> getProjectTeamData(TeamParam param);

	List<TeamInfo> searchEmployeeInfos(TeamParam param);

	List<Map<String, String>> queryAreaAndCuBycondition(TeamParam param);

	String saveProjectTeamData(List<TeamInfo> teamInfos);

	String saveProjectTeamRole(TeamInfo teamInfo);

	void teamRelatedPo(String teamId, String projectId);
	
}
