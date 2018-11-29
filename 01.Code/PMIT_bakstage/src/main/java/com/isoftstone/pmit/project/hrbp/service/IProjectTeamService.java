package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.common.model.QueryParam;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;

public interface IProjectTeamService {

	List<TeamInfo> getProjectTeamData(QueryParam param);

	List<TeamInfo> searchEmployeeInfos(QueryParam param);

	List<Map<String, String>> queryAreaAndCuBycondition(QueryParam param);

	String saveProjectTeamData(List<TeamInfo> teamInfos);
	
}
