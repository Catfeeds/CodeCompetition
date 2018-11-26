package com.isoftstone.pmit.system.menu.controller;

import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.system.menu.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihur
 * 项目菜单
 */
@RestController
@RequestMapping("/system/menu")
@Api(value = "菜单管理" ,tags = {"系统管理中的菜单管理"})
public class MenuController extends AbstractController {

    @Value("${PMIT.name}")
    private String projectEnName;

    @Value("${PMIT.cnName}")
    private String projectCnName;

    @Autowired
    private MenuService menuService;


//    @ApiOperation(value="查询筛选测试接口", notes="查询筛选测试接口")
//    @PostMapping(value = "/selectDictDataByDataType")
//    public String selectDictDataByDataType(@RequestBody String parameter){
//        DictData dictType = JsonUtils.readValue(parameter, DictData.class);
//        PageInfo resultList;
//        try {
//            String queryAllData = "All";
//            if((queryAllData).equals(dictType)){
//                dictType = null;
//            }
//            resultList = dictDataService.selectDictDataByDataType(pageNum,pageSize,dictType);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("==========selectDictDataByDataType error===========" + e.getMessage());
//            return AjaxResult.returnToMessage(false, e.getMessage());
//        }
//        return AjaxResult.returnToResult(true, resultList);
//    }







}
