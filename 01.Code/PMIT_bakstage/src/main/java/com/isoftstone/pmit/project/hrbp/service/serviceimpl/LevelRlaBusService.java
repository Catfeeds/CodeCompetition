package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.mapper.LevelRlaBusMapper;
import com.isoftstone.pmit.project.hrbp.service.ILevelRlaBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LevelRlaBusService implements ILevelRlaBusService {
    @Autowired
    private LevelRlaBusMapper mapper;

    private Map<String, List<String>> levelNameMap = new HashMap<String, List<String>>();

    {
        levelNameMap.put("Level", Arrays.asList("bu", "du", "pdu", "region"));
        levelNameMap.put("MainstayLevel", Arrays.asList("bu", "region", "pdu"));
    }

    @Override
    public List<String> queryLevel(Map<String, Object> params) {
        String col = getQueryCol(params, levelNameMap.get("Level"));
        params.put("col", col);
        List<String> levelInfo = mapper.queryLevelNode(params);
        return levelInfo;
    }

    @Override
    public List<String> queryMainstayLevel(Map<String, Object> params) {
        String col = getQueryCol(params, levelNameMap.get("MainstayLevel"));
        params.put("col", col);
        List<String> levelInfo = mapper.queryLevelNode(params);
        return levelInfo;
    }

    private static String getQueryCol(Map<String, Object> params, List<String> levelNames) {
        String col = levelNames.get(levelNames.size() - 1);
        for (String levelName : levelNames) {
            if (params.get(levelName) == null) {
                col = levelName;
                break;
            }
        }
        return col;
    }
}
