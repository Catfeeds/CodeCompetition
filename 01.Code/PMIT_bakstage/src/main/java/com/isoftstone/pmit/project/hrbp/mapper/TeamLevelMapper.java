package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeamLevelMapper {
    public List<String> queryLevel(Map<String, Object> queryMap);

    public List<Long> queryProjects(Map<String, Object> queryMap);

    public void addTeamNode(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryLevelNodes(Map<String, Object> queryMap);

    public Long addLevelTreeNode(Map<String, Object> queryMap);

    public void deleteTempNode(Map<String, Object> queryMap);

    public void deleteLevelTreeNode(Map<String, Object> params);

    public Map<String,Object> queryLevels(Integer levelID);

}
