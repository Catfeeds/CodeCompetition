package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.AllPersonalResult;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/hrbp/baseStaff")
@Api(value = "员工基本信息", tags = {"员工基本信息查询"})
public class BaseStaffInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(BaseStaffInfoController.class);
    @Autowired
    private BaseStaffInfoService baseStaffInfoService;

    @RequestMapping(value = "/getPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="查询员工基本信息")
    public String getBaseStaffInfoById(@RequestBody Map<String,String> paraterMap){
        PersonalInformation info = null;
        try{
            info = baseStaffInfoService.getBaseStaffInfoByID(paraterMap);
        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, info);
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
        return AjaxResult.returnToMessage(true,"Update PersonalInfomation Success");
    }

    @RequestMapping(value = "/deletePersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="单个员工", notes="删除员工基本信息")
    public String deletePersonalInformation(@RequestBody Map<String,String> paraterMap) {
        LOG.info("BaseStaffInfoController deletePersonalInformation" + paraterMap);
        try {
             baseStaffInfoService.deletePersonalInformationById(paraterMap);
        }catch (Exception e){
           return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToMessage(true,"Delete PersonalInfomation Success");
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
        return AjaxResult.returnToMessage(true,"Delete  MorePersonalInfomation Success");
    }

    @RequestMapping(value = "/insertPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="员工信息", notes="添加员工基本信息")
    public String insertPersonalInformation(@RequestBody PersonalInformation personalInformation) {
        LOG.info("insert PersonalInfomation"+personalInformation.toString());
        try {
            baseStaffInfoService.insertPersonalInformation(personalInformation);
        }catch (Exception e){
            return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToMessage(true,"Insert PersonalInfomation Success");
    }
    @RequestMapping(value = "/getAllPersonalInfo", method = { RequestMethod.POST })
    @ApiOperation(value="全员信息", notes="查看全员信息")
    public String getAllPersonalInformation(@RequestBody(required=false) PersonInfoAndPageInfo paramter) {
        LOG.info(" BaseStaffInfoController getAllPersonalInformation"+paramter);
        PageInfo<BaseStaffInfo> fuzzyQuery = null;
        try {
             fuzzyQuery = baseStaffInfoService.getPersonalInfoByFuzzyQuery(paramter);

        }catch (Exception e){
            return AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, fuzzyQuery);
    }
}
