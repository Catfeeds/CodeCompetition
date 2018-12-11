package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lf
 */
@Mapper
@Repository
public interface ExaminationMapper {

    /**
     * 获取所有考试项
     * @param examinationParam
     * @return
     */
    List<ExaminationInfo> queryAllExamination(ExaminationParam examinationParam);

    /**
     * 删除考试项
     * @param examinationInfo
     */
    void deleteExamination(ExaminationInfo examinationInfo);

    /**
     * 更新考试项
     * @param examinationInfo
     */
    void updateExamination(ExaminationInfo examinationInfo);

    /**
     * 添加考试项
     * @param examinationInfo
     */
    void insertExamination(ExaminationInfo examinationInfo);

    /**
     * 根据examinationID获取考试项
     * @param examinationInfo
     * @return
     */
    ExaminationInfo queryExaminationByID(ExaminationInfo examinationInfo);
}
