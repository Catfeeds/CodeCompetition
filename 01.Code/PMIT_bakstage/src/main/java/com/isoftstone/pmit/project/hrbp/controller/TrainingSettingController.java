package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.model.TrainingParam;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.TrainingInfo;
import com.isoftstone.pmit.project.hrbp.service.ITrainingSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hrbp/trainingsetting")
@Api(value="培训设置", tags={"培训设置"})
public class TrainingSettingController extends AbstractController {
	
	@Autowired
	private ITrainingSettingService trainingSettingService;
	
	@ApiOperation("查询bu产品线")
	@RequestMapping("/queryBu")
	public String queryBu() {
		List<String> bu = trainingSettingService.queryBu();
		return AjaxResult.returnToResult(false, bu);
	}
	
	@ApiOperation("查询培训列表")
	@RequestMapping("/queryTrainingList")
	public String queryTrainingList(TrainingParam param) {
		List<TrainingInfo> trainingInfos = trainingSettingService.queryTrainingList(param);
		return AjaxResult.returnToResult(false, trainingInfos);
	}
	
	@ApiOperation("新增培训信息")
	@RequestMapping("/saveTrainingInfo")
	public String saveTrainingInfo(TrainingInfo trainingInfo) {
		
		try {
			trainingSettingService.saveTrainingInfo(trainingInfo);
		} catch (Exception e) {
			logger.error("saveTrainingInfo error" + e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	@ApiOperation("删除培训信息")
	@RequestMapping("/deleteTrainingInfo")
	public String deleteTrainingInfo(Integer trainingId) {
		try {
			trainingSettingService.deleteTrainingInfo(trainingId);
		} catch (Exception e) {
			logger.error("deleteTrainingInfo error" + e.getMessage());
			return "fail";
		}
		return "success";
	}
}
