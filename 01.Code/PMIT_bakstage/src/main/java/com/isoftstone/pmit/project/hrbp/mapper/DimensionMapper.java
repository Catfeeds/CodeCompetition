package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationDimension;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lf
 */
@Mapper
@Repository
public interface DimensionMapper {
    /**
     * 添加维度
     * @param examinationDimension
     */
    void insertDimension(ExaminationDimension examinationDimension);

    /**
     * 添加事务维度
     * @param affairID
     * @param dimensionId
     */
    void insertAffairDimension(@Param("affairID") String affairID,@Param("dimensionId") Integer dimensionId);

    /**
     * 查看所有维度
     * @param affairID
     * @return
     */
    List<ExaminationDimension> queryDimension(String affairID);

    /**
     * 删除事务下已有维度
     * @param examinationAffair
     */
    void deleteAffairAndDimension(ExaminationAffair examinationAffair);
    void deleteDimension(ExaminationDimension examinationDimension);
}
