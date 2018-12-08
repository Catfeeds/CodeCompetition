package com.isoftstone.pmit.project.hrbp.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface GetScoreMapper {

    List<Map<String,Object>> getColumnName(Map<String,String> parameter);
    List<Map<String,Object>> getAllTransactionScore(Map<String,String> parameter);
    List<Map<String,Object>> getAllCourseScore(Map<String,String> parameter);



}
