package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.AffairDimension;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationDimension;
import com.isoftstone.pmit.project.hrbp.service.IDimensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lf
 */
@RestController
@RequestMapping("/hrbp/dimension")
@Api(value = "考核事务维度模块" ,tags = {"考核事务维度模块"})
public class DimensionController extends AbstractController {

    @Autowired
    private IDimensionService dimensionService;

    @ApiOperation(value = "添加事务维度", notes = "添加事务维度")
    @PostMapping(value = "/insertDimension")
    public AjaxResult insertDimension(@RequestBody String parameter){
        AffairDimension affairDimension = JsonUtils.readValue(parameter, AffairDimension.class);
        ExaminationAffair examinationAffair = affairDimension.getExaminationAffair();
        List<ExaminationDimension> examinationDimensionList = affairDimension.getExaminationDimensionList();
        try {
            dimensionService.insertDimension(examinationAffair,examinationDimensionList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertDimension error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }
    @ApiOperation(value = "查看所有维度", notes = "查看所有维度")
    @PostMapping(value = "/queryDimension")
    public String queryDimension(@RequestBody String parameter){
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        Integer affairID = examinationAffair.getAffairID();
        List<ExaminationDimension> examinationDimensionList;
        try {
            examinationDimensionList = dimensionService.queryDimension(affairID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("queryDimension ERROR" + e.getMessage());
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,examinationDimensionList);
    }

}
