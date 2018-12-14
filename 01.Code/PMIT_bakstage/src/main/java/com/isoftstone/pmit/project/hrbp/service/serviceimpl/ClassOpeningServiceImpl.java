package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.CourseInfo;
import com.isoftstone.pmit.project.hrbp.entity.CourseParam;
import com.isoftstone.pmit.project.hrbp.mapper.ClassOpeningMapper;
import com.isoftstone.pmit.project.hrbp.service.IClassOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lf
 */
@Service
public class ClassOpeningServiceImpl implements IClassOpeningService {

    @Autowired
    private ClassOpeningMapper classOpeningMapper;

    /**
     *  查询所有课程
     * @param courseParam
     * @return
     */
    @Override
    public PageInfo<CourseInfo> queryAllClass(CourseParam courseParam) {
        PageHelper.startPage(courseParam.getPageInfo().getCurrPage(), courseParam.getPageInfo().getPageSize());
        String sortType = courseParam.getPageInfo().getSortType();
        String sortColumn = courseParam.getPageInfo().getSortColumn();
        if (null != sortColumn && sortColumn != "" &&sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<CourseInfo> courseInfoList = classOpeningMapper.queryAllClass(courseParam);
        PageInfo<CourseInfo> classInfoPageInfo = new PageInfo<>(courseInfoList);
        return classInfoPageInfo ;
    }

    /**
     * 删除开课项
     * @param courseInfo
     */
    @Override
    public void deleteClass(CourseInfo courseInfo) {
        classOpeningMapper.deleteClass(courseInfo);
    }

    /**
     * 添加开课项
     * @param courseInfo
     */
    @Override
    public void insertClass(CourseInfo courseInfo) {
        classOpeningMapper.insertClass(courseInfo);
    }

    /**
     * 根据OpeningID查找开课项
     * @param courseInfo
     * @return
     */
    @Override
    public CourseInfo findClassByID(CourseInfo courseInfo) {
        return classOpeningMapper.findClassByID(courseInfo);
    }

    /**
     * 更新开课项
     * @param courseInfo
     */
    @Override
    public void updateClass(CourseInfo courseInfo) {
        classOpeningMapper.updateClass(courseInfo);
    }
}
