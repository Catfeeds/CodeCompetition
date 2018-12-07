package com.isoftstone.pmit.system.exceloperation.mapper;

import com.isoftstone.pmit.project.hrbp.entity.TechnicalInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelTechnicalInfoMapper {

    int insertTechnicalInformation(List<TechnicalInformation> insertTechnicalInformation);
}
