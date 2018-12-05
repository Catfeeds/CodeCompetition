package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lf
 */
@Mapper
@Repository
public interface ExaminationAffairMapper {
    /**
     * 获取所有事务
     * @return
     */
    List<ExaminationAffair> findAllAffair(ExaminationAffair examinationAffair);

    /**
     * 删除事务
     * @param affairID
     */
    void deleteAffair(@Param("affairID") Integer affairID);

    /**
     * 更新事务
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
     * 事务所属体系接口
     * @return
     */
    List<ExaminationAffair> findExamAffairSystem();

    /**
     * 查询所属系列
     * @return
     */
    List<ExaminationAffair> findExamAffairSeries();
}
