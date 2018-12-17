package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RelationTreeMapper {
    public List<Map<String,Object>> queryAllLevel(Map<String, Object> queryMap);

    public List<RelationTreeNode> queryTree();

    public void addTeamNode(Map<String, Object> queryMap);

    public void deleteNode(Map<String, Object> queryMap);

    public void deleteNodeAndChildren(Map<String, Object> queryMap);

    public void updateTreeNode(Map<String, Object> queryMap);

    public void moveTreeNode(Map<String, Object> queryMap);

    public List<RelationTreeNode> queryNodes(Map<String, Object> queryMap);
}
