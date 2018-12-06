package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.DataBackUpInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DataBackAndRecovMapper {
    /**
     * 保存基本数据信息
     * @param dataBackUpInfo
     */
   void saveBackUpInfo(DataBackUpInfo dataBackUpInfo);

    /**
     * 查询备份人员信息列表
     * @return
     */
    List<DataBackUpInfo> findAllDataInfoList();


}
