package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ClassInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lf
 */
@Mapper
@Repository
public interface ClassOpeningMapper {

    /**
     * 查询所有课程
     * @param classParam
     * @return
     */
    List<ClassInfo> queryAllClass(ClassParam classParam);

    /**
     * 删除开课项
     * @param classInfo
     */
    void deleteClass(ClassInfo classInfo);

    /**
     * 添加开课项
     * @param classInfo
     */
    void insertClass(ClassInfo classInfo);

    /**
     * 根据OpeningID查找开课项
     * @param classInfo
     * @return
     */
    ClassInfo findClassByID(ClassInfo classInfo);

    /**
     * 更新开课项
     * @param classInfo
     */
    void updateClass(ClassInfo classInfo);
}
