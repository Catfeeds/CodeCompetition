package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.service.IExaminationAffairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "获取所有考核事务", notes = "获取所有考核事务,输入pageNum,pageSize")
    public String findAllAffair(@RequestBody String parameter) {
        Map<String, String> paramMap = JsonUtils.readValue(parameter, Map.class);
        int pageNum = Integer.parseInt(String.valueOf(paramMap.get("pageNum")));
        int pageSize = Integer.parseInt(String.valueOf(paramMap.get("pageSize")));
        PageInfo resultList;
        try {
            resultList = examinationAffairService.findAllAffair(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }

    @ApiOperation(value = "删除考核事务", notes = "删除考核事务")
    @PostMapping(value = "/deleteAffair")
    public AjaxResult deleteUserRole(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        try {
            examinationAffairService.deleteAffair(examinationAffair.getAffairID());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteAffair error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @RequestMapping(value = "/findExamAffairByAffairId", method = { RequestMethod.POST })
    @ApiOperation(value="根据AffairId获取考核事务", notes="根据AffairId获取考核事务")
    public String findExamAffairByAffairId(@RequestBody String parameter){
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        Integer affairID = examinationAffair.getAffairID();
        ExaminationAffair examinationAffairResult;
        try {
            examinationAffairResult = examinationAffairService.findExamAffairByAffairId(affairID);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====findExamAffairByAffairId error=============" + e);
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,examinationAffairResult);
    }

    @ApiOperation(value = "修改考核事务", notes = "修改考核事务")
    @PostMapping(value = "/updateAffair")
    public AjaxResult updateAffair(@RequestBody String parameter) {
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        try {
            examinationAffairService.updateAffair(examinationAffair);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateAffair error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "添加考核事务", notes = "添加考核事务")
    @PostMapping(value = "/insertAffair")
    public AjaxResult insertAffair(@RequestBody String parameter){
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        try {
            examinationAffairService.insertAffair(examinationAffair);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertAffair error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @RequestMapping(value = "/findExamAffairSystem", method = { RequestMethod.POST })
    @ApiOperation(value="模糊查询", notes="模糊查询所属体系")
    public String findExamAffairSystem(@RequestBody String parameter){
        ExaminationAffair examinationAffair = JsonUtils.readValue(parameter, ExaminationAffair.class);
        List<ExaminationAffair> examinationAffairResult;
        try {
            examinationAffairResult = examinationAffairService.findExamAffairSystem(examinationAffair);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====findExamAffairSystem error=============" + e);
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true,examinationAffairResult);
    }

}
