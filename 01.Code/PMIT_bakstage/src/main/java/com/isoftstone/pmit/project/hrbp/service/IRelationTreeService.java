package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;

import java.util.List;
import java.util.Map;

public interface IRelationTreeService {
    public List<RelationTreeNode> queryTeamTree();

    public void addTeamNode(Map<String, Object> params);

    public void deleteNode(Map<String, Object> params);

    public void deleteNodeAndChildren(Map<String, Object> params);

    public void updateTreeNode(Map<String, Object> params);

    public void moveTreeNode(Map<String, Object> params);

    public List<RelationTreeNode> queryLevelRlaLeafNode(List<Map<String, Object>> nodeList);
}
