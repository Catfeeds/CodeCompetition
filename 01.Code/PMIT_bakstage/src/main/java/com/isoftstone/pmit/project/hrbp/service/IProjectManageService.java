package com.isoftstone.pmit.project.hrbp.service;


import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TableInfo;

import java.util.Map;

public interface IProjectManageService {
    public TableInfo queryProjects(ProjectTreeNode parameter);

    public void addProjectNode(Map<String,Object> queryMap);
}
