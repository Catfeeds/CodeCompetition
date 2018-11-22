package com.isoftstone.pmit.system.dict.mapper;

import com.isoftstone.pmit.system.dict.entity.DictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * 字典类型
 */
@Mapper
public interface DictTypeMapper {

    /**
     * 查询字典类型
     * @param param
     * @return
     */
    List<DictType> selectAllDictType(Map<String,String> param);

    /**
     * 新增字典类型
     * @param dictType
     */
    void insertSysDictType(DictType dictType);

    /**
     * 更新字典类型
     * @param dictType
     */
    void updateSysDictType(DictType dictType);

    /**
     * 删除字典类型
     * @param str
     */
    void deleteSysDictType(String str);

    /**
     * 根据字典类型查询字典数据表
     * @param srr
     * @return
     */
    List<Map<String,String>> selectDictDataByDataTypeByType(String srr);
}
