package com.isoftstone.pmit.common.operationlog;

import com.isoftstone.pmit.common.operationlog.logbuilder.BaseLogBuilder;
import com.isoftstone.pmit.common.operationlog.logbuilder.TestBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LogBuilderManager {
    private  Map<String,ILogBuilder> logBuilderMap = new HashMap<String,ILogBuilder>();

    {
        logBuilderMap.put("http://localhost:8080/system/test/selectTestData",new TestBuilder());
    }

    public ILogBuilder getLogBuilderByUrl(String url){
        ILogBuilder logBuilder = logBuilderMap.get(url);
        if(logBuilder == null){
            logBuilder = new BaseLogBuilder();
        }
        return logBuilder;
    }
}
