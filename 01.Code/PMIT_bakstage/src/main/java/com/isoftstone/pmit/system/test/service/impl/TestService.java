package com.isoftstone.pmit.system.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.system.test.entity.TestData;
import com.isoftstone.pmit.system.test.mapper.TestMapperDao;
import com.isoftstone.pmit.system.test.service.ITestSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService implements ITestSevice{
    @Autowired
    private TestMapperDao dao;

    @Override
    public PageInfo<TestData> queryTestData(int pageNum, int pageSize, Map<String, Object> param) {
        PageHelper.startPage(pageNum, pageSize);
        List<TestData> datas = dao.queryTestData(param);
        PageInfo<TestData> result = new PageInfo<TestData>(datas);
        return result;
    }
}
