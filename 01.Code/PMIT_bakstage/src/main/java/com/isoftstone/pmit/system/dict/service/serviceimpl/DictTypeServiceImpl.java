package com.isoftstone.pmit.system.dict.service.serviceimpl;

import com.isoftstone.pmit.system.dict.entity.DictType;
import com.isoftstone.pmit.system.dict.mapper.DictTypeMapper;
import com.isoftstone.pmit.system.dict.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * 字典类型
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Override
    public List<DictType> selectAllDictType(Map<String,String> param) {
        return dictTypeMapper.selectAllDictType(param);
    }

    @Override
    public void insertSysDictType(DictType dictType) {
        dictTypeMapper.insertSysDictType(dictType);
    }

    @Override
    public void updateSysDictType(DictType dictType) {
        dictTypeMapper.updateSysDictType(dictType);
    }

    @Override
    public void deleteSysDictType(String str) {
        dictTypeMapper.deleteSysDictType(str);
    }

    @Override
    public List<Map<String, String>> selectDictDataByDataTypeByType(String str) {
        return dictTypeMapper.selectDictDataByDataTypeByType(str);
    }
}
