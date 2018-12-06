package com.isoftstone.pmit.project.hrbp.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.common.web.controller.AbstractController;
import com.isoftstone.pmit.project.hrbp.entity.DataBackUpInfo;
import com.isoftstone.pmit.project.hrbp.service.IDataBackAndRecovService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@RestController
@RequestMapping("/hrbp/backup")
@Api(value = "数据库备份还原模块", tags = {"数据库备份还原模块"})
@EnableScheduling
public class DataBackupController extends AbstractController {

    @Autowired
    private IDataBackAndRecovService dataBackAndRecovService;

    @ApiOperation(value="数据库备份", notes="数据库备份")
    @PostMapping(value = "/dataDackup")
    //@Scheduled(fixedRate = 3600000) //=604800000 7天  360000  表示6分钟
    public String backup(@RequestBody String parameter){
        DataBackUpInfo dataBackUpInfo = JsonUtils.readValue(parameter, DataBackUpInfo.class);
        logger.info("database backup start");
        try {
            String dataName = backupAndRecover();
            //将备份人员信息录入数据库中
            dataBackUpInfo.setDataName(dataName);
            dataBackAndRecovService.saveBackUpInfo(dataBackUpInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false,e.getMessage());
        }
        logger.info("database backup end");
        return AjaxResult.returnToResult(true,1);
    }

    @Scheduled(fixedRate = 604800000) //=604800000 7天  360000  表示6分钟
    public void backupScheduled(){
        try {
            String dataName = backupAndRecover();
            //将备份人员信息录入数据库中
            DataBackUpInfo dataBackUpInfo =  new DataBackUpInfo();
            dataBackUpInfo.setDataName(dataName);
            dataBackUpInfo.setReasonRemark("系统定时任务备份");
            dataBackAndRecovService.saveBackUpInfo(dataBackUpInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @ApiOperation(value="数据库还原", notes="数据库还原")
    @PostMapping(value = "/dataRecoverp")
        public AjaxResult recover(@RequestParam(value = "feilName", required = true)String feilName){
        logger.info("database recover start ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataName = "backup" + formatter.format(new Date());
        String filePath = System.getProperty("user.dir")+"/backup/";
        String filePathName = null;
        try {
            if(null == feilName || "".equals(feilName)){
                filePathName = filePath+dataName+".sql";
               dataBackAndRecovService.backup(filePath,dataName,"pmo_it");
            }else{
                 filePathName = filePath+feilName+".sql";
            }
            //还原数据库
            dataBackAndRecovService.recover(filePathName,"hw_it");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
        logger.info("database recover end ");
        return AjaxResult.success();
    }

    @ApiOperation(value="备份人员信息列表", notes="备份人员信息列表")
    @PostMapping(value = "/dataInfoModifList")
    public String  getDataInfoModifList(@RequestBody String parameter){
        logger.info("database recover start ");
        Map<String, String> paramMap = JsonUtils.readValue(parameter, Map.class);
        int pageNum = Integer.parseInt(String.valueOf(paramMap.get("pageNum")));
        int pageSize = Integer.parseInt(String.valueOf(paramMap.get("pageSize")));
        PageInfo  resultList;
        try {
            resultList = dataBackAndRecovService.findAllDataInfoList(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.returnToResult(false, e.getMessage());
        }
        logger.info("database recover end ");
        return AjaxResult.returnToResult(true,resultList);
    }

    //数据备份和还原
    private String backupAndRecover(){
        logger.info("database backup start");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataName = "backup" + formatter.format(new Date());
        String filePath = System.getProperty("user.dir")+"/backup/";
        try {
            //先备份文件到制定目录，然后恢复到制定的数据库中
            dataBackAndRecovService.backup(filePath,dataName,"hw_it");
            //数据库是否存在 如果存在删除数据库
            dataBackAndRecovService.recover(filePath+dataName+".sql","pmo_it");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("database backup end");
        return dataName;

    }

}
