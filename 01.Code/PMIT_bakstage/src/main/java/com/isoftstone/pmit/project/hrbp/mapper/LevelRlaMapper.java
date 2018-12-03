package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface LevelRlaMapper {
    public List<LevelTreeNode> queryLevelRlaNode(Map<String, Object> queryMap);

    public void addLevelRlaNode(Map<String, Object> queryMap);

    public void deleteLevelRlaNode(Map<String, Object> queryMap);

    public void updateLevelRlaNode(Map<String, Object> queryMap);

    public void moveLevelRlaNode(Map<String, Object> queryMap);
}
