package com.isoftstone.pmit.project.hrbp.controller;

import com.isoftstone.pmit.common.model.PostOptionParam;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.service.IPostOptionService;
import com.isoftstone.pmit.project.hrbp.util.ParamUtils;
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
@RequestMapping("/hrbp/postOpt")
@Api(value = "岗位选项模块", tags = {"岗位选项模块"})
public class PostOptionController {
    @Autowired
    private IPostOptionService postOptionService;

    @ApiOperation(value="查询单人的岗位选项", notes="查询单人的岗位选项")
    @PostMapping(value = "/queryPostOption")
    public String queryPostOption(@RequestBody String parameter) {
        PostOptionParam params = JsonUtils.readValue(parameter, PostOptionParam.class);
        Map<String, Object> queryParams = ParamUtils.getPostOptionParams(params);
        RelationTreeNode rst = postOptionService.queryBuByPostOption(queryParams);
        if (null == rst) {
            return AjaxResult.returnToResult(false, "查询岗位选项失败!");
        }

        return AjaxResult.returnToResult(true, rst);
    }

    @ApiOperation(value="查询所有岗位选项", notes="查询所有岗位选项")
    @PostMapping(value = "/queryPostOptionInfos")
    public String queryPostOptionInfos(@RequestBody String parameter) {
        PostOptionParam params = JsonUtils.readValue(parameter, PostOptionParam.class);
        Map<String, Object> queryParams = ParamUtils.getPostOptionParams(params);
        List<PostOptionInfo> rst = postOptionService.queryPostOptionInfos(queryParams);
        if (ListUtils.isEmpty(rst)) {
            return AjaxResult.returnToResult(false, "查询所有岗位选项未获得有效数据!");
        }

        return AjaxResult.returnToResult(true, rst);
    }

    @ApiOperation(value="保存岗位选项", notes="保存岗位选项")
    @PostMapping(value = "/savePostOption")
    public String savePostOption(@RequestBody String parameter) {
        PostOptionInfo onePost = JsonUtils.readValue(parameter, PostOptionInfo.class);
        boolean rst = postOptionService.savePostOption(onePost);
        if (!rst) {
            return AjaxResult.returnToMessage(false, "保存岗位选项失败");
        }

        return AjaxResult.returnToMessage(true, "保存岗位选项成功");
    }

    @ApiOperation(value="删除岗位选项", notes="删除岗位选项")
    @PostMapping(value = "/deletePostOption")
    public String deletePostOption(@RequestBody String parameter) {
        PostOptionInfo onePost = JsonUtils.readValue(parameter, PostOptionInfo.class);
        boolean rst = postOptionService.deletePostOption(onePost.getPostId());
        if (!rst) {
            return AjaxResult.returnToMessage(false, "删除岗位选项失败");
        }

        return AjaxResult.returnToMessage(true, "删除岗位选项成功");
    }
}
