package com.isoftstone.pmit.project.rest.mapper;

import com.isoftstone.pmit.framework.druid.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

@Mapper
public interface RestMapper {
    /**
     *
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
