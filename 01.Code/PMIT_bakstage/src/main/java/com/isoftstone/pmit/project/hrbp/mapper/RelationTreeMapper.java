package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RelationTreeMapper {

    public List<RelationTreeNode> queryLevelTree();

    public List<RelationTreeNode> queryRelationNode(Map<String, Object> queryMap);

    public void addTeamLevelNode(Map<String, Object> queryMap);

    public void deleteLevelRlaNode(Map<String, Object> queryMap);

    public void updateLevelRlaNode(Map<String, Object> queryMap);

    public void moveLevelRlaNode(Map<String, Object> queryMap);


}
