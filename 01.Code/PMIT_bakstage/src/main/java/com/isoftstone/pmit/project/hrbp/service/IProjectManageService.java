package com.isoftstone.pmit.project.hrbp.service;


import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.Project;

import java.util.Map;

public interface IProjectManageService {
    public PageInfo<Project> queryProjectTreeNode(Map<String,Object> queryMap);

    public void addProjectNode(Map<String,Object> queryMap);
}
