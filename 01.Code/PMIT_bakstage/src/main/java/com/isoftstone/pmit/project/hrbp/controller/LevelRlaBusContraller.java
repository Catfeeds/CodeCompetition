package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.service.ILevelRlaBusService;
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
@RequestMapping("/hrbp/levelBussinessRelationship")
@Api(value = "业务层级关系设置", tags = {"业务层级关系设置"})
public class LevelRlaBusContraller {
    @Autowired
    private ILevelRlaBusService levelRlaBusService;

    @ApiOperation(value = "查询层级", notes = "查询层级")
    @PostMapping(value = "/queryLevel")
    public String queryLevel(@RequestBody Map<String, Object> params) {
        List<String> result;
        try {
            result = levelRlaBusService.queryLevel(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }


}
