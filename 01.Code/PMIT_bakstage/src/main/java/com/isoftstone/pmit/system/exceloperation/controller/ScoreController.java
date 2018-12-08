package com.isoftstone.pmit.system.exceloperation.controller;

import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "成绩表", tags = {"导入EXCEL文件的课程成绩"})
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    /**
     * 导入个人所有信息
     * @param scorePath 需要导入excel文件路径
     * @return
     */
    @RequestMapping(value = "/importScore")
    public String importPersonalInfo(@RequestParam(value = "scorePath", required = true)
                                             String scorePath){
        return scoreService.importScore(scorePath);
    }


}
