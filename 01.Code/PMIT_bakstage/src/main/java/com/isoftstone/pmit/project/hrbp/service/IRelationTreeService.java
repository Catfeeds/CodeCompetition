package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;

import java.util.List;
import java.util.Map;

public interface IRelationTreeService {
    public List<RelationTreeNode> queryTeamLevelTree();

    public void addTeamLevelNode(Map<String, Object> queryMap);

    public void deleteNode(String nodePath, Integer nodeID);

    public void deleteNodeAndChildren(Map<String,Object> params);

    public void moveLevelRlaNode(Map<String, Object> queryMap, String tableName);

    public void updateLevelRlaNode(Map<String, Object> queryMap, String tableName);

    public List<RelationTreeNode> queryLevelRlaLeafNode(List<Map<String, Object>> nodeList, String tableName);
}
