package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.mapper.GetScoreMapper;
import com.isoftstone.pmit.project.hrbp.service.IGradeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GradeSheetServiceImpl implements IGradeSheetService {

    @Autowired
    private GetScoreMapper getScoreMapper;


    @Override
    public StaffScoreInfos getColumnNameService(PersonalScoreParameter parameter) {

        /**获取列名*/
        List<Map<String, Object>> allColumnName = getScoreMapper.getColumnName(parameter);
        List<Train> columnNameMap = getColumnNameMap(allColumnName);

        /**获取课程分数*/
        List<Map<String, Object>> allCourseScore = getScoreMapper.getAllCourseScore(parameter);
        PageHelper.startPage(parameter.getPageInfo().getCurrPage(), parameter.getPageInfo().getPageSize());
        String sortColumn = parameter.getPageInfo().getSortColumn();
        String sortType = parameter.getPageInfo().getSortType();
        if (null != sortColumn && sortColumn != "" &&sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<Map<String, Object>> allSocres = getAllScoureAndTransactionScore(allCourseScore);
        PageInfo<Map<String, Object>> allScoresInfo = new PageInfo<>(allSocres);
        int size = allSocres.size();
        StaffScoreInfos scoreInfos = new StaffScoreInfos();
        scoreInfos.setAllCourseScoreList(allScoresInfo);
        scoreInfos.setColumnNameMap(columnNameMap);
        scoreInfos.setSize(size);
        return scoreInfos;
    }



    //获取列头名
    private List<Train> getColumnNameMap(List<Map<String, Object>> parameter) {
        if (null == parameter) {
            new ArrayList<Train>();
        }
        Map<String, List<Course>> columnNameMap = new HashMap<>();
        for (Map<String, Object> stringMap : parameter) {
            String courseName = (String) stringMap.get("courseName");//课程名
            String dimensionName = (String) stringMap.get("dimensionName");//维度名
            int type =(int) stringMap.get("type");
            String oneScoureAllScore = courseName + "oneScore";
            String oneSourceCredit = courseName + "credit";

            List<Course> list1 = new ArrayList<>();
            if (columnNameMap.containsKey(courseName)) {
                Course course1 = new Course();
                course1.setCourseID(courseName + dimensionName);
                course1.setCourseName(dimensionName);
                course1.setType(type);
                List<Course> courses =columnNameMap.get(courseName);
                courses.add(course1);
            } else {
                Course course = new Course();
                course.setCourseID(courseName + dimensionName);
                course.setCourseName(dimensionName);
                course.setType(type);
                Course course2 = new Course();
                course2.setCourseID(oneScoureAllScore);
                course2.setCourseName("总分");
                course2.setType(type);
                Course course3 = new Course();
                course3.setCourseID(oneSourceCredit);
                course3.setCourseName("学分");
                course3.setType(type);
                list1.add(course3);
                list1.add(course2);
                list1.add(course);
                columnNameMap.put(courseName, list1);
            }
        }
        List<Train> trainList = new ArrayList<>();
        Iterator<Map.Entry<String, List<Course>>> entries = columnNameMap.entrySet().iterator();
        while (entries.hasNext()) {
            Train train = new Train();
            Map.Entry<String, List<Course>> entry = entries.next();
            train.setName(entry.getKey());
            train.setCourses(entry.getValue());
            trainList.add(train);

        }
        return trainList;
    }

    /**
     * 获取课程和事务的所有分数
     */
    private List<Map<String, Object>> getAllScoureAndTransactionScore(List<Map<String, Object>> parameter) {
        Map<String, Map<String, Object>> allInfoMap = new HashMap<>();
        for (Map<String, Object> courseMap : parameter) {
            String employeeID = (String) courseMap.get("employeeID");
            String parentHeader = (String) courseMap.get("parentHeader");
            String childHeader = (String) courseMap.get("childHeader");
            int itemtype = (int) courseMap.get("itemtype");
            float score = (float) courseMap.get("score");
            int pass = (int) courseMap.get("pass");
            Double credit = (Double) courseMap.get("credit");
            /**课程+学分id对应的学分*/
            String courseCredit = parentHeader + "|credits";
            /**课程加维度对应的ID考试分数*/
            String courseDimension = parentHeader +"|"+ childHeader;
            /**每个课程或者事务的总分*/
            String courTranScore = parentHeader + "|allSocre";
            /**每个课程或事务对应通过分数的标准*/
            String passScore = parentHeader + "|pass";
            /**每个人的总分*/
            String personalTotalScore = "total";
            if ( score < pass){
                credit = 0.0;
            }
            if (allInfoMap.containsKey(employeeID)) {
                Map<String, Object> personalCourseMap = allInfoMap.get(employeeID);
                float totalScore =(float)(personalCourseMap.get(personalTotalScore));
                totalScore = totalScore + score;
                personalCourseMap.put(personalTotalScore,totalScore);
                float allScore = score;
                if (personalCourseMap.containsKey(parentHeader) && itemtype == 1) {
                    if (personalCourseMap.containsKey(courTranScore)) {
                        float currScore = (float)personalCourseMap.get(courTranScore);
                        allScore = score + currScore;
                        if (allScore < pass){
                            credit = 0.0;
                        }
                    }
                    personalCourseMap.put(courTranScore, allScore);
                }
                personalCourseMap.put(courseCredit, credit);
                personalCourseMap.put(courseDimension, score);
                personalCourseMap.put(courTranScore, allScore);
                personalCourseMap.putAll(courseMap);
                personalCourseMap.put(passScore,pass);
                personalCourseMap.put(parentHeader,pass);
            }  else {
                courseMap.put(courseCredit,credit);
                courseMap.put(passScore,pass);
                courseMap.put(parentHeader,pass);
                courseMap.put(courseDimension, score);
                if (itemtype == 1){
                    courseMap.put(courTranScore,score);
                }
                courseMap.put(personalTotalScore,score);
                allInfoMap.put(employeeID, courseMap);
            }
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> entry : allInfoMap.entrySet()) {
            Map<String, Object> value = entry.getValue();
            double personCredit = 0;
            for (Map.Entry<String,Object> personalEntry :value.entrySet()){
                if(personalEntry.getKey().contains("credits")){
                    personCredit += (double) personalEntry.getValue();
                }
            }
            value.put("personalCredit",personCredit);
            resultList.add(value);
            entry.getKey();
        }
        return resultList;
    }

    @Override
    public PageInfo<PersonalScore> getAllPersonalScores(PersonalScoreParam param) {
        if (param == null){
            new PersonalScoreParam();
        }
        PageHelper.startPage(param.getPageParam().getCurrPage(), param.getPageParam().getPageSize());
        String sortColumn = param.getPageParam().getSortColumn();
        String sortType = param.getPageParam().getSortType();
        if (null != sortColumn && sortColumn != "" && sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<PersonalScore> allPersonalScores = getScoreMapper.getAllPersonalScores(param);
        PageInfo<PersonalScore> resultScores = new PageInfo<>(allPersonalScores);

        return resultScores;
    }

    /**
     * 删除一个人某一门成绩
     * */
    @Override
    public boolean deletePersonalScores(PersonalScore personalScore) {
        if (personalScore == null){
            return false;
        }
        boolean scores = getScoreMapper.deletePersonalScores(personalScore);


        return scores;
    }

    /**
     * 获取一个人的某一个事务的所有得分列表
     * */
    @Override
    public List<PersonalTranAndDimeScore> getPersonalTransactionInfo(PersonalScore personalScore) {
        if (personalScore == null){
            personalScore = new PersonalScore();
        }
        List<PersonalTranAndDimeScore> personalTransactionInfo = getScoreMapper.getPersonalTransactionInfo(personalScore);

        return personalTransactionInfo;
    }

    @Override
    public boolean updatePersonalTraningScores(PersonalScore parameter) {
        if (parameter == null){
           parameter = new PersonalScore();
        }
        boolean scores = getScoreMapper.updatePersonalScores(parameter);
        return scores;
    }
}


