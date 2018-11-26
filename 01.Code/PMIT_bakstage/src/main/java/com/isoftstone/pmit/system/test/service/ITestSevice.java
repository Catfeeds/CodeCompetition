package com.isoftstone.pmit.system.test.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.system.test.entity.TestData;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface ITestSevice {
    public PageInfo<TestData> queryTestData(int pageNum, int pageSize, Map<String,Object> param);
}
