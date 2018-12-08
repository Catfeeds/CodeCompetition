package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.model.ExaminationRulesParam;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
import com.isoftstone.pmit.project.hrbp.entity.SpecialRolesInfo;
import com.isoftstone.pmit.project.hrbp.service.IExaminationRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrbp/eRules")
@Api(value = "考核规则设置模块", tags = {"考核规则设置模块"})
public class ExaminationRulesController extends AbstractController {

    @Autowired
    private IExaminationRulesService examinationRulesService;

    @ApiOperation(value="查询所有关键角色", notes="查询所有关键角色")
    @PostMapping(value = "/querySpecialRolesInfo")
    public String querySpecialRolesInfo(@RequestBody String parameter) {
        ExaminationRulesParam params = JsonUtils.readValue(parameter, ExaminationRulesParam.class);
        List<SpecialRolesInfo> rst = examinationRulesService.querySpecialRolesInfo(params);
        if (ListUtils.isEmpty(rst)) {
            return AjaxResult.returnToResult(false, "查询所有关键角色未获得有效数据!");
        }

        return AjaxResult.returnToResult(true, rst);
    }

    @ApiOperation(value="保存关键角色", notes="保存关键角色")
    @PostMapping(value = "/saveSpecialRole")
    public String saveSpecialRole(@RequestBody String parameter) {
        SpecialRolesInfo oneRole = JsonUtils.readValue(parameter, SpecialRolesInfo.class);
        boolean rst = examinationRulesService.saveSpecialRole(oneRole);
        if (!rst) {
            return AjaxResult.returnToMessage(false, "保存关键角色失败");
        }

        return AjaxResult.returnToMessage(true, "保存关键角色成功");
    }

    @ApiOperation(value="删除关键角色", notes="删除关键角色")
    @PostMapping(value = "/deleteSpecialRole")
    public String deleteSpecialRole(int roleId) {
        boolean rst = examinationRulesService.deleteSpecialRole(roleId);
        if (!rst) {
            return AjaxResult.returnToMessage(false, "删除关键角色失败");
        }

        return AjaxResult.returnToMessage(true, "删除关键角色成功");
    }

    @ApiOperation(value="查询所有考核规则", notes="查询所有考核规则")
    @PostMapping(value = "/queryExaminationRulesInfo")
    public String queryExaminationRulesInfo(@RequestBody String parameter) {
        ExaminationRulesParam params = JsonUtils.readValue(parameter, ExaminationRulesParam.class);
        List<ExaminationRulesInfo> rst = examinationRulesService.queryExaminationRulesInfo(params);
        if (ListUtils.isEmpty(rst)) {
            return AjaxResult.returnToResult(false, "查询所有考核规则未获得有效数据!");
        }

        return AjaxResult.returnToResult(true, rst);
    }

    @ApiOperation(value="存储考核规则", notes="存储考核规则")
    @PostMapping(value = "/saveExaminationRulesInfo")
    public String saveExaminationRulesInfo(@RequestBody String parameter) {
        ExaminationRulesParam params = JsonUtils.readValue(parameter, ExaminationRulesParam.class);
        boolean rst = examinationRulesService.saveExaminationRulesInfo(params);
        if (!rst) {
            return AjaxResult.returnToMessage(false, "保存考核规则失败");
        }

        return AjaxResult.returnToMessage(true, "保存考核规则成功");
    }

    @ApiOperation(value="删除考核规则", notes="删除考核规则")
    @PostMapping(value = "/deleteExaminationRulesInfo")
    public String deleteExaminationRulesInfo(int ruleId) {
        boolean rst = examinationRulesService.deleteExaminationRulesInfo(ruleId);
        if (!rst) {
            return AjaxResult.returnToMessage(false, "删除考核规则失败");
        }

        return AjaxResult.returnToMessage(true, "删除考核规则成功");
    }

    @ApiOperation(value="查询单项规则", notes="查询单项规则")
    @PostMapping(value = "/querySigleRuleInfo")
    public String querySigleRuleInfo(int ruleId) {
        List<SingleRuleInfo> rst = examinationRulesService.querySigleRuleInfo(ruleId);
        if (ListUtils.isEmpty(rst)) {
            return AjaxResult.returnToResult(false, "查询单项规则未获得有效数据!");
        }

        return AjaxResult.returnToResult(true, rst);
    }
}
