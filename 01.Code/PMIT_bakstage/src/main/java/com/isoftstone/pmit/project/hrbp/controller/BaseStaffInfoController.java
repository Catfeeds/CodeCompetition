package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.project.hrbp.entity.PersonInfoAndPageInfo;
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
    public String updatePersonalInformation(@RequestBody PersonalInformation personalInformation){
        LOG.info("BaseStaffInfoController updatePersonalInformation"+personalInformation);
        try{
            baseStaffInfoService.updatePersonalInformation(personalInformation);
        }catch (Exception e){
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true,"Update PersonalInfomation Success");
    }

    @RequestMapping(value = "/deletePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="删除员工基本信息")
    public String deletePersonalInformation(@RequestParam(value = "employeeID", required = false)String employeeID) {
        LOG.info("BaseStaffInfoController deletePersonalInformation" + employeeID);
        try {
             baseStaffInfoService.deletePersonalInformationById(employeeID);
        }catch (Exception e){
           return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true,"Delete PersonalInfomation Success");
    }

    @RequestMapping(value = "/deleteMorePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="删除多个员工基本信息")
    public String deleteMorePersonalInformation(@RequestBody List<String> employeeIdList) {
        LOG.info(" deleteMorePersonalInformation "+employeeIdList.toString());
        try{
            baseStaffInfoService.deletePersonalInformationByList(employeeIdList);
        }catch (Exception e){
            return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true,"Delete  MorePersonalInfomation Success");
    }

    @RequestMapping(value = "/insertPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="员工信息", notes="添加员工基本信息")
    public String insertPersonalInformation(@RequestBody PersonalInformation personalInformation) {
        LOG.info(" insert PersonalInfomation"+personalInformation.toString());
        try {
            baseStaffInfoService.insertPersonalInformation(personalInformation);
        }catch (Exception e){
            return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true,"Insert PersonalInfomation Success");
    }
    @RequestMapping(value = "/getAllPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="全员信息", notes="查看全员信息")
    public List<BaseStaffInfo> getAllPersonalInformation(@RequestBody PersonInfoAndPageInfo paramter) {
        LOG.info(" BaseStaffInfoController getAllPersonalInformation"+paramter);
        List<BaseStaffInfo> staffInfoList = baseStaffInfoService.getPersonalInfoByFuzzyQuery(paramter);
        return staffInfoList;
    }


}
