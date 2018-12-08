package com.isoftstone.pmit.system.exceloperation.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.system.exceloperation.mapper.*;
import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    ExcelTransactionMapper transactionMapper;

    @Autowired
    ExcelCourseMapper CourseMapper;

    /**
     * 导入个人信息分表数据组装并实现插入数据库
     * @param scoreFile 需要导入excel文件
     * @return
     */
    @Override
    public String importScore(File scoreFile) {

        // 读取excel文件的所有数据
        Map<Integer, Object> result = new HashMap<Integer, Object>();
        result = ExcelOperationUtils.importExcel(scoreFile);
        if (result.containsKey(0)) {
            return (String)result.get(0);
        }

        // 得到正确的excel文件的数据
        List<Map<String, String>> scoreList = (List<Map<String, String>>)result.get(1);

        List<ScoreTransaction> transList = new ArrayList<ScoreTransaction>();
        List<ScoreCourse> courseList = new ArrayList<ScoreCourse>();

        for (Map<String, String> score : scoreList){

            // 组装事务表数据
            if (!StringUtils.isEmpty(score.get("事务名"))
                    && !StringUtils.isEmpty(score.get("事务维度"))
                    && !StringUtils.isEmpty(score.get("维度分数"))){
                ScoreTransaction trans = new ScoreTransaction();
                trans.setEmployeeID(score.get("软通工号"));
                trans.setTransactionName(score.get("事务名"));
                trans.setDimensionName(score.get("事务维度"));
                trans.setDimensionScore(score.get("维度分数"));
                transList.add(trans);
            }


            // 组装成绩表数据
            if (!StringUtils.isEmpty(score.get("课程名"))
                    && !StringUtils.isEmpty(score.get("课程成绩"))){
                ScoreCourse course = new ScoreCourse();
                course.setEmployeeID(score.get("软通工号"));
                course.setCourseDimension(score.get("课程维度"));
                course.setCourseName(score.get("课程名"));
                course.setCourseScore(score.get("课程成绩"));
                courseList.add(course);
            }
        }


        // 分别插入2张成绩表中
        int transAddResult = transactionMapper.insertTransaction(transList);
        int scoreAddResult = CourseMapper.insertCourse(courseList);
        if (transAddResult + scoreAddResult >= 2){
            return "SUCCESS";
        }
        return "有数据插入数据库失败";
    }
}






















