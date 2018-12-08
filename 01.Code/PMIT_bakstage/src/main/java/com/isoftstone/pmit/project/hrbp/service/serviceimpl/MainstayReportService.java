package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.Course;
import com.isoftstone.pmit.project.hrbp.entity.Train;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.mapper.MainstayReportMapper;
import com.isoftstone.pmit.project.hrbp.service.IMainstayReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainstayReportService implements IMainstayReportService {

    @Autowired
    private MainstayReportMapper mapper;

    private NumberFormat formatD2 = NumberFormat.getInstance();
    private NumberFormat formatD1 = NumberFormat.getInstance();
    private static final double timeMonth = 30 * 24 * 60 * 60 * 1000.0;

    {
        formatD2.setMaximumFractionDigits(2);
        formatD1.setMaximumFractionDigits(1);
    }

//    private void buildLevelTreeInfo(BaseTreeNode root, List<Map<String, String>> levelInfo) {
//        if (levelInfo != null) {
//            Map<String, BaseTreeNode> tempMap = new HashMap<String, BaseTreeNode>();
//            for (Map<String, String> temp : levelInfo) {
//                BaseTreeNode parentNode = root;
//
//                String bu = temp.get("bu");
//                parentNode = getBaseTreeNode(tempMap, parentNode, bu);
//
//                String workPlaceArea = temp.get("workPlaceArea");
//                parentNode = getBaseTreeNode(tempMap, parentNode, workPlaceArea);
//
//                String pdu = temp.get("pdu");
//                parentNode = getBaseTreeNode(tempMap, parentNode, pdu);
//            }
//        }
//    }
//
//    private BaseTreeNode getBaseTreeNode(Map<String, BaseTreeNode> tempMap, BaseTreeNode parentNode, String currentNode) {
//        if (tempMap.containsKey(currentNode)) {
//            parentNode = tempMap.get(currentNode);
//        } else {
//            BaseTreeNode child = new BaseTreeNode();
//            child.setNodeName(currentNode);
//            child.setChildList(new ArrayList<BaseTreeNode>());
//            parentNode.getChildList().add(child);
//            tempMap.put(currentNode, child);
//            parentNode = child;
//        }
//        return parentNode;
//    }

    @Override
    public List<TupleData> queryMainstayProportionChart(Map<String, Object> params) {
        Map<String, Object> datas = mapper.queryMainstayProportionChart(params);
        List<TupleData> proportionChart = new ArrayList<TupleData>();
        buildMainstayNum(datas, proportionChart);
        return proportionChart;
    }

    private void buildMainstayNum(Map<String, Object> datas, List<TupleData> result) {
        if (datas != null) {
            for (Map.Entry<String, Object> temp : datas.entrySet()) {
                String name = temp.getKey();
                String value = String.valueOf(temp.getValue());
                if (!StringUtilsMethod.isEmpty(name) && !StringUtilsMethod.isEmpty(value)) {
                    TupleData tupleData = new TupleData(name, value);
                    result.add(tupleData);
                }
            }
        }
    }

    @Override
    public List<TupleData> queryCostCenterMainstay(Map<String, Object> params) {
        List<Map<String, Object>> datas = mapper.queryCostCenterMainstay(params);
        List<TupleData> costCenterMainstayNum = new ArrayList<TupleData>();
        buildMainstayNum(datas, costCenterMainstayNum, "其他");
        return costCenterMainstayNum;
    }

    @Override
    public List<TupleData> queryMainstayByPost(Map<String, Object> params) {
        List<Map<String, Object>> datas = mapper.queryMainstayByPost(params);
        List<TupleData> postMainstayNum = new ArrayList<TupleData>();
        buildMainstayNum(datas, postMainstayNum, "Other");
        return postMainstayNum;
    }

    private void buildMainstayNum(List<Map<String, Object>> datas, List<TupleData> result, String defName) {
        if (datas != null) {
            for (Map<String, Object> temp : datas) {
                String name = String.valueOf(temp.get("name"));
                if (name.equalsIgnoreCase("null") || name.isEmpty()) {
                    name = defName;
                }
                String value = String.valueOf(temp.get("number"));
                if (!StringUtilsMethod.isEmpty(name) && !StringUtilsMethod.isEmpty(value)) {
                    TupleData tupleData = new TupleData(name, value);
                    result.add(tupleData);
                }
            }
        }
    }

    @Override
    public List<TupleData> queryMainstayTraining(Map<String, Object> params) {
        List<String> staffIDs = mapper.queryTrainingStaffs(params);
        params.put("staffIDs", staffIDs);
        List<Map<String, Object>> trainInfo = mapper.queryTrainings(params);
        List<TupleData> result = new ArrayList<TupleData>();
        buildMainstayPercentage(trainInfo, (double) staffIDs.size(), result, "Other");
        return result;
    }

    private void buildMainstayPercentage(List<Map<String, Object>> datas, Double totleCount, List<TupleData> result, String defult) {
        if (datas != null) {
            for (Map<String, Object> tempMap : datas) {
                String name = String.valueOf(tempMap.get("name"));

                Double number = Double.valueOf(String.valueOf(tempMap.get("number")));
                String value = formatD2.format(number / totleCount * 100);

                TupleData tupleData = new TupleData();
                tupleData.setName(name);
                tupleData.setValue(value);
                result.add(tupleData);
            }
        }
    }

    @Override
    public List<TupleData> queryMainstayAge(Map<String, Object> params) {
        Calendar age30 = Calendar.getInstance();
        age30.roll(Calendar.YEAR, -30);
        params.put("age30", age30.getTime());

        Calendar age40 = Calendar.getInstance();
        age40.roll(Calendar.YEAR, -40);
        params.put("age40", age40.getTime());

        Map<String, Object> datas = mapper.queryMainstayAge(params);
        List<TupleData> costCenterMainstayNum = new ArrayList<TupleData>();

        buildMainstayNum(datas, costCenterMainstayNum);
        return costCenterMainstayNum;
    }

    @Override
    public Map<String, Object> queryMainstayTable(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer pagenNo = Integer.valueOf(String.valueOf(params.get("pageNo")));
        params.put("rowStart", (pagenNo - 1) * 10);
        params.put("rowEnd", (pagenNo) * 10 - 1);

        Integer totleSize = mapper.queryBaseStaffInfoSize(params);
        result.put("totleSize", totleSize);

        if (totleSize != 0) {
            List<Map<String, Object>> staffInfo = mapper.queryBaseStaffInfo(params);

            Set<String> staffIDs = getStaffIDs(staffInfo);
            params.put("staffIDs", staffIDs);

            List<Map<String, Object>> trainInfos = mapper.queryStaffTrainInfo(params);

            List<Train> trains = new ArrayList<Train>();
            buildTrains(staffInfo, trainInfos, trains);

            result.put("datas", staffInfo);
            result.put("trains", trains);
        }
        return result;
    }

    private Set<String> getStaffIDs(List<Map<String, Object>> staffInfo) {
        Set<String> staffIDs = null;
        if (staffInfo != null) {
            staffIDs = new HashSet<String>();
            for (Map<String, Object> temp : staffInfo) {
                staffIDs.add(String.valueOf(temp.get("staffID")));
                calTimeCol(temp);
            }

            if (staffIDs.isEmpty()) {
                staffIDs = null;
            }
        }

        return staffIDs;
    }

    private void calTimeCol(Map<String, Object> temp) {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Map<String, Double> times;
            long currentTime = System.currentTimeMillis();

            Date employmentDate = dataFormat.parse(String.valueOf(temp.get("employmentDate")));
            times = getTime(currentTime, employmentDate.getTime());
            temp.put("employmentDate", formatD1.format(times));

            Date graduationTime = dataFormat.parse(String.valueOf(temp.get("graduationTime")));
            times = getTime(currentTime, graduationTime.getTime());
            temp.put("graduationTime", formatD1.format(times));
        } catch (Exception e) {

        }
    }

    private Map<String, Double> getTime(long currentTime, long compareTime) {
        Map<String, Double> result = new HashMap<String, Double>();
        long time = currentTime - compareTime;
        Double year = time / timeMonth / 12;
        result.put("year", year);

        Double month = time / timeMonth;
        result.put("month", month);

        return result;
    }

    private void buildTrains(List<Map<String, Object>> staffInfo, List<Map<String, Object>> trainInfos,
                             List<Train> trains) {
        Map<String, Map<String, Object>> staffMap = getTempMap(staffInfo);

        Map<String, Train> trainMap = new HashMap<String, Train>();
        Map<String, Course> courseMap = new HashMap<String, Course>();
        if (trainInfos != null) {
            for (Map<String, Object> temp : trainInfos) {
                buildTrainMap(trainMap, temp, courseMap);
                buildStaffScore(staffMap, temp);
                //后续判断是否及格Double passScore = Double.valueOf(String.valueOf(temp.get("passScore")));
            }

            for (Map.Entry<String, Train> temp : trainMap.entrySet()) {
                trains.add(temp.getValue());
            }
        }
    }

    private void buildTrainMap(Map<String, Train> trainMap, Map<String, Object> temp,
                               Map<String, Course> courseMap) {
        String trainName = String.valueOf(temp.get("trainName"));
        Train train = trainMap.get(trainName);
        if (train == null) {
            train = new Train();
            train.setName(trainName);
            train.setCourses(new ArrayList<Course>());
            trainMap.put(trainName, train);
        }

        String courseName = String.valueOf(temp.get("courseName"));
        Integer sourseScore = Integer.valueOf(String.valueOf(temp.get("sourseScore")));
        Course course = courseMap.get(trainName + "-" + courseName);
        if (course == null) {
            course = new Course();
            course.setCourseID(courseName);
            course.setCourseName(courseName + "(" + sourseScore + "分)");
            train.getCourses().add(course);
            courseMap.put(trainName + "-" + courseName, course);
        }
    }

    private void buildStaffScore(Map<String, Map<String, Object>> staffMap, Map<String, Object> temp) {
        String staffID = String.valueOf(temp.get("staffID"));
        Double score = Double.valueOf(String.valueOf(temp.get("score")));
        String courseName = String.valueOf(temp.get("courseName"));

        Map<String, Object> staff = staffMap.get(staffID);
        staff.put(courseName, formatD2.format(score));
    }

    private Map<String, Map<String, Object>> getTempMap(List<Map<String, Object>> staffInfo) {
        Map<String, Map<String, Object>> staffMap = new HashMap<String, Map<String, Object>>();
        if (staffInfo != null) {
            for (Map<String, Object> temp : staffInfo) {
                staffMap.put(String.valueOf(temp.get("staffID")), temp);
            }
        }
        return staffMap;
    }
}
