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
    List<ExaminationAffair> findAllAffair();

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
     * 模糊查询所属体系
     * @param examinationAffair
     * @return
     */
    List<ExaminationAffair> findExamAffairSystem(ExaminationAffair examinationAffair);
}
