package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PersonalHistoryInfo;

import java.util.List;
import java.util.Map;

public interface IPersonalCenterService {

	List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap);

	List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap);

	List<PersonalHistoryInfo> queryHistorySolved(Map<String, Object> paramMap);

    void saveEvaluation(PersonalHistoryInfo personalHistoryInfo);

	Map<String, Object> countPendingSolved(Map<String,Object> paramMap);
}
