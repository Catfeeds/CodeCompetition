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


    @Override
    public List<TupleData> queryMainstayProportionChart(Map<String, Object> params) {
        Map<String, Object> datas = mapper.queryMainstayProportionChart(params);
        List<TupleData> proportionChart = new ArrayList<TupleData>();
        buildMainstayNum(datas, proportionChart);
        return proportionChart;
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

    @Override
    public List<TupleData> queryMainstayTraining(Map<String, Object> params) {
        List<String> staffIDs = mapper.queryTrainingStaffs(params);
        staffIDs.remove(null);
        List<Map<String, Object>> trainInfo = mapper.queryTrainings(params);
        List<TupleData> result = new ArrayList<TupleData>();
        buildMainstayPercentage(trainInfo, (double) staffIDs.size(), result, "Other");
        return result;
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

        buildAgFilter(params);
        List<Integer> staffs = mapper.queryBaseStaffs(params);
        Integer totleSize = staffs == null ? 0 : staffs.size();
        result.put("totleSize", totleSize);

        if (staffs != null) {
            Integer pagenNo = Integer.valueOf(String.valueOf(params.get("pageNo")));
            Integer pageSize = Integer.valueOf(String.valueOf(params.get("pageSize")));

            Integer rowStart = (pagenNo - 1) * pageSize;
            rowStart = rowStart > totleSize - 1 ? (totleSize - 1) : rowStart;
            Integer rowEnd = (pagenNo) * pageSize;
            rowEnd = rowEnd > totleSize ? totleSize : rowEnd;

            if (rowStart != rowEnd) {
                staffs = staffs.subList(rowStart, rowEnd);
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("staffs", staffs);
                List<Map<String, Object>> staffInfo = mapper.queryBaseStaffInfo(queryMap);
                List<Map<String, Object>> trainInfo = mapper.queryTrainInfo(queryMap);
                List<Map<String, Object>> transaction = mapper.queryTransactionInfo(queryMap);

                List<Train> trains = new ArrayList<Train>();
                Map<String, Map<String, Object>> staffMap = getTempMap(staffInfo);

                buildTrains(staffMap, trainInfo, trains);
                buildTrains(staffMap, transaction, trains);
                result.put("datas", staffInfo);
                result.put("trains", trains);
            }
        }
        return result;
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

    private void buildTrains(Map<String, Map<String, Object>> staffMap, List<Map<String, Object>> trainInfo,
                             List<Train> trains) {


        Map<String, Train> trainMap = new HashMap<String, Train>();
        Map<String, Course> courseMap = new HashMap<String, Course>();
        if (trainInfo != null) {
            for (Map<String, Object> temp : trainInfo) {
                buildTrainMap(trainMap, temp, courseMap);
                buildStaffScore(staffMap, temp);
            }

            for (Map.Entry<String, Train> temp : trainMap.entrySet()) {
                trains.add(temp.getValue());
            }
        }
    }

    private void buildTrainMap(Map<String, Train> seriesMap, Map<String, Object> temp,
                               Map<String, Course> courseMap) {
        String series = String.valueOf(temp.get("series"));
        Train train = seriesMap.get(series);
        if (train == null) {
            train = new Train();
            train.setName(series);
            train.setCourses(new ArrayList<Course>());
            seriesMap.put(series, train);
        }

        String courseName = String.valueOf(temp.get("training"));
        Course course = courseMap.get(series + "-" + courseName);
        if (course == null) {
            course = new Course();
            course.setCourseID(courseName);
            course.setCourseName(courseName);
            train.getCourses().add(course);
            courseMap.put(series + "-" + courseName, course);
        }
    }

    private void buildStaffScore(Map<String, Map<String, Object>> staffMap, Map<String, Object> temp) {
        String staffID = String.valueOf(temp.get("staffID"));
        Double score = Double.valueOf(String.valueOf(temp.get("score")));
        String courseName = String.valueOf(temp.get("training"));

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

    private void buildAgFilter(Map<String, Object> params) {
        Object ageInfo = params.get("ageInfo");
        if (ageInfo != null) {
            switch (String.valueOf(ageInfo)) {
                case "agelessthan30": {
                    Calendar age30 = Calendar.getInstance();
                    age30.roll(Calendar.YEAR, -30);
                    params.put("ageStart", age30.getTime());
                    break;
                }
                case "age30to40": {
                    Calendar age40 = Calendar.getInstance();
                    age40.roll(Calendar.YEAR, -40);
                    params.put("ageStart", age40.getTime());

                    Calendar age30 = Calendar.getInstance();
                    age30.roll(Calendar.YEAR, -30);
                    params.put("ageEnd", age30.getTime());
                    break;
                }
                case "agegreaterthan40": {
                    Calendar age40 = Calendar.getInstance();
                    age40.roll(Calendar.YEAR, -40);
                    params.put("ageEnd", age40.getTime());
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
