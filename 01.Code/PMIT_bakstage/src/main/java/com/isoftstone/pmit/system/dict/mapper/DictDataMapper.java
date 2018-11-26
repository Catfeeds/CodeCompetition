package com.isoftstone.pmit.system.dict.mapper;

import com.isoftstone.pmit.system.dict.entity.DictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * 字典类型数据
 */
@Mapper
public interface DictDataMapper{

    /**
     * 根据数据类型查询字典数据
     * @param dataType
     * @return
     */
    List<DictData> selectDictDataByDataType(@Param("dataType") String dataType);


    List<DictData> selectDictDataByDataTypeNew(DictData dataType);

    /**
     * 更新字典数据
     * @param dictData
     */
    void updateSysDictData(DictData dictData);

    /**
     * 新增字典数据
     * @param dictData
     */
    void insertSysDictData(DictData dictData);

    /**
     * 删除字典数据
     * @param dictCode
     */
    void deleteSysDictData(String dictCode);


}
