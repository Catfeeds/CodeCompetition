package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;

import com.isoftstone.pmit.common.model.TrainingParam;
import com.isoftstone.pmit.project.hrbp.entity.TrainingInfo;

public interface ITrainingSettingService {

	List<String> queryBu();
	List<String> queryDu(String bu);
	List<String> queryPdu(String bu, String du);
	List<TrainingInfo> queryTrainingList(TrainingParam param);

	String saveTrainingInfo(TrainingInfo trainingInfo);

	void deleteTrainingInfo(String string);

	List<String> querySort();

	List<String> querySeries();

	TrainingInfo queryTrainingInfoByTrainingId(String string);
}
