package com.isoftstone.pmit.system.exceloperation.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ScoreCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelCourseMapper {

    int insertCourse(List<ScoreCourse> courseList);

    List<ScoreCourse> getCourse();
}
