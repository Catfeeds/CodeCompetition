package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.Project;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectManageService implements IProjectManageService,Serializable {

    @Autowired
    private ProjectManageMapper mapper;

    @Override
    public PageInfo<Project> queryProjectTreeNode(Map<String, Object> queryMap) {
        PageHelper.startPage((Integer) queryMap.get("pageNo"), (Integer) queryMap.get("pageSize"));

        List<Project> resultList = new ArrayList<Project>();
        resultList = mapper.queryProjectTreeNode(queryMap);

        return new PageInfo<Project>(resultList);
    }

    @Override
    public void addProjectNode(Map<String, Object> queryMap) {
        mapper.addProjectNode(queryMap);
    }
}
