package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationParam;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationMapper;
import com.isoftstone.pmit.project.hrbp.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lf
 */

@Service
public class ExaminationServiceImpl implements IExaminationService {

    @Autowired
    private ExaminationMapper examinationMapper;

    /**
     * 获取所有考试项
     * @param examinationParam
     * @return
     */
    @Override
    public PageInfo<ExaminationInfo> queryAllExamination(ExaminationParam examinationParam) {
        //分页
        PageHelper.startPage(examinationParam.getPageInfo().getCurrPage(), examinationParam.getPageInfo().getPageSize());
        String sortType = examinationParam.getPageInfo().getSortType();
        String sortColumn = examinationParam.getPageInfo().getSortColumn();
        if (null != sortColumn && sortColumn != "" && sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<ExaminationInfo> examinationInfoList = examinationMapper.queryAllExamination(examinationParam);
        PageInfo<ExaminationInfo> classInfoPageInfo = new PageInfo<>(examinationInfoList);
        return classInfoPageInfo;
    }

    /**
     * 根据examinationID获取考试项
     * @param examinationInfo
     * @return
     */
    @Override
    public ExaminationInfo queryExaminationByID(ExaminationInfo examinationInfo) {
        return examinationMapper.queryExaminationByID(examinationInfo);
    }

    /**
     * 添加考试项
     * @param examinationInfo
     */
    @Override
    public void insertExamination(ExaminationInfo examinationInfo) {
        examinationMapper.insertExamination(examinationInfo);
    }

    /**
     * 更新考试项
     * @param examinationInfo
     */
    @Override
    public void updateExamination(ExaminationInfo examinationInfo) {
        examinationMapper.updateExamination(examinationInfo);
    }

    /**
     * 删除考试项
     * @param examinationInfo
     */
    @Override
    public void deleteExamination(ExaminationInfo examinationInfo) {
        examinationMapper.deleteExamination(examinationInfo);
    }
}
