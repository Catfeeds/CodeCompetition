package com.isoftstone.pmit.system.exceloperation.controller;

import io.swagger.annotations.Api;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "模板", tags = {"下载骨干考核系统EXCEL模板文件"})
public class ExcelTemplateController {

    /**
     * 下载骨干考核系统个人信息EXCEL模板文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/download/personalInfoTemplate")
    public void importPersonalInfo(HttpServletRequest request, HttpServletResponse response){

        try{
            File templateFile = new File("src/main/resources/configfilemodel/personalInfoTemplate.xlsx");
            InputStream inputStream = new FileInputStream(templateFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=personalInfoTemplate.xlsx");
            workbook.write(os);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * 下载骨干考核系统培训得分EXCEL模板文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/download/scoreTemplate")
    public void importScore(HttpServletRequest request, HttpServletResponse response){

        try{
            File templateFile = new File("src/main/resources/configfilemodel/scoreTemplate.xlsx");
            InputStream inputStream = new FileInputStream(templateFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=scoreTemplate.xlsx");
            workbook.write(os);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
