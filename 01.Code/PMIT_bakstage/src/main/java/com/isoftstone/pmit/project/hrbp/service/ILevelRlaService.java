package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;

import java.util.List;
import java.util.Map;

public interface ILevelRlaService {
    public List<LevelTreeNode> queryLevelRlaNode(List<Map<String, Object>> nodeList, String tableName);

    public void addLevelRlaNode(Map<String, Object> queryMap, String tableName);

    public void deleteLevelRlaNode(String nodePath, Integer nodeID, String tableName);

    public void moveLevelRlaNode(Map<String, Object> queryMap, String tableName);

    public void updateLevelRlaNode(Map<String, Object> queryMap, String tableName);
}
