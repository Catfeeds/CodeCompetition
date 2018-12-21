package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RelationMapper {
    public List<Map<String, Object>> queryAllLevel(Map<String, Object> queryMap);

    public List<RelationTreeNode> queryTeamInfo();

    public List<RelationTreeNode> queryPOInfo();

    public List<RelationTreeNode> queryTree(Map<String, Object> queryMap);

    public void addTeamNode(Map<String, Object> queryMap);

    public void deleteNode(Map<String, Object> queryMap);

    public void deleteNodeAndChildren(Map<String, Object> queryMap);

    public void updateTreeNode(Map<String, Object> queryMap);

    public void moveTreeNode(Map<String, Object> queryMap);

    public List<RelationTreeNode> queryNodes(Map<String, Object> queryMap);

    public PostOptionInfo queryPostOption(Map<String, Object> queryMap);

    //public List<Integer> queryTeams(Map<String, Object> queryMap);
}
