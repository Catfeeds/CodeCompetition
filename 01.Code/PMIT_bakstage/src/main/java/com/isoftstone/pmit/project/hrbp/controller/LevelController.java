package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.service.ILevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/hrbp/levelManager")
@Api(value = "层级关系设置", tags = {"层级关系设置"})
public class LevelController {

    @Autowired
    private ILevelService service;

    @PostMapping(value = "/queryTree")
    @ApiOperation(value = "查询关系树", notes = "查询关系树")
    private String queryLevelTree(@RequestBody Map<String, Object> params) {
        List<LevelTreeNode> result;

        try {
            // TODO: 2018/12/11 通过用户信息查询有权限的项目组/PO，全部项目组/PO权限返回null
            Set<Integer> authorityIDs = null;
            params.put("authorityIDs", authorityIDs);
            result = service.queryLevelTree(params);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, result);
    }


}
