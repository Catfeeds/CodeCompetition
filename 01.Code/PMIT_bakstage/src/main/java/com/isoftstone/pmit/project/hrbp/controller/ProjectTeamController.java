package com.isoftstone.pmit.project.hrbp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.service.IProjectTeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hrbp/TeamManage")
@Api(value="群组管理", tags={"项目群组管理"})
public class ProjectTeamController extends AbstractController {
	
	@Autowired
	private IProjectTeamService projectTeamService;
	
	@ApiOperation("查询项目组人员信息")
	@GetMapping("/getTeamInfos")
	public String getGroupTree() {
		
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}
	
}
