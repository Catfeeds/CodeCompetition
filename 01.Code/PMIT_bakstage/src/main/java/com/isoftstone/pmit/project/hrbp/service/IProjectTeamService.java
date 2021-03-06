package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.common.model.TeamParam;
import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;

public interface IProjectTeamService {

	List<TeamInfo> getProjectTeamData(Map<String, Object> queryParam);

	List<Map<String, Object>> searchEmployeeInfos(Map<String, Object> queryMap);

	List<Map<String, String>> queryAreaAndCuByCondition(TeamParam param);

	String saveProjectTeamData(List<TeamInfo> teamInfos);

	void saveProjectRole(List<Map<String, Object>> paramMap);

	void teamRelatedPo(String teamId, List<String> projectIds);

	List<Map<String, Object>> queryProjectInformation(Map<String, Object> paramMap);

	List<Map<String, Object>> queryProjectRole();

	List<Map<String, Object>> queryProjectInformationByProjectTime(Map<String, Object> paramMap);

	List<Map<String, Object>> queryPoStaffInfo(Map<String, Object> paramMap);

	void deleteteamRelatedPo(Map<String, Object> paramMap);

	void deletePoStaffInfo(Map<String, Object> paramMap);
}
