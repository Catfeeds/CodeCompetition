package com.isoftstone.pmit.project.hrbp.service;


import com.isoftstone.pmit.project.hrbp.entity.Project;

import java.util.List;
import java.util.Map;

public interface IProjectManageService {
    public List<Project> queryProjectTreeNode(Map<String,Object> queryMap);


}
