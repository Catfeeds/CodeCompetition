package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProjectManageMapper {
    public Integer queryProjectSize(Map<String, Object> queryMap);

    public List<Map<String, String>> queryProjects(Map<String, Object> queryMap);

    public void insertProjectNode(Map<String, Object> queryMap);

    public void deleteProject(Map<String, Object> queryMap);
}
