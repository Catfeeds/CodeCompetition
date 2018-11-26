package com.isoftstone.pmit.system.dict.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.system.dict.entity.DictData;

import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * 字典类型数据
 */
public interface DictDataService{

    /**
     * 根据数据类型查询字典数据
     * @param pageNum
     * @param pageSize
     * @param dictData
     * @return
     */
    PageInfo<DictData> selectDictDataByDataType(int pageNum, int pageSize,DictData dictData);


    /**
     * 根据数据类型查询字典数据 带分页参数
     * @param pageNum
     * @param pageSize
     * @param dictType
     * @return
     */
    PageInfo<DictData> findAllDataType(int pageNum, int pageSize,String dictType);

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
