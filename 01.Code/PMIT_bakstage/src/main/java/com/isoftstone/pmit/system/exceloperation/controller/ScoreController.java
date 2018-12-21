package com.isoftstone.pmit.system.exceloperation.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.PersonalScore;
import com.isoftstone.pmit.project.hrbp.entity.PersonalScoreParam;
import com.isoftstone.pmit.project.hrbp.service.IGradeSheetService;
import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "成绩表", tags = {"导入EXCEL文件的课程成绩"})
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IGradeSheetService gradeSheetService;


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


    @RequestMapping(value = "/exportScore", method = { RequestMethod.POST })
    @ResponseBody
    @ApiOperation(value="exportScore", notes="exportScore")
    public void exportScore(@RequestBody PersonalScoreParam param,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        List<PersonalScore> scoreList = gradeSheetService.exportScore(param);

        List<String> trainTitleName = new ArrayList<String>();
        trainTitleName.add("序号");
        trainTitleName.add("软通工号");
        trainTitleName.add("员工姓名");
        trainTitleName.add("产品线");
        trainTitleName.add("部门");
        trainTitleName.add("所属系列");
        trainTitleName.add("开班名称");
        trainTitleName.add("培训名称");
        trainTitleName.add("开班时间");
        trainTitleName.add("讲师");
        trainTitleName.add("得分");

        List<String> evaluateTitleName = new ArrayList<String>();
        evaluateTitleName.add("序号");
        evaluateTitleName.add("软通工号");
        evaluateTitleName.add("员工姓名");
        evaluateTitleName.add("产品线");
        evaluateTitleName.add("部门");
        evaluateTitleName.add("所属系列");
        evaluateTitleName.add("评价名称");
        evaluateTitleName.add("评价时间");
        evaluateTitleName.add("得分");

        List<Map<String, String>> trainListMap = new ArrayList<Map<String, String>>();
        List<Map<String, String>> evaluateListMap = new ArrayList<Map<String, String>>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int trainNum = 1;
        int evaluateNum = 1;
        for (PersonalScore score : scoreList)
        {
            if (StringUtils.equals("培训", score.getTypes()))
            {
                Map<String, String> trainMap = new HashMap<String, String>();
                trainMap.put("序号", String.valueOf(trainNum));
                trainMap.put("软通工号", score.getEmployeeID());
                trainMap.put("员工姓名", score.getEmployeeName());
                trainMap.put("产品线", score.getBu());
                trainMap.put("部门", score.getCostCenter());
                trainMap.put("所属系列", score.getSeries());
                trainMap.put("开班名称", score.getOpeningName());
                trainMap.put("培训名称", score.getAffairName());
                trainMap.put("开班时间", df.format(score.getScoreTime()));
                trainMap.put("讲师", score.getLecturer());
                trainMap.put("得分", String.valueOf(score.getScore()));
                trainListMap.add(trainMap);
                trainNum++;
            }
            if (StringUtils.equals("评价", score.getTypes()))
            {
                Map<String, String> evaluateMap = new HashMap<String, String>();
                evaluateMap.put("序号", String.valueOf(evaluateNum));
                evaluateMap.put("软通工号", score.getEmployeeID());
                evaluateMap.put("员工姓名", score.getEmployeeName());
                evaluateMap.put("产品线", score.getBu());
                evaluateMap.put("部门", score.getCostCenter());
                evaluateMap.put("所属系列", score.getSeries());
                evaluateMap.put("评价名称", score.getAffairName());
                evaluateMap.put("评价时间", df.format(score.getScoreTime()));
                evaluateMap.put("得分", String.valueOf(score.getScore()));
                evaluateListMap.add(evaluateMap);
                evaluateNum++;
            }
        }

        try{
            File templateFile = new File("src/main/resources/configfilemodel/exportScoreTemplate.xlsx");
            InputStream inputStream = new FileInputStream(templateFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(0), trainTitleName, trainListMap);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(1), evaluateTitleName, evaluateListMap);
            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=scoreTable.xlsx");
            workbook.write(os);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
