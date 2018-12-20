package com.isoftstone.pmit.system.exceloperation.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ScoreCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelCourseMapper {

    List<ScoreCourse> getCourse();

    int insertCourse(List<ScoreCourse> newCoursesInsert);

    int updateCourse(List<ScoreCourse> newCoursesUpdate);
}
