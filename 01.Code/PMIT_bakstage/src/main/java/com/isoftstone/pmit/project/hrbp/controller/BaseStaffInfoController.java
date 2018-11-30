package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.project.hrbp.entity.PersonalInformation;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/baseStaff")
@Api(value = "员工基本信息", tags = {"员工基本信息查询"})
public class BaseStaffInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(BaseStaffInfoController.class);
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
    public int updatePersonalInformation(@RequestBody PersonalInformation personalInformation){
        LOG.info("BaseStaffInfoController updatePersonalInformation"+personalInformation);
        baseStaffInfoService.updatePersonalInformation(personalInformation);
        return 0;
    }

    @RequestMapping(value = "/deletePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="删除员工基本信息")
    public int deletePersonalInformation(@RequestParam(value = "employeeID", required = false)String employeeID) {
        LOG.info("BaseStaffInfoController deletePersonalInformation" + employeeID);
        baseStaffInfoService.deletePersonalInformationById(employeeID);
        return 0;
    }

    @RequestMapping(value = "/deleteMorePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="删除多个员工基本信息")
    public int deleteMorePersonalInformation(@RequestBody List<String> employeeIdList) {
        baseStaffInfoService.deletePersonalInformationByList(employeeIdList);
        return 0;
    }

    @RequestMapping(value = "/insertPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="员工信息", notes="添加员工基本信息")
    public int insertPersonalInformation(@RequestBody PersonalInformation personalInformation) {
        LOG.info(" insert PersonalInfomation"+personalInformation.toString());
        if (null ==  personalInformation ){
            throw new NullPointerException();
        }
        baseStaffInfoService.insertPersonalInformation(personalInformation);
        return 0;
    }

}
