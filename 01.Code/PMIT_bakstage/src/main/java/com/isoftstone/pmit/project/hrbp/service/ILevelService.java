package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;

import java.util.List;
import java.util.Map;

public interface ILevelService {
    public List<TupleData> queryLevelInfo(int levelID);

    public List<String> queryTeamLevel(Map<String, Object> params);

    public List<LevelTreeNode> queryLevelTree(Map<String, Object> params);
}
