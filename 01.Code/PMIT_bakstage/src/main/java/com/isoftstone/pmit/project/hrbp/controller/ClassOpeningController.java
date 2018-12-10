package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.ClassInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassParam;
import com.isoftstone.pmit.project.hrbp.service.IClassOpeningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lf
 */
@RestController
@RequestMapping("/hrbp/class")
@Api(value = "开班设置模块", tags = {"开班设置模块"})
public class ClassOpeningController {

    @Autowired
    private IClassOpeningService classOpeningService;

    @RequestMapping(value = "/queryAllClass", method = { RequestMethod.POST })
    @ApiOperation(value="获取所有开课项", notes="获取所有开课项")
    public String queryAllClass(@RequestBody String parameter){
        ClassParam classParam = JsonUtils.readValue(parameter, ClassParam.class);
        PageInfo classInfoList;
        try {
            classInfoList = classOpeningService.queryAllClass(classParam);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "查询所有开课项未获得有效数据!");
        }
        return AjaxResult.returnToResult(true, classInfoList);
    }

    @RequestMapping(value = "/insertClass", method = { RequestMethod.POST })
    @ApiOperation(value="添加开课项", notes="添加开课项")
    public String insertClass(@RequestBody String parameter){
        ClassInfo classInfo = JsonUtils.readValue(parameter, ClassInfo.class);
        try {
             classOpeningService.insertClass(classInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "添加失败");
        }
        return AjaxResult.returnToResult(true, "添加成功");
    }

    @RequestMapping(value = "/deleteClass", method = { RequestMethod.POST })
    @ApiOperation(value="删除开课项", notes="删除开课项")
    public String deleteClass(@RequestBody String parameter){
        ClassInfo classInfo = JsonUtils.readValue(parameter, ClassInfo.class);
        try {
             classOpeningService.deleteClass(classInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "删除失败");
        }
        return AjaxResult.returnToResult(true, "删除成功");
    }

    @RequestMapping(value = "/findClassByID", method = { RequestMethod.POST })
    @ApiOperation(value="根据OpeningID查找开课项", notes="根据OpeningID查找开课项")
    public String findClassByID(@RequestBody String parameter){
        ClassInfo classInfo = JsonUtils.readValue(parameter, ClassInfo.class);
        ClassInfo classResult;
        try {
            classResult = classOpeningService.findClassByID(classInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "");
        }
        return AjaxResult.returnToResult(true, classResult);
    }


    @RequestMapping(value = "/updateClass", method = { RequestMethod.POST })
    @ApiOperation(value="更新开课项", notes="更新开课项")
    public String updateClass(@RequestBody String parameter){
        ClassInfo classInfo = JsonUtils.readValue(parameter, ClassInfo.class);
        try {
            classOpeningService.updateClass(classInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, "更新失败");
        }
        return AjaxResult.returnToResult(true, "更新成功");
    }
}
