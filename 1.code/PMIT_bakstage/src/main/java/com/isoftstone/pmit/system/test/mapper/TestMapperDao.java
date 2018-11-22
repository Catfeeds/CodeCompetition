package com.isoftstone.pmit.system.test.mapper;

import com.isoftstone.pmit.system.test.entity.TestData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestMapperDao {
    public List<TestData> queryTestData(Map<String,Object> queryParam);
}
