package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.CourseInfo;
import com.isoftstone.pmit.project.hrbp.entity.CourseParam;

/**
 * @author lf
 */
public interface IClassOpeningService {

    /**
     * 查询所有开课项
     * @param courseParam
     * @return
     */
    PageInfo<CourseInfo> queryAllClass(CourseParam courseParam);

    /**
     * 删除开课项
     * @param courseInfo
     */
    void deleteClass(CourseInfo courseInfo);

    /**
     * 添加开课项
     * @param courseInfo
     * @return
     */
    void insertClass(CourseInfo courseInfo);

    /**
     * 根据OpeningID查找开课项
     * @param courseInfo
     * @return
     */
    CourseInfo findClassByID(CourseInfo courseInfo);

    /**
     * 更新开课项
     * @param courseInfo
     */
    void updateClass(CourseInfo courseInfo);
}
