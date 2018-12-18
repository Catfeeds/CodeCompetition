package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoftstone.pmit.project.hrbp.mapper.PersonalCenterMapper;
import com.isoftstone.pmit.project.hrbp.service.IPersonalCenterService;

@Service
public class PersonalCenterService implements IPersonalCenterService {
	
	@Autowired
	private PersonalCenterMapper personalCenterMapper;

	@Override
	public List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap) {
		return personalCenterMapper.getPendingSolved(paramMap);
	}

	@Override
	public List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap) {
		return personalCenterMapper.getDimensionByAffair(paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveScoresByName(Map<String, Object> paramMap) {
		List<Map<String, Object>> dimensionScore = (List<Map<String, Object>>) paramMap.get("affair");
		paramMap.remove("affair");
		for(Map<String, Object> map : dimensionScore) {
			map.putAll(paramMap);
			personalCenterMapper.saveScoresByName(paramMap);
		}
		personalCenterMapper.updateStaffAffairStatus(paramMap);
	}

	@Override
	public List<Map<String, Object>> queryHistorySolved(Map<String, Object> paramMap) {
		return personalCenterMapper.queryHistorySolved(paramMap);
	}
}
