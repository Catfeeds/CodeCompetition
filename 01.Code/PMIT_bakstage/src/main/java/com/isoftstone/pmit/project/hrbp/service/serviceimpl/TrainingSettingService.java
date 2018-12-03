package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.pmit.common.model.TrainingParam;
import com.isoftstone.pmit.project.hrbp.entity.TrainingInfo;
import com.isoftstone.pmit.project.hrbp.mapper.TrainingSettingMapper;
import com.isoftstone.pmit.project.hrbp.service.ITrainingSettingService;

@Service
public class TrainingSettingService implements ITrainingSettingService {
	
	@Autowired
	private TrainingSettingMapper trainingSettingMapper;

	@Override
	public List<String> queryBu() {
		return trainingSettingMapper.queryBu();
	}

	@Override
	public List<TrainingInfo> queryTrainingList(TrainingParam param) {
		Map<String, Object> map = new HashMap<>();
		map.put("trainingName", param.getTrainingName());
		map.put("series", param.getSeries());
		map.put("sort", param.getSort());
		map.put("type", param.getType());
		map.put("bu", param.getBu());
		List<TrainingInfo> trainingInfos = trainingSettingMapper.queryTrainingList(map);
		return trainingInfos;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void saveTrainingInfo(TrainingInfo param) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("trainingName", param.getTrainingName());
		map.put("series", param.getSeries());
		map.put("sort", param.getSort());
		map.put("type", param.getType());
		map.put("bu", param.getBu());
		map.put("creatorId", param.getCreatorId());
		map.put("creatorName", param.getCreatorName());
		map.put("trainingDes", param.getTrainingDes());
		map.put("trainingDuration", param.getTrainingDuration());
		
		if (null == param.getTrainingId()) {
			trainingSettingMapper.addTrainingInfo(map);
		} else {
			trainingSettingMapper.updateTrainingInfo(map);
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteTrainingInfo(Integer trainingId) {
		trainingSettingMapper.deleteTrainingInfo(trainingId);
	}

	@Override
	public List<String> querySort() {
		return trainingSettingMapper.querySort();
	}

	@Override
	public List<String> querySeries() {
		return trainingSettingMapper.querySeries();
	}
	
}
