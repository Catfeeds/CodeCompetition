package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;

import java.util.List;
import java.util.Map;

public interface IRelationService {
    public List<Map<String, Object>> queryAllLevel(Map<String, Object> params);

    public List<RelationTreeNode> queryTeamInfo();

    public List<RelationTreeNode> queryPOInfo();

    public List<RelationTreeNode> queryAllTree(Map<String, Object> params);

    public void addTeamNode(Map<String, Object> params);

    public void deleteNode(Map<String, Object> params);

    public void deleteNodeAndChildren(Map<String, Object> params);

    public void updateTreeNode(Map<String, Object> params);

    public void moveTreeNode(Map<String, Object> params);

    public List<RelationTreeNode> queryParentTreesByNode(Map<String, Object> params);

    public List<RelationTreeNode> queryChildTreesByNode(Map<String, Object> params);

    public Map<String, Object> queryCascadeInfo(Map<String, Object> params);

}
