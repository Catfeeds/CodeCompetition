package com.isoftstone.pmit.project.hrbp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.service.IPersonalCenterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/hrbp/PersonalCenter")
@Api(value="个人中心", tags={"个人中心"})
public class PersonalCenterController {
	
	@Autowired
	private IPersonalCenterService personalCenterService;
	
	@ApiOperation("查询待解决事务")
	@PostMapping("/getPendingSolved")
	public String getPendingSolved(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		List<Map<String, Object>> result = personalCenterService.getPendingSolved(paramMap);
		return AjaxResult.returnToResult(true, result);
	}
	
	@ApiOperation("根据事务获取维度")
	@PostMapping("/getDimensionByAffair")
	public String getDimensionByAffair(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		List<Map<String, Object>> result = personalCenterService.getDimensionByAffair(paramMap);
		return AjaxResult.returnToResult(true, result);
	}
	
	@ApiOperation("保存评价得分")
	@PostMapping("/saveScoresByName")
	public String saveScoresByName(@RequestBody String param) {
		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		personalCenterService.saveScoresByName(paramMap);
		return AjaxResult.returnToMessage(true, "success");
	}
	
	@ApiOperation("查询历史记录")
	@PostMapping("/queryHistorySolved")
	public String queryHistorySolved(@RequestBody Map<String, Object> paramMap) {
//		Map<String, Object> paramMap = JSONObject.parseObject(param, HashMap.class);
		List<Map<String, Object>> result = personalCenterService.queryHistorySolved(paramMap);
		return AjaxResult.returnToResult(true, result);
	}
	
}