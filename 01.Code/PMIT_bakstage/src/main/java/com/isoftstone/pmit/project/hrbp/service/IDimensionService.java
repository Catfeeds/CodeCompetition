package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationDimension;

import java.util.List;

/**
 * @author lf
 */
public interface IDimensionService {
    /**
     * 批量保存维度
     * @param examinationAffair
     * @param examinationDimensionList
     */
    void insertDimension(ExaminationAffair examinationAffair, List<ExaminationDimension> examinationDimensionList);

    /**
     * 查看所有维度
     * @param affairID
     * @return
     */
    List<ExaminationDimension> queryDimension(String affairID);

}
