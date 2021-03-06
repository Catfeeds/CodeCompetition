package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.service.IMainstayReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/hrbp/MainstayReport")
@Api(value = "骨干报表展现", tags = {"骨干报表展现"})
public class MainstayReportController {

    private Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
    private Map<String, List<String>> tempLevelMap = new HashMap<String, List<String>>();

    {
        tempMap.put("GENEX", new ArrayList<Integer>(Arrays.asList(1, 2, 6)));
        tempMap.put("Cloud", new ArrayList<Integer>(Arrays.asList(3, 4, 5)));
        tempMap.put("ALL", new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)));


        tempLevelMap.put("2012", new ArrayList<String>(Arrays.asList("西安")));
        tempLevelMap.put("西安", new ArrayList<String>(Arrays.asList("GENEX", "Cloud")));
        tempLevelMap.put("ALL", new ArrayList<String>(Arrays.asList("2012")));
    }


    @PostMapping(value = "/queryMainstayLevel")
    public String queryMainstayLevel(@RequestBody Map<String, Object> params) {
        List<String> resulr = null;
        if (params.get("bu") == null) {
            resulr = tempLevelMap.get("ALL");
            return AjaxResult.returnToResult(true, resulr);
        }

        if (params.get("bu") != null && params.get("workPlaceArea") == null) {
            resulr = tempLevelMap.get(String.valueOf(String.valueOf(params.get("bu"))));
            return AjaxResult.returnToResult(true, resulr);
        }

        if (params.get("workPlaceArea") != null) {
            resulr = tempLevelMap.get(String.valueOf(String.valueOf(params.get("workPlaceArea"))));
            return AjaxResult.returnToResult(true, resulr);
        }

        return AjaxResult.returnToResult(true, resulr);
    }


    private void buildTempIDs(Map<String, Object> params) {
        Object pdu = params.get("pdu");
        if (pdu == null) {
            pdu = "ALL";
        }

        params.put("poIDs", tempMap.get(String.valueOf(pdu)));
    }

    @Autowired
    private IMainstayReportService mainstayReportService;

    @Autowired

    @ApiOperation(value = "查询骨干占比饼图", notes = "查询骨干占比饼图")
    @PostMapping(value = "/queryMainstayProportionChart")
    public String queryMainstayProportionChart(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        List<TupleData> result;
        try {
            result = mainstayReportService.queryMainstayProportionChart(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询事业部骨干人数", notes = "查询事业部骨干人数")
    @PostMapping(value = "/queryMainstayByBU")
    public String queryCostCenterMainstay(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        List<TupleData> result;
        try {
            result = mainstayReportService.queryCostCenterMainstay(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询各岗位骨干人数", notes = "查询各岗位骨干人数")
    @PostMapping(value = "/queryMainstayByPost")
    public String queryMainstayByPost(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        List<TupleData> result;
        try {
            result = mainstayReportService.queryMainstayByPost(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干培训情况", notes = "查询骨干培训情况")
    @PostMapping(value = "/queryMainstayTraining")
    public String queryMainstayTraining(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        List<TupleData> result;
        try {
            result = mainstayReportService.queryMainstayTraining(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干年龄分布", notes = "查询骨干年龄分布")
    @PostMapping(value = "/queryMainstayAge")
    public String queryMainstayAge(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        List<TupleData> result;
        try {
            result = mainstayReportService.queryMainstayAge(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }

    @ApiOperation(value = "查询骨干信息表格", notes = "查询骨干信息表格")
    @PostMapping(value = "/queryMainstayTable")
    public String queryMainstayTable(@RequestBody Map<String, Object> params) {
        buildTempIDs(params);
        Map<String, Object> result;
        try {
            result = mainstayReportService.queryMainstayTable(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }
}
