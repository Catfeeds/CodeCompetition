package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.Course;
import com.isoftstone.pmit.project.hrbp.entity.StaffScoreInfos;
import com.isoftstone.pmit.project.hrbp.service.IGradeSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/hrbp/gradeSheet")
@Api(value = "成绩展示", tags = {"成绩整体展示"})
public class GradeSheetController {

    @Autowired
    private IGradeSheetService gradeSheetService;

    @RequestMapping(value = "/getColumnName", method = { RequestMethod.POST })
    @ApiOperation(value="表头", notes="表头")
    public StaffScoreInfos getColumnName(@RequestBody Map<String,String> parameter){
        StaffScoreInfos result = gradeSheetService.getColumnNameService(parameter);
        /*try{
            result =
        }catch (Exception e) {
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);*/
        return result;
    }



}
