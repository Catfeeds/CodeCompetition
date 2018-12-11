package com.isoftstone.pmit.project.hrbp.mapper;



import com.isoftstone.pmit.project.hrbp.entity.PersonalScoreParameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface GetScoreMapper {

    List<Map<String,Object>> getColumnName(PersonalScoreParameter parameter);
    List<Map<String,Object>> getAllCourseScore(PersonalScoreParameter parameter);



}
