package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.common.model.TrainingParam;
import com.isoftstone.pmit.project.hrbp.entity.SystemSeriesInfo;
import com.isoftstone.pmit.project.hrbp.entity.TrainingInfo;

public interface ITrainingSettingService {

	List<String> queryBu();

	List<TrainingInfo> queryTrainingList(TrainingParam param);

	String saveTrainingInfo(TrainingInfo trainingInfo);

	void deleteTrainingInfo(Integer trainingId);

	List<String> querySort();

	List<String> querySeries();

	TrainingInfo queryTrainingInfoByTrainingId(Integer trainingId);

	/**
	 * 查询体系与系列
	 * @return
	 */
	SystemSeriesInfo querySystemAndSeries(int id);
	/**
	 * 查询体系
	 * @return
	 */
	List<String> querySystem(int id);
	/**
	 * 新增体系与系列
	 * @return
	 */
	boolean addSystemAndSeriesInfo(Map<String, Object> data);
	/**
	 * 更新体系与系列
	 * @return
	 */
	boolean updateSystemAndSeriesInfo(Map<String, Object> data);
	/**
	 * 删除体系与系列
	 * @return
	 */
	boolean deleteSystemAndSeriesInfo(int id);
}
