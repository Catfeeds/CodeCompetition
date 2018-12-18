package com.isoftstone.pmit.project.hrbp.mapper;



import com.isoftstone.pmit.project.hrbp.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface GetScoreMapper {

    List<Map<String,Object>> getColumnName(PersonalScoreParameter parameter);
    List<Map<String,Object>> getAllCourseScore(PersonalScoreParameter parameter);

    List<PersonalScore> getAllPersonalScores(PersonalScoreParam personalScore);

    boolean deletePersonalScores(PersonalScore personalScore);
    boolean updatePersonalScores(PersonalScore parameter);
    List<PersonalTranAndDimeScore> getPersonalTransactionInfo(PersonalScore personalScore);
    void deleteScoreByOpenID(CourseInfo courseInfo);
}
