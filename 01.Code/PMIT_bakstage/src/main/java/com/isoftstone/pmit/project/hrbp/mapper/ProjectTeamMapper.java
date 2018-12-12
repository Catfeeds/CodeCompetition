package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.project.hrbp.entity.TeamInfo;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProjectTeamMapper {

	List<TeamInfo> queryProjectTeamData(Map<String, Object> queryMap);

	List<Map<String, Object>> queryResourcePoolData(Map<String, Object> queryMap);

	List<Map<String, String>> queryAreaAndCuByCondition(Map<String, Object> queryMap);

	void addTeamInfo(List<TeamInfo> addTeamInfos);

	void deleteTeamInfo(List<TeamInfo> deleteTeamInfos);

	void saveProjectRole(Map<String, Object> map);

	void teamRelatedPo(Map<String, Object> map);

    List<Map<String, Object>> queryProjectInformation(Map<String, Object> paramMap);

    List<Map<String, Object>> queryProjectRole();

	List<Map<String, Object>> queryProjectInformationByProjectTime(Map<String, Object> paramMap);

	List<Map<String, Object>> queryPoStaffInfo(Map<String, Object> paramMap);

	void deleteProjectRole(Map<String, Object> map);

	void deleteteamRelatedPo(Map<String, Object> paramMap);

	void deletePoStaffInfo(Map<String, Object> paramMap);
}
