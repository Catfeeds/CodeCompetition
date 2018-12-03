package com.isoftstone.pmit.project.hrbp.entity;

import com.isoftstone.pmit.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lf
 */
public class AffairDimension {
    /** 考核事务 */
    private ExaminationAffair examinationAffair;
    /** 考核事务维度 */
    private List<ExaminationDimension> examinationDimensionList;

    public ExaminationAffair getExaminationAffair() {
        return examinationAffair;
    }

    public void setExaminationAffair(ExaminationAffair examinationAffair) {
        this.examinationAffair = examinationAffair;
    }

    public List<ExaminationDimension> getExaminationDimensionList() {
        return examinationDimensionList;
    }

    public void setExaminationDimensionList(List<ExaminationDimension> examinationDimensionList) {
        this.examinationDimensionList = examinationDimensionList;
    }
}
