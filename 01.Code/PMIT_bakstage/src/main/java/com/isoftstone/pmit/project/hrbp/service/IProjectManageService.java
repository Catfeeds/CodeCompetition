package com.isoftstone.pmit.project.hrbp.service;


import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TableInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IProjectManageService {
    public TableInfo queryProjects(ProjectTreeNode parameter,String pmName,String projectName);

    public ProjectTreeNode queryProjectLevel(Set<String> projectIDs);

    public void addProjectNode(Map<String,Object> queryMap);
}
