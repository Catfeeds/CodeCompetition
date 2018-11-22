package com.isoftstone.pmit.project.rest.service.serviceimpl;

import com.isoftstone.pmit.framework.druid.DataSource;
import com.isoftstone.pmit.project.rest.mapper.RestMapper;
import com.isoftstone.pmit.project.rest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 外部接口的实现类
 */
@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private RestMapper restMapper;

    @Override
    @DataSource(name="second")
    public List<Map<String, Object>> getKPIbyPrjID(String prjID) {
        return restMapper.getKPIbyPrjID(prjID);
    }

    @Override
    @DataSource(name="second")
    public List<Map<String, Object>> getKPIAnalyseResultByKPI(String seqID) {
        return restMapper.getKPIAnalyseResultByKPI(seqID);
    }

}
