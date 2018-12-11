package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationParam;
import com.isoftstone.pmit.project.hrbp.service.IExaminationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lf
 */
@RestController
@RequestMapping("/hrbp/examinaiton")
@Api(value = "考试设置模块", tags = {"考试设置模块"})
public class ExaminationController {

    @Autowired
    private IExaminationService examinationService;

    @RequestMapping(value = "/queryAllExamination", method = { RequestMethod.POST })
    @ApiOperation(value="获取所有考试项", notes="获取所有考试项")
    public String queryAllExamination(@RequestBody String parameter){
        ExaminationParam examinationParam = JsonUtils.readValue(parameter, ExaminationParam.class);
        PageInfo classInfoList;
        try {
            classInfoList = examinationService.queryAllExamination(examinationParam);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "查询所有考试项未获得有效数据!");
        }
        return AjaxResult.returnToResult(true, classInfoList);
    }

    @RequestMapping(value = "/queryExaminationByID", method = { RequestMethod.POST })
    @ApiOperation(value="根据examinationID获取考试项", notes="根据examinationid获取考试项")
    public String queryExaminationByID(@RequestBody String parameter){
        ExaminationInfo examinationInfo = JsonUtils.readValue(parameter, ExaminationInfo.class);
        ExaminationInfo examinationResult;
        try {
            examinationResult = examinationService.queryExaminationByID(examinationInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "根据examinationID查询考试项未获得有效数据!");
        }
        return AjaxResult.returnToResult(true, examinationResult);
    }

    @RequestMapping(value = "/insertExamination", method = { RequestMethod.POST })
    @ApiOperation(value="添加考试项", notes="添加考试项")
    public String insertExamination(@RequestBody String parameter){
        ExaminationInfo examinationInfo = JsonUtils.readValue(parameter, ExaminationInfo.class);
        try {
           examinationService.insertExamination(examinationInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "添加失败!");
        }
        return AjaxResult.returnToResult(true, "添加成功!");
    }

    @RequestMapping(value = "/updateExamination", method = { RequestMethod.POST })
    @ApiOperation(value="更新考试项", notes="更新考试项")
    public String updateExamination(@RequestBody String parameter){
        ExaminationInfo examinationInfo = JsonUtils.readValue(parameter, ExaminationInfo.class);
        try {
            examinationService.updateExamination(examinationInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "更新失败!");
        }
        return AjaxResult.returnToResult(true, "更新成功!");
    }

    @RequestMapping(value = "/deleteExamination", method = { RequestMethod.POST })
    @ApiOperation(value="删除考试项", notes="删除考试项")
    public String deleteExamination(@RequestBody String parameter){
        ExaminationInfo examinationInfo = JsonUtils.readValue(parameter, ExaminationInfo.class);
        try {
            examinationService.deleteExamination(examinationInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "删除失败!");
        }
        return AjaxResult.returnToResult(true, "删除成功!");
    }

}
