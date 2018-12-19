package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.pmit.common.model.TrainingParam;
import com.isoftstone.pmit.common.util.ListUtils;
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
	public List<String> queryDu(String bu) {
		return trainingSettingMapper.queryDu(bu);
	}
	@Override
	public List<String> queryPdu(String bu, String du) {
        Map<String, Object> map = new HashMap<>();
        map.put("bu", bu);
        map.put("du", du);
		return trainingSettingMapper.queryPdu(map);
	}
	@Override
	public List<TrainingInfo> queryTrainingList(TrainingParam param) {
		Map<String, Object> map = new HashMap<>();
		map.put("trainingName", param.getTrainingName());
		map.put("series", param.getSeries());
		map.put("sort", param.getSort());
		map.put("classType", param.getClassType());
		map.put("bu", param.getBu());
		List<TrainingInfo> trainingInfos = trainingSettingMapper.queryTrainingList(map);
		return trainingInfos;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public String saveTrainingInfo(TrainingInfo param) {

		Map<String, Object> map = new HashMap<>();
		map.put("trainingName", param.getTrainingName());
		
		map.put("trainingId", param.getTrainingId());
		map.put("series", param.getSeries());
		map.put("sort", param.getSort());
		map.put("type", param.getType());
		map.put("classType", param.getClassType());
		map.put("bu", param.getBu());
		map.put("creatorId", param.getCreatorId());
		String creatorName = null;
		if(null != param.getCreatorId() && param.getCreatorId() != "") {
			creatorName = trainingSettingMapper.queryNameByID(param.getCreatorId());
		}
		map.put("creatorName", creatorName);

		map.put("updaterId", param.getUpdaterId());
		String updaterName = null;
		if(null != param.getUpdaterId() && param.getUpdaterId() != "") {
			updaterName = trainingSettingMapper.queryNameByID(param.getUpdaterId());
		}
		map.put("updaterName", updaterName);
		map.put("trainingDes", param.getTrainingDes());
		map.put("trainingDuration", param.getTrainingDuration());

		if (null == param.getTrainingId()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			map.put("trainingId", "Tra"+ sdf.format(new Date()));
			Integer count = trainingSettingMapper.queryTrainingByName(map);
			if (count > 0) {
				return "DuplicateName";
			}
			trainingSettingMapper.addTrainingInfo(map);
		} else {
			trainingSettingMapper.updateTrainingInfo(map);
		}
		return "success";
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteTrainingInfo(String trainingId) {
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

	@Override
	public TrainingInfo queryTrainingInfoByTrainingId(String trainingId) {
		Map<String, Object> map = new HashMap<>();
		map.put("trainingId", trainingId);
		List<TrainingInfo> trainingInfos = trainingSettingMapper.queryTrainingList(map);
		if (ListUtils.isEmpty(trainingInfos)) {
			return null;
		}
		return trainingInfos.get(0);
	}
	
}
