package com.isoftstone.pmit.project.rest.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.rest.service.RestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 外部统一接口
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@Api(value = "对外数据接口" ,tags = {"RestApi"})
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private RestService restService;


    @ApiOperation("根据prjID 查询KPI信息")
    @GetMapping(value = "/getKPIbyPrjID")
    public String getKPIbyPrjID(HttpServletRequest request){
        String prjID = (String)request.getParameter("prjID");
        List<Map<String,Object>> resultList;
        try {
            resultList = restService.getKPIbyPrjID(prjID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getKPIbyPrjID error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }

    @ApiOperation(value="测试用接口", notes="测试用接口")
    @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getKPIbyProjectID/{projectId}")
    public String getKPIbyProjectID(@PathVariable String projectId){
        List<Map<String,Object>> resultList;
        try {
            resultList = restService.getKPIAnalyseResultByKPI(projectId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getKPIbyProjectID error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }



    @ApiOperation(value="KPI信息", notes="根据KPI记录的SeqID查询扣分原因分析数据")
    @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getKPIAnalyseResultByKPI")
    public String getKPIAnalyseResultByKPI(HttpServletRequest request){
        String seqID = (String)request.getParameter("seqID");
        List<Map<String,Object>> resultList;

        try {
            resultList = restService.getKPIAnalyseResultByKPI(seqID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getKPIAnalyseResultByKPI error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }

}
