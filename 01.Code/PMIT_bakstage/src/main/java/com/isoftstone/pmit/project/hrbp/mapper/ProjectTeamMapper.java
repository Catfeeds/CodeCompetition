package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;

@Mapper
public interface ProjectTeamMapper {

	List<TeamInfo> queryProjectTeamData(Map<String, Object> queryMap);

	List<TeamInfo> queryResourcePoolData(Map<String, Object> queryMap);

	List<Map<String, String>> queryAreaAndCuBycondition(Map<String, Object> queryMap);

	void addTeamInfo(List<TeamInfo> addTeamInfos);

	void deleteTeamInfo(List<TeamInfo> deleteTeamInfos);
	
}
