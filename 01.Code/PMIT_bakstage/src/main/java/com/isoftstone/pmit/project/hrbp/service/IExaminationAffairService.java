package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;

import java.util.List;

/**
 * @author lf
 */
public interface IExaminationAffairService {
    /**
     * 获取所有事务
     * @param examinationAffair
     * @return
     */
    List<ExaminationAffair> findAllAffair(ExaminationAffair examinationAffair);

    /**
     * 删除事务
     * @param affairID
     */
    void deleteAffair(String affairID);

    /**
     * 编辑事务
     * @param examinationAffair
     */
    void updateAffair(ExaminationAffair examinationAffair);
    /**
     * 查找单个事务
     * @param affairID
     * @return
     */
    ExaminationAffair findExamAffairByAffairId(String affairID);

    /**
     * 添加考核事务
     * @param examinationAffair
     */
    Integer insertAffair(ExaminationAffair examinationAffair);

    /**
     * 事务所属体系接口
     * @return
     */
    List<ExaminationAffair> findExamAffairSystem();

    /**
     * 事务所属系列接口
     * @return
     */
    List<ExaminationAffair> findExamAffairSeries();

}
