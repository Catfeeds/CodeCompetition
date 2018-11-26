package com.isoftstone.pmit.system.dict.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.system.dict.entity.DictData;
import com.isoftstone.pmit.system.dict.mapper.DictDataMapper;
import com.isoftstone.pmit.system.dict.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author yihur
 * 字典类型数据
 */
@Service("DictDataService")
public class DictDataServiceImpl implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public PageInfo<DictData> selectDictDataByDataType(int pageNum, int pageSize, DictData dictData) {
        PageHelper.startPage(pageNum, pageSize);
        List<DictData> userDomains = dictDataMapper.selectDictDataByDataTypeNew(dictData);
        PageInfo<DictData> result = new PageInfo<>(userDomains);
        return result;
    }

    /**
     *
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<DictData> findAllDataType(int pageNum, int pageSize,String dictType) {
        PageHelper.startPage(pageNum, pageSize);
        List<DictData> userDomains = dictDataMapper.selectDictDataByDataType(dictType);
        PageInfo<DictData> result = new PageInfo<>(userDomains);
        return result;
    }

    @Override
    public void updateSysDictData(DictData dictData) {
        dictDataMapper.updateSysDictData(dictData);
    }

    @Override
    public void insertSysDictData(DictData dictData) {
        dictDataMapper.insertSysDictData(dictData);
    }

    @Override
    public void deleteSysDictData(String dictCode) {
        dictDataMapper.deleteSysDictData(dictCode);
    }


}
