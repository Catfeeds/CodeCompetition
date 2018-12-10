package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassParam;

/**
 * @author lf
 */
public interface IClassOpeningService {

    /**
     * 查询所有开课项
     * @param classParam
     * @return
     */
    PageInfo<ClassInfo> queryAllClass(ClassParam classParam);

    /**
     * 删除开课项
     * @param classInfo
     */
    void deleteClass(ClassInfo classInfo);

    /**
     * 添加开课项
     * @param classInfo
     * @return
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
