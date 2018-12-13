package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;

import java.util.List;
import java.util.Map;

public interface ITeamLevelService {
    public List<LevelTreeNode> queryLevelTree(Map<String, Object> params);

    public Long addLevelTreeNode(Map<String, Object> params);

    public void deleteLevelTreeNode(Map<String, Object> params);

    //↓↓↓↓↓↓↓↓↓↓↓内部使用接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    public List<TupleData> queryLevelInfo(int levelID);

    public List<String> queryLevel(Map<String, Object> params);

    public List<Long> queryProjects(Map<String, Object> params);

    public void addTeamNode(Map<String, Object> params);
}
