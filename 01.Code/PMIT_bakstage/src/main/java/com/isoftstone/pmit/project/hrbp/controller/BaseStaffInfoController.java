package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.project.hrbp.entity.OneStaffAllInfo;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/staffInfo")
@Api(value = "员工基本信息", tags = {"员工基本信息查询"})
public class BaseStaffInfoController {

    @Autowired
    private BaseStaffInfoService baseStaffInfoService;

    @RequestMapping(value = "/loginIn", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="查询员工基本信息")
    public OneStaffAllInfo getBaseStaffInfoById(@RequestParam(value = "staffisstAccount", required = true) String employeeID){
        OneStaffAllInfo info = baseStaffInfoService.getBaseStaffInfoByID(employeeID);

        return info;
    }

}
