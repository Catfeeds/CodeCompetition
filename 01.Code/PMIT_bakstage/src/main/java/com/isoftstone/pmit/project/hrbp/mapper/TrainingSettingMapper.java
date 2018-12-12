package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.project.hrbp.entity.TrainingInfo;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TrainingSettingMapper {

	List<String> queryBu();
	List<String> queryDu(String bu);
	List<String> queryPdu(Map<String, Object> map);
	List<TrainingInfo> queryTrainingList(Map<String, Object> map);

	void addTrainingInfo(Map<String, Object> map);

	void updateTrainingInfo(Map<String, Object> map);

	void deleteTrainingInfo(Integer trainingId);

	List<String> querySort();

	List<String> querySeries();

	Integer queryTrainingByName(Map<String, Object> map);

	String queryNameByID(String Id);
}
