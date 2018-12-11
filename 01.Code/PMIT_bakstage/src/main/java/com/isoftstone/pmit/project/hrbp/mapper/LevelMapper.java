package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface LevelMapper {
    public Map<String,Object> queryLevels(Integer levelID);

    public List<String> queryTeamLevel(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryLevelTeamNodes(Map<String, Object> queryMap);

    public List<Map<String, Object>> queryLevelPONodes(Map<String, Object> queryMap);
}
