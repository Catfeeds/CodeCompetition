package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationParam;

/**
 * @author lf
 */
public interface IExaminationService {

    /**
     * 获取所有考试项
     * @param examinationParam
     * @return
     */
    PageInfo<ExaminationInfo> queryAllExamination(ExaminationParam examinationParam);

    /**
     * 根据examinationID获取考试项
     * @param examinationInfo
     * @return
     */
    ExaminationInfo queryExaminationByID(ExaminationInfo examinationInfo);

    /**
     * 添加考试项
     * @param examinationInfo
     */
    void insertExamination(ExaminationInfo examinationInfo);

    /**
     * 更新考试项
     * @param examinationInfo
     */
    void updateExamination(ExaminationInfo examinationInfo);

    /**
     * 删除考试项
     * @param examinationInfo
     */
    void deleteExamination(ExaminationInfo examinationInfo);
}
