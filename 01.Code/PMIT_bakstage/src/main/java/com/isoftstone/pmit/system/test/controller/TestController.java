package com.isoftstone.pmit.system.test.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.system.dict.entity.DictData;
import com.isoftstone.pmit.system.test.entity.TestData;
import com.isoftstone.pmit.system.test.service.ITestSevice;
import com.isoftstone.pmit.system.test.service.impl.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/test")
@Api(value = "testData" ,tags = {"测试类"})
public class TestController {

    @Autowired
    private ITestSevice sevice;

    @ApiOperation(value="测试数据查询接口", notes="测试数据查询接口")
    @PostMapping(value = "/selectTestData")
    public String selectTestData(@RequestBody String parameter){
        Map<String,Object> paramMap = JsonUtils.readValue(parameter, Map.class);
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("staffISSTNum",paramMap.get("staffISSTNum"));

        int pageNum= Integer.parseInt(String.valueOf(paramMap.get("pageNum")));
        int pageSize= Integer.parseInt(String.valueOf(paramMap.get("pageSize")));
        PageInfo resultList;
        try {
            resultList = sevice.queryTestData(pageNum,pageSize,queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }

    @ApiOperation(value="testParam", notes="testParam")
    @PostMapping(value = "/testParam")
    public void testParam(@RequestBody List<LevelTreeNode> parameter){
        System.out.println(parameter.getClass().getName());
        System.out.println(parameter);
        System.out.println();
        System.out.println();
    }

    @ApiOperation(value="testParam2", notes="testParam2")
    @PostMapping(value = "/testParam2")
    public void testParam2(@RequestBody String parameter){
        Map<String,Object> param = JsonUtils.readValue(parameter, Map.class);

        System.out.println(param);


        System.out.println();
    }
}
