package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.service.IGradeSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/hrbp/gradeSheet")
@Api(value = "成绩展示", tags = {"成绩整体展示"})
public class GradeSheetController {

    @Autowired
    private IGradeSheetService gradeSheetService;

  /*  @RequestMapping(value = "/getAllPersonalScore", method = { RequestMethod.POST })
    @ApiOperation(value="所有人成绩展示", notes="所有人成绩展示")
    public String getColumnName(@RequestBody PersonalScoreParameter parameter){
        StaffScoreInfos result = null;
        try{
            result =gradeSheetService.getColumnNameService(parameter);
        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }*/


    @RequestMapping(value = "/getTotalPersonalsScore", method = { RequestMethod.POST })
    @ApiOperation(value="所有人成绩展示第二版", notes="所有人成绩展示第二版")
    public String getAllPersonalScore(@RequestBody PersonalScoreParam param){
        PageInfo<PersonalScore> result = null;
        try{
            result=gradeSheetService.getAllPersonalScores(param);
        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @RequestMapping(value = "/deletePersonalsScore", method = { RequestMethod.POST })
    @ApiOperation(value="删除某一个人的事务或培训成绩", notes="删除某一个人的事务或培训成绩")
    public String deletePersonalScore(@RequestBody PersonalScore param){
        boolean result = false;
        try{
            result=gradeSheetService.deletePersonalScores(param);
        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @RequestMapping(value = "/getPersonaltransactionScore", method = { RequestMethod.POST })
    @ApiOperation(value="获取一个人的事务所属的维度得分", notes="获取一个人的维度得分")
    public String getPersonalTranscationScore(@RequestBody PersonalScore param){
        List<PersonalTranAndDimeScore> result = null;
        try{
            result = gradeSheetService.getPersonalTransactionInfo(param);

        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @RequestMapping(value = "/updatePersonalScore", method = { RequestMethod.POST })
    @ApiOperation(value="修改某一个人的得分", notes="修改某一个人的得分")
    public String updatePersonalTranscationScore(@RequestBody PersonalScore parameter) {
        boolean result = false;
        try {
            result = gradeSheetService.updatePersonalTraningScores(parameter);
        } catch (Exception e) {
            AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }
    }
