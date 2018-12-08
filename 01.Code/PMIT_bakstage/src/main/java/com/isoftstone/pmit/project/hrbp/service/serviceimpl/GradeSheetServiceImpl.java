package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.Course;
import com.isoftstone.pmit.project.hrbp.entity.StaffScoreInfos;
import com.isoftstone.pmit.project.hrbp.entity.Train;
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
    public StaffScoreInfos getColumnNameService(Map<String,String> parameter) {


        //获取列名
        List<Map<String, Object>> allColumnName = getScoreMapper.getColumnName(parameter);
        List<Train> columnNameMap = getColumnNameMap(allColumnName);

        //获取课程分数
        List<Map<String, Object>> allCourseScore = getScoreMapper.getAllCourseScore(parameter);
        List<Map<String, Object>> allCourseScoreList = getAllSocres(allCourseScore);


        //获取事务对应的分数
//        List<Map<String, Object>> allTransactionScore = getScoreMapper.getAllTransactionScore(parameter);
//        List<Map<String, Object>> allTransactionScoreList = getAllSocres(allTransactionScore);

//        allCourseScoreList.addAll(allTransactionScoreList);
        int size = allCourseScoreList.size();
        StaffScoreInfos scoreInfos = new StaffScoreInfos();
        scoreInfos.setAllCourseScoreList(allCourseScoreList);
        scoreInfos.setColumnNameMap(columnNameMap);
        scoreInfos.setSize(size);
        return scoreInfos;
    }



    //获取列头名
    private  List<Train> getColumnNameMap(List<Map<String, Object>> parameter){
        if ( null == parameter ){
            new ArrayList<Train>();
        }

        Map<String, List<Course>> columnNameMap = new HashMap<>();
        for(Map<String, Object> stringMap :parameter){
            List<Course> list1 = new ArrayList<>();
            String courseName = (String)stringMap.get("courseName");//课程名
            String dimensionName =(String) stringMap.get("dimensionName");
            if (columnNameMap.containsKey(courseName)){
                Course course1 = new Course();
                course1.setCourseID(courseName+dimensionName);
                course1.setCourseName(dimensionName);
                List lis = columnNameMap.get(courseName);
                lis.add(course1);
            }else {
                Course course = new Course();
                course.setCourseID(courseName+dimensionName);
                course.setCourseName(dimensionName);
                list1.add(course);
                columnNameMap.put(courseName,list1);
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

    //获取课程分数
    private List<Map<String, Object>> getAllSocres(List<Map<String, Object>> parameter){
        Map<String,Map<String,Object>> allInfoMap = new HashMap<>();
        for (Map<String,Object> courseMap:parameter){
            String employeeID =(String)courseMap.get("employeeID");
            String parentHeader =(String) courseMap.get("parentHeader");
            String childHeader = (String) courseMap.get("childHeader");
            int itemtype =(int) courseMap.get("itemtype");
            float score = (float)courseMap.get("score");
            int pass = (int)courseMap.get("pass");
            Double credit =(Double) courseMap.get("credit");
            /**课程+学分id对应的学分*/
            String courseCredit = parentHeader+credit;
            /**课程加维度对应的ID考试分数*/
            String courseDimension = parentHeader+childHeader;

                if (allInfoMap.containsKey(employeeID)){
                        Map<String, Object> personalCourseMap = allInfoMap.get(employeeID);
                        personalCourseMap.put(courseCredit, credit);
                        personalCourseMap.put(courseDimension, score);
                        personalCourseMap.putAll(courseMap);
                }else{
                    courseMap.put(courseCredit,credit);
                    courseMap.put(courseDimension,score);
                    allInfoMap.put(employeeID,courseMap);
                }
        }
        List<Map<String,Object>> resultList = new ArrayList<>();
        for(Map.Entry<String, Map<String, Object>> entry: allInfoMap.entrySet()){
            Map<String, Object> value = entry.getValue();
            resultList.add(value);
        }

        return resultList;
    }

    //获取事务分数
    private List<Map<String,Object>> getTransactionScores(List<Map<String, Object>> parameter){
        Map<String,Map<String,Object>> allInfoMap = new HashMap<>();
        return null;
    }




}
