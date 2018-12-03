package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;

import java.util.List;

/**
 * @author lf
 */
public interface IExaminationAffairService {
    /**
     * 获取所有事务
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ExaminationAffair> findAllAffair(Integer pageNum, Integer pageSize);

    /**
     * 删除事务
     * @param affairID
     */
    void deleteAffair(Integer affairID);

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
    ExaminationAffair findExamAffairByAffairId(Integer affairID);

    /**
     * 添加考核事务
     * @param examinationAffair
     */
    void insertAffair(ExaminationAffair examinationAffair);

    /**
     * 模糊查询
     * @param examinationAffair
     * @return
     */
    List<ExaminationAffair> findExamAffairSystem(ExaminationAffair examinationAffair);
}
