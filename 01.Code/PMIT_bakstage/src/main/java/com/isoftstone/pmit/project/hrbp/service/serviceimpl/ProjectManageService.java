package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.Project;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectManageService implements IProjectManageService{
    @Autowired
    private ProjectManageMapper mapper;

    @Override
    public List<Project> queryProjectTreeNode(Map<String, Object> queryMap) {
        List<Project> result = new ArrayList<Project>();
        result = mapper.queryProjectTreeNode(queryMap);
        return result;
    }


}
