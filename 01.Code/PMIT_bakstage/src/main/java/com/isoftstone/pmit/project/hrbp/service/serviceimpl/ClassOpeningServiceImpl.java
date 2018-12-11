package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassInfo;
import com.isoftstone.pmit.project.hrbp.entity.ClassParam;
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
     * @param classParam
     * @return
     */
    @Override
    public PageInfo<ClassInfo> queryAllClass(ClassParam classParam) {
        PageHelper.startPage(classParam.getPageInfo().getCurrPage(),classParam.getPageInfo().getPageSize());
        String sortType = classParam.getPageInfo().getSortType();
        String sortColumn = classParam.getPageInfo().getSortColumn();
        if (null != sortColumn && sortColumn != "" &&sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<ClassInfo> classInfoList = classOpeningMapper.queryAllClass(classParam);
        PageInfo<ClassInfo> classInfoPageInfo = new PageInfo<>(classInfoList);
        return classInfoPageInfo ;
    }

    /**
     * 删除开课项
     * @param classInfo
     */
    @Override
    public void deleteClass(ClassInfo classInfo) {
        classOpeningMapper.deleteClass(classInfo);
    }

    /**
     * 添加开课项
     * @param classInfo
     */
    @Override
    public void insertClass(ClassInfo classInfo) {
        classOpeningMapper.insertClass(classInfo);
    }

    /**
     * 根据OpeningID查找开课项
     * @param classInfo
     * @return
     */
    @Override
    public ClassInfo findClassByID(ClassInfo classInfo) {
        return classOpeningMapper.findClassByID(classInfo);
    }

    /**
     * 更新开课项
     * @param classInfo
     */
    @Override
    public void updateClass(ClassInfo classInfo) {
        classOpeningMapper.updateClass(classInfo);
    }
}
