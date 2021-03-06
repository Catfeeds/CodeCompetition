package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MainstayReportMapper {
    public Map<String, Object> queryMainstayProportionChart(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryCostCenterMainstay(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryMainstayByPost(Map<String, Object> queryMap);

    public List<String> queryTrainingStaffs(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryTrainings(Map<String, Object> queryMap);

    public Map<String, Object> queryMainstayAge(Map<String, Object> queryMap);

    public List<Integer> queryBaseStaffs(Map<String, Object> queryMap);

    public List<Map<String,Object>> queryBaseStaffInfo(Map<String, Object> queryMap);

    public List<Map<String,Object>> queryTrainInfo(Map<String, Object> queryMap);

    public List<Map<String,Object>> queryTransactionInfo(Map<String, Object> queryMap);
}
