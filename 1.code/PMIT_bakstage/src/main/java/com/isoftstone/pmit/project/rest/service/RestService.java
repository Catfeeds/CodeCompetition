package com.isoftstone.pmit.project.rest.service;

import com.isoftstone.pmit.framework.druid.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * 外部调用接口
 */
public interface RestService {

    /**
     * 根据项目ID获取KPI信息
     * @param prjID
     * @return
     */
    List<Map<String,Object>> getKPIbyPrjID(String prjID);

    /**
     *
     * @param seqID
     * @return
     */
    List<Map<String,Object>> getKPIAnalyseResultByKPI(String seqID);

}
