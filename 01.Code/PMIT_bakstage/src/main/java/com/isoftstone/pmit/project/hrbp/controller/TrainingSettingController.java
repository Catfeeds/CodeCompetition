package com.isoftstone.pmit.project.hrbp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("/queryBu")
	public String queryBu() {
		List<String> bu = trainingSettingService.queryBu();
		return AjaxResult.returnToResult(false, bu);
	}
	
	@ApiOperation("查询所属体系")
	@PostMapping("/querySort")
	public String querySort() {
		List<String> sorts = trainingSettingService.querySort();
		return AjaxResult.returnToResult(false, sorts);
	}
	
	@ApiOperation("查询所属系列")
	@PostMapping("/querySeries")
	public String querySeries() {
		List<String> series = trainingSettingService.querySeries();
		return AjaxResult.returnToResult(false, series);
	}
	
	@ApiOperation("查询培训列表")
	@PostMapping("/queryTrainingList")
	public String queryTrainingList(@RequestBody TrainingParam param) {
		List<TrainingInfo> trainingInfos = trainingSettingService.queryTrainingList(param);
		return AjaxResult.returnToResult(false, trainingInfos);
	}
	
	@ApiOperation("根据ID查询培训列表")
	@PostMapping("/queryTrainingInfoByTrainingId")
	public String queryTrainingInfoByTrainingId(@RequestBody TrainingInfo trainingInfo) {
		TrainingInfo result = trainingSettingService.queryTrainingInfoByTrainingId(trainingInfo.getTrainingId());
		return AjaxResult.returnToResult(false, result);
	}
	
	@ApiOperation("新增/修改培训信息")
	@PostMapping("/saveTrainingInfo")
	public String saveTrainingInfo(@RequestBody TrainingInfo trainingInfo) {
		String result = null;
		try {
			result = trainingSettingService.saveTrainingInfo(trainingInfo);
		} catch (Exception e) {
			logger.error("saveTrainingInfo error" + e.getMessage());
			return "fail";
		}
		return result;
	}
	
	@ApiOperation("删除培训信息")
	@PostMapping("/deleteTrainingInfo")
	public String deleteTrainingInfo(@RequestBody TrainingInfo trainingInfo) {
		try {
			trainingSettingService.deleteTrainingInfo(trainingInfo.getTrainingId());
		} catch (Exception e) {
			logger.error("deleteTrainingInfo error" + e.getMessage());
			return "fail";
		}
		return "success";
	}
}
