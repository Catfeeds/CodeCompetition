package com.isoftstone.pmit.system.dict.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.system.dict.entity.DictData;
import com.isoftstone.pmit.system.dict.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yihur
 * 字典类型数据
 */
@RestController
@RequestMapping("/system/dictData")
@Api(value = "dictData" ,tags = {"字典类型数据"})
public class DictDataController extends AbstractController {

    @Autowired
    private DictDataService dictDataService;

    @ApiOperation(value="字典数据查询筛试接口", notes="字典数据查询筛试接口")
    @PostMapping(value = "/selectDictDataByDataType")
    public String selectDictDataByDataType(@RequestBody String parameter){
        Map<String,String> paramMap = JsonUtils.readValue(parameter, Map.class);
        int pageNum= Integer.parseInt(String.valueOf(paramMap.get("pageNum")));
        int pageSize= Integer.parseInt(String.valueOf(paramMap.get("pageSize")));
        DictData dictType = JsonUtils.readValue(JsonUtils.toJSON(paramMap.get("item")), DictData.class);
        PageInfo resultList;
        try {
            resultList = dictDataService.selectDictDataByDataType(pageNum,pageSize,dictType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========selectDictDataByDataType error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }



    @ApiOperation(value="更新字典数据", notes="更新字典数据")
    @PostMapping(value = "/updateSysDictData")
    public AjaxResult updateSysDictData(@RequestBody String parameter) {
        DictData parmMap = JsonUtils.readValue(parameter, DictData.class);
        try {
            parmMap.setUpdateBy("penggangq");
            //parmMap.setUpdateTime(new Date());
            dictDataService.updateSysDictData(parmMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateSysDictData error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    @ApiOperation(value="新增字典数据", notes="新增字典数据")
    @PostMapping(value = "/insertSysDictData")
    public AjaxResult insertSysDictData(@RequestBody String parameter) {
        DictData paramMap = JsonUtils.readValue(parameter, DictData.class);
        try {
            paramMap.setCreateBy("admin");
            paramMap.setCreateTime(new Date());
            dictDataService.insertSysDictData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertSysDictData error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }



    @ApiOperation(value="删除字典数据", notes="删除字典数据,可以批量删除")
    @PostMapping(value = "/deleteSysDictData")
    public AjaxResult deleteSysDictData(@RequestBody String parameter) {
        List<Map<String, String>> paramMap = JsonUtils.readValue(parameter, List.class);
        try {
            for(Map<String, String> map:paramMap){
                String str = String.valueOf(map.get("dictCode"));
                dictDataService.deleteSysDictData(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteSysDictData error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}
