package com.isoftstone.pmit.project.hrbp.service;


import java.util.List;
import java.util.Map;

public interface IProjectManageService {
    public List<String> queryLevel(Map<String, Object> params);

    public Map<String, Object> queryProjects(Map<String, Object> parameter);

    public Map<String, Object> queryProject(Map<String, Object> parameter);

    public Long addProjectNode(Map<String, Object> queryMap);

    public void deleteProject(Map<String, Object> queryMap);

    public List<Map<String, String>> queryPMs(Map<String, Object> queryMap);
}
