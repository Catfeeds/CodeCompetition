package com.isoftstone.pmit.system.exceloperation.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.system.exceloperation.mapper.*;
import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
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
        List<Map<String, Object>> scoreList = (List<Map<String, Object>>)result.get(1);

        List<ScoreTransaction> transList = new ArrayList<ScoreTransaction>();
        List<ScoreCourse> courseList = new ArrayList<ScoreCourse>();

        for (Map<String, Object> score : scoreList){

            // 组装事务表数据
            if (!StringUtils.isEmpty((String)score.get("事务ID"))
                    && !StringUtils.isEmpty((String)score.get("维度ID"))){
                ScoreTransaction trans = new ScoreTransaction();
                trans.setEmployeeID((String) score.get("软通工号"));
                trans.setTransactionID((String) score.get("事务ID"));
                trans.setDimensionID((String) score.get("维度ID"));
                trans.setScore(Float.valueOf((String) score.get("分数")));
                trans.setHeaderID((String) score.get("导师ID"));
                trans.setTutorID((String) score.get("HeaderID"));

                transList.add(trans);
            }


            // 组装成绩表数据
            if (!StringUtils.isEmpty((String)score.get("开班ID"))){
                ScoreCourse course = new ScoreCourse();
                course.setEmployeeID((String) score.get("软通工号"));
                course.setOpeningID((String) score.get("开班ID"));
                course.setCourseScore(Float.valueOf(String.valueOf(score.get("分数"))));
                course.setType((String) score.get("类型"));
                courseList.add(course);
            }
        }

        List<ScoreTransaction> getTransaction = transactionMapper.getTransaction();
        List<ScoreCourse> getCourse = CourseMapper.getCourse();
        List<ScoreTransaction> repeatedTransaction = new ArrayList<>();
        List<ScoreCourse> repeatedCourse = new ArrayList<>();

        for (ScoreCourse course :getCourse){
            String employeeID = course.getEmployeeID();
            String openingID = course.getOpeningID();
            

        }

        for (ScoreTransaction transaction : getTransaction){
            String employeeID = transaction.getEmployeeID();
            String dimensionID = transaction.getDimensionID();
        }


        int transAddResult = 0;
        int scoreAddResult = 0;
        // 分别插入2张成绩表中
        if (!transList.isEmpty()){
             transAddResult = transactionMapper.insertTransaction(transList);

        }
        if (!courseList.isEmpty()){
            scoreAddResult = CourseMapper.insertCourse(courseList);
        }


        if (transAddResult + scoreAddResult >= 1){
            return "SUCCESS";
        }
        return "有数据插入数据库失败";
    }
}






















