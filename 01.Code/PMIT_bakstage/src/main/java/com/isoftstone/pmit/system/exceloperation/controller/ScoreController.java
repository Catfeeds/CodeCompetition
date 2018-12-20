package com.isoftstone.pmit.system.exceloperation.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "成绩表", tags = {"导入EXCEL文件的课程成绩"})
public class ScoreController {

    @Autowired
    private IScoreService scoreService;


    /**
     * 导入个人成绩信息
     * @param file 导入的excel文件
     * @param request
     * @return
     */
    @PostMapping(value ="/importScore")
    @ResponseBody
    @ApiOperation(value = "importScore", notes = "importScore")
    public String importScore(@RequestParam("file") MultipartFile file,
                              @RequestParam("user") String user, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return scoreService.importScore(saveFile, user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return AjaxResult.returnToResult(false,"上传失败," + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.returnToResult(false,"上传失败," + e.getMessage());
            }
        } else {
            return AjaxResult.returnToResult(false,"上传失败，因为文件为空.");
        }
    }

}
