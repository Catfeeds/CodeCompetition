package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.project.hrbp.entity.SystemSeriesInfo;
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
			updaterName = trainingSettingMapper.queryNameByID(param.getUpdateName());
		}
		map.put("updaterName", updaterName);
		map.put("trainingDes", param.getTrainingDes());
		map.put("trainingDuration", param.getTrainingDuration());
		
		if (null == param.getTrainingId()) {
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

	@Override
	public TrainingInfo queryTrainingInfoByTrainingId(Integer trainingId) {
		Map<String, Object> map = new HashMap<>();
		map.put("trainingId", trainingId);
		List<TrainingInfo> trainingInfos = trainingSettingMapper.queryTrainingList(map);
		if (ListUtils.isEmpty(trainingInfos)) {
			return null;
		}
		return trainingInfos.get(0);
	}

    @Override
    public SystemSeriesInfo querySystemAndSeries(int id) {
        List<Map<String, Object>> queryRst = trainingSettingMapper.querySystemAndSeriesInfo(id);

        SystemSeriesInfo rst = new SystemSeriesInfo();
        rst.setIdAndSeriesName(new HashMap<>());
        rst.setSystemAndSeriesName(new HashMap<>());

        Map<Integer, String> idAndSeriesName = rst.getIdAndSeriesName();
        Map<String, List<String>> systemAndSeriesName = rst.getSystemAndSeriesName();
        for (Map<String, Object> oneRow: queryRst) {
            String oneSystem = oneRow.get("systemName").toString();
            if (!systemAndSeriesName.containsKey(oneSystem)) {
                systemAndSeriesName.put(oneSystem, new ArrayList<>());
            }

            systemAndSeriesName.get(oneSystem).add(oneRow.get("seriesName").toString());
            idAndSeriesName.put(Integer.valueOf(oneRow.get("id").toString()), oneRow.get("seriesName").toString());
        }

        return rst;
    }

    @Override
    public List<String> querySystem(int id) {
        return  trainingSettingMapper.querySystem(id);
    }

    @Override
    public boolean addSystemAndSeriesInfo(Map<String, Object> data) {
        return 0 != trainingSettingMapper.addSystemAndSeriesInfo(data);
    }

    @Override
    public boolean updateSystemAndSeriesInfo(Map<String, Object> data) {
        return 0 != trainingSettingMapper.updateSystemAndSeriesInfo(data);
    }

    @Override
    public boolean deleteSystemAndSeriesInfo(int id) {
	    return 0 != trainingSettingMapper.deleteSystemAndSeriesInfo(id);
    }
}
