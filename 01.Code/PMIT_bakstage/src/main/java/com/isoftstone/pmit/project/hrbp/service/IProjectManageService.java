package com.isoftstone.pmit.project.hrbp.service;


import java.util.List;
import java.util.Map;

public interface IProjectManageService {
    public Map<String, Object> queryProjects(Map<String, Object> parameter);

    public void deleteProject(Map<String, Object> queryMap);

    public List<Map<String, String>> queryPMs(Map<String, Object> queryMap);

    public Long addProjectNode(Map<String, Object> queryMap);
}
