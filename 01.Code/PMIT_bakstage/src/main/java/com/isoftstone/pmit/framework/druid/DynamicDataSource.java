package com.isoftstone.pmit.framework.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 用来保存数据源与获取数据源
     */
    private static final ThreadLocal<String> CONTEXTHOLDER = new ThreadLocal<String>();

    public DynamicDataSource(DataSource masterDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(masterDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        CONTEXTHOLDER.set(dataSource);
    }

    public static String getDataSource() {
        return CONTEXTHOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXTHOLDER.remove();
    }
}
