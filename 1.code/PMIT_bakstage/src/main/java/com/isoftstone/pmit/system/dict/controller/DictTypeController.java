package com.isoftstone.pmit.system.dict.controller;

import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.system.dict.entity.DictType;
import com.isoftstone.pmit.system.dict.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * 字典类型
 */
@RestController
@RequestMapping("/system/dictType")
@Api(value = "dictType" ,tags = {"字典类型"})
public class DictTypeController extends AbstractController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation(value="查询字典类型", notes="查询字典类型,可选查询参数dictName，status，dictType，updateBy")
    @PostMapping(value = "/getAllDictType")
    public String getAllDictType(@RequestBody String param){
        Map<String, String> dictTypeParam = JsonUtils.readValue(param, Map.class);
        List<DictType> resultList;
        try {
            resultList = dictTypeService.selectAllDictType(dictTypeParam);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("==========getAllDictType error===========" + e.getMessage());
            return AjaxResult.returnToMessage(false, e.getMessage());
        }
        return AjaxResult.returnToResult(true, resultList);
    }


    @ApiOperation(value="新增字典类型", notes="新增字典类型")
    @PostMapping(value = "/insertSysDictType")
    public AjaxResult insertSysDictType(@RequestBody String parameter) {
        DictType paramMap = JsonUtils.readValue(parameter, DictType.class);
        try {
            paramMap.setCreateBy("admin");
            //paramMap.setCreateTime(new Date());
            dictTypeService.insertSysDictType(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====insertSysDictData error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    @ApiOperation(value="更新字典类型", notes="更新字典类型")
    @PostMapping(value = "/updateSysDictType")
    public AjaxResult updateSysDictType(@RequestBody String parameter) {
        DictType paramMap = JsonUtils.readValue(parameter, DictType.class);
        try {
            paramMap.setUpdateBy("penggangq");
            //paramMap.setUpdateTime(new Date());
            dictTypeService.updateSysDictType(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====updateSysDictType error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    @ApiOperation(value="删除字典类型", notes="删除字典类型,返回信息中 code=2 时 该类型下有相关的字典数据不能删除")
    @PostMapping(value = "/deleteSysDictType")
    public AjaxResult deleteSysDictType(@RequestBody String parameter) {
        Map<String,String> paramList = JsonUtils.readValue(parameter, Map.class);
        try {
            String param = String.valueOf(paramList.get("dictId"));
            List<Map<String,String>> paramMap = dictTypeService.selectDictDataByDataTypeByType(param);
            if (!paramMap.isEmpty()){
                return AjaxResult.dataExpection();
            }
            dictTypeService.deleteSysDictType(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====deleteSysDictType error=============" + e);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }



}
