package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

public interface IPersonalCenterService {

	List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap);

	List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap);

	void saveScoresByName(Map<String, Object> paramMap);

	List<Map<String, Object>> queryHistorySolved(Map<String, Object> paramMap);
	
}
