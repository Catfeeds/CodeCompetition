package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.project.hrbp.entity.PersonalInformation;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/baseStaff")
@Api(value = "员工基本信息", tags = {"员工基本信息查询"})
public class BaseStaffInfoController {

    @Autowired
    private BaseStaffInfoService baseStaffInfoService;

    @RequestMapping(value = "/getPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="查询员工基本信息")
    public PersonalInformation getBaseStaffInfoById(@RequestParam(value = "employeeId", required = true) String employeeId){
        PersonalInformation info = baseStaffInfoService.getBaseStaffInfoByID(employeeId);
        return info;
    }

    @RequestMapping(value = "/updatePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="修改员工基本信息")
    public int updatePersonalInformation(PersonalInformation personalInformation){
        int valueNum = baseStaffInfoService.updatePersonalInformation(personalInformation);
        return valueNum;
    }


}
