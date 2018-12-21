package com.isoftstone.pmit.system.exceloperation.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.system.exceloperation.mapper.*;
import com.isoftstone.pmit.system.exceloperation.service.IScoreService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    ExcelTransactionMapper transactionMapper;

    @Autowired
    ExcelCourseMapper courseMapper;

    @Autowired
    ExcelStaffMapper staffMapper;

    /**
     * 导入个人信息分表数据组装并实现插入数据库
     * @param scoreFile 需要导入excel文件
     * @return
     */
    @Override
    public String importScore(File scoreFile, String user) {

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
            if (!StringUtils.isEmpty(score.get("事务ID"))
                    && !StringUtils.isEmpty(score.get("维度ID"))){
                ScoreTransaction trans = new ScoreTransaction();
                trans.setEmployee_id(score.get("软通工号"));
                trans.setAffair_id(score.get("事务ID"));
                trans.setDimension_id(score.get("维度ID"));
                trans.setEvaluator(score.get("评价人ID"));
                trans.setScore(Float.valueOf(score.get("分数")));
                trans.setType(score.get("类型"));
                trans.setModifier(user);
                transList.add(trans);
            }

            // 组装成绩表数据
            if (!StringUtils.isEmpty(score.get("开班ID"))){
                ScoreCourse course = new ScoreCourse();
                course.setEmployee_id(score.get("软通工号"));
                course.setOpening_id(score.get("开班ID"));
                course.setTest_score(Float.valueOf(score.get("分数")));
                course.setType(score.get("类型"));
                course.setModifier(user);
                courseList.add(course);
            }
        }

        // 获取已有的成绩表和事务表的数据
        List<ScoreCourse> courses = courseMapper.getCourse();
        List<ScoreTransaction> trans = transactionMapper.getTransaction();
        List<ScoreCourse> newCoursesInsert = new ArrayList<ScoreCourse>();
        List<ScoreTransaction> newTransInsert = new ArrayList<ScoreTransaction>();
        List<ScoreCourse> newCoursesUpdate = new ArrayList<ScoreCourse>();
        List<ScoreTransaction> newTransUpdate = new ArrayList<ScoreTransaction>();
        List<ScoreTransaction> newStaffs = new ArrayList<ScoreTransaction>();
        newStaffs = transList;

        boolean scoreExist = false;
        for (ScoreCourse scoreCourse : courseList){
            String courseEmployeeID = scoreCourse.getEmployee_id();
            String courseOpeningID = scoreCourse.getOpening_id();
            for (ScoreCourse course : courses){
                String employeeID = course.getEmployee_id();
                String openingID = course.getOpening_id();
                if (StringUtils.equals(courseEmployeeID, employeeID)
                        && StringUtils.equals(courseOpeningID, openingID))
                {
                    scoreExist = true;
                    break;
                }
            }
            if (scoreExist){
                newCoursesUpdate.add(scoreCourse);
            }else{
                newCoursesInsert.add(scoreCourse);
            }
            scoreExist = false;
        }

        for (ScoreTransaction scoreTran : transList){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            scoreTran.setChange_time(df.format(new Date()));
            String tranEmployeeID = scoreTran.getEmployee_id();
            String tranAffairID = scoreTran.getAffair_id();
            for (ScoreTransaction tran : trans){
                String employeeID = tran.getEmployee_id();
                String affairID = tran.getAffair_id();
                if (StringUtils.equals(tranEmployeeID, employeeID)
                        && StringUtils.equals(tranAffairID, affairID))
                {
                    scoreExist = true;
                    break;
                }
            }
            if (scoreExist){
                newTransUpdate.add(scoreTran);
            }else{
                newTransInsert.add(scoreTran);
            }
            scoreExist = false;
        }

        int courseAddResult = 0;
        int transAddResult = 0;
        int courseUpdateResult = 0;
        int transUpdateResult = 0;
        int staffAddResult = 0;
        if (!newCoursesInsert.isEmpty()){
            courseAddResult = courseMapper.insertCourse(newCoursesInsert);
        }
        if (!newTransInsert.isEmpty()){
            transAddResult = transactionMapper.insertTransaction(newTransInsert);

        }
        if (!newCoursesUpdate.isEmpty()){
            courseUpdateResult = courseMapper.updateCourse(newCoursesUpdate);
        }
        if (!newTransUpdate.isEmpty()){
            transUpdateResult = transactionMapper.updateTransaction(newTransUpdate);

        }
        if (!newStaffs.isEmpty()){
            staffAddResult = staffMapper.insertStaff(newStaffs);
        }

        if (courseAddResult >= 0 && transAddResult >= 0
                && courseUpdateResult >= 0 && transUpdateResult >= 0
                && staffAddResult >= 0){
            return "SUCCESS";
        }else{
            return "有数据库操作失败";
        }
    }
}
