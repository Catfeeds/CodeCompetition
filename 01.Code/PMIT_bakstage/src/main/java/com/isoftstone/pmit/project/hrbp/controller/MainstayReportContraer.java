package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.BaseTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.MainstayInfo;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.service.IMainstayReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrbp/MainstayReportContraer")
@Api(value = "骨干报表展现", tags = {"骨干报表展现"})
public class MainstayReportContraer {

    @Autowired
    private IMainstayReportService service;

    @ApiOperation(value = "查询骨干信息层级", notes = "查询骨干信息层级")
    @PostMapping(value = "/queryMainstayLevel")
    public String queryMainstayLevel(@RequestBody Map<String, Object> params) {
        BaseTreeNode result;
        try {
            result = service.queryMainstayLevel(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干占比饼图", notes = "查询骨干占比饼图")
    @PostMapping(value = "/queryMainstayProportionChart")
    public String queryMainstayProportionChart(@RequestBody Map<String, Object> params) {
        List<TupleData> result;
        try {
            result = service.queryMainstayProportionChart(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询事业部骨干人数", notes = "查询事业部骨干人数")
    @PostMapping(value = "/queryMainstayByBU")
    public String queryCostCenterMainstay(@RequestBody Map<String, Object> params) {
        List<TupleData> result;
        try {
            result = service.queryCostCenterMainstay(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询各岗位骨干人数", notes = "查询各岗位骨干人数")
    @PostMapping(value = "/queryMainstayByPost")
    public String queryMainstayByPost(@RequestBody Map<String, Object> params) {
        List<TupleData> result;
        try {
            result = service.queryMainstayByPost(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干培训情况", notes = "查询骨干培训情况")
    @PostMapping(value = "/queryMainstayTraining")
    public String queryMainstayTraining(@RequestBody Map<String, Object> params) {
        List<TupleData> result;
        try {
            result = service.queryMainstayTraining(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干年龄分布", notes = "查询骨干年龄分布")
    @PostMapping(value = "/queryMainstayAge")
    public String queryMainstayAge(@RequestBody Map<String, Object> params) {
        List<TupleData> result;
        try {
            result = service.queryMainstayAge(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干信息表格", notes = "查询骨干信息表格")
    @PostMapping(value = "/queryMainstayTable")
    public String queryMainstayTable(@RequestBody Map<String, Object> params) {
        MainstayInfo result;
        try {
            result = service.queryMainstayTable(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }
}
