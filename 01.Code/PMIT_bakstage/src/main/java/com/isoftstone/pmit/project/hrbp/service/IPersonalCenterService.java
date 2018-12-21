package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PersonalAffairdimension;
import com.isoftstone.pmit.project.hrbp.entity.PersonalHistoryInfo;

import java.util.List;
import java.util.Map;

public interface IPersonalCenterService {

	List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap);

	List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap);

	List<Map<String, Object>> queryHistorySolved(Map<String, Object> paramMap);

    void saveEvaluation(List<PersonalAffairdimension> personalAffairdimensionList);

	Map<String, Object> countPendingSolved(Map<String,Object> paramMap);
}
