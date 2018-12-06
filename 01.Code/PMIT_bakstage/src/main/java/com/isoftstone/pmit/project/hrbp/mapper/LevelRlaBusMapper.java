package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface LevelRlaBusMapper {
    public List<String> queryLevelNode(Map<String, Object> queryMap);
}
