package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.service.IExaminationAffairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lf
 */
@RestController
@RequestMapping("/hrbp/affair")
@Api(value = "考核事务设置模块", tags = {"考核事务设置模块"})
public class ExaminationAffairController extends AbstractController {

    @Autowired
    private IExaminationAffairService examinationAffairService;

    @RequestMapping(value = "/findAllAffair", method = {RequestMethod.POST})
    @ApiOperation(value = "获取所有考核事务", notes = "获取所有考核事务")
    public String findAllAffair(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        List<ExaminationAffair> resultList;
        try {
            resultList = examinationAffairService.findAllAffair(examinationAffair);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }

    @ApiOperation(value = "删除考核事务", notes = "删除考核事务")
    @PostMapping(value = "/deleteAffair")
    public String deleteUserRole(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        try {
            examinationAffairService.deleteAffair(examinationAffair.getAffairID());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteAffair error=============" + e);
            return AjaxResult.returnToMessage(false, "删除失败");
        }
        return AjaxResult.returnToMessage(true, "删除成功");
    }

    @RequestMapping(value = "/findExamAffairByAffairId", method = {RequestMethod.POST})
    @ApiOperation(value = "根据AffairId获取考核事务", notes = "根据AffairId获取考核事务")
    public String findExamAffairByAffairId(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        String affairID = examinationAffair.getAffairID();
        ExaminationAffair examinationAffairResult;
        try {
            examinationAffairResult = examinationAffairService.findExamAffairByAffairId(affairID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====findExamAffairByAffairId error=============" + e);
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, examinationAffairResult);
    }

    @ApiOperation(value = "修改考核事务", notes = "修改考核事务")
    @PostMapping(value = "/updateAffair")
    public String updateAffair(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        try {
            examinationAffairService.updateAffair(examinationAffair);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateAffair error=============" + e);
            return AjaxResult.returnToMessage(false, "修改失败");
        }
        return AjaxResult.returnToMessage(true, "修改成功");
    }

    @ApiOperation(value = "添加考核事务", notes = "添加考核事务")
    @PostMapping(value = "/insertAffair")
    public String insertAffair(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        Integer result = examinationAffairService.insertAffair(examinationAffair);
        if (result ==0){
            return AjaxResult.returnToMessage(false, "事务名重复重复");
        }else if(result==1){
            return AjaxResult.returnToMessage(true, "添加成功");
        }else{
            return AjaxResult.returnToMessage(false, "添加失败");
        }
    }

    @RequestMapping(value = "/findExamAffairSystem", method = {RequestMethod.POST})
    @ApiOperation(value = "事务所属体系接口", notes = "事务所属体系接口")
    public String findExamAffairSystem() {
        List<ExaminationAffair> examinationAffairResult;
        try {
            examinationAffairResult = examinationAffairService.findExamAffairSystem();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====findExamAffairSystem error=============" + e);
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, examinationAffairResult);
    }

    @RequestMapping(value = "/findExamAffairSeries", method = {RequestMethod.POST})
    @ApiOperation(value = "事务所属系列接口", notes = "事务所属系列接口")
    public String findExamAffairSeries() {
        List<ExaminationAffair> examinationAffairResult;
        try {
            examinationAffairResult = examinationAffairService.findExamAffairSeries();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====findExamAffairSeries error=============" + e);
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, examinationAffairResult);
    }
}
