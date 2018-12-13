package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProjectManageService implements IProjectManageService {

    @Autowired
    private ProjectManageMapper mapper;


    @Override
    public List<String> queryLevel(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map<String, Object> queryProjects(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer totleSize = mapper.queryProjectSize(params);
        result.put("totleSize", totleSize);
        if (totleSize != 0) {
            Integer pagenNo = Integer.valueOf(String.valueOf(params.get("pageNo")));
            Integer pageSize = Integer.valueOf(String.valueOf(params.get("pageSize")));
            params.put("rowStart", (pagenNo - 1) * pageSize);
            params.put("rowEnd", (pagenNo) * pageSize - 1);
            List<Map<String, String>> datas = mapper.queryProjects(params);
            result.put("datas", datas);
        }

        return result;
    }

    @Override
    public Map<String, Object> queryProject(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<String, Object>();
        params.put("rowStart", 0);
        params.put("rowEnd", 1);
        List<Map<String, String>> datas = mapper.queryProjects(params);
        result.put("datas", datas);
        return result;
    }

    @Override
    public Long addProjectNode(Map<String, Object> queryMap) {
        return mapper.insertProjectNode(queryMap);
    }

    @Override
    public void deleteProject(Map<String, Object> queryMap) {
        mapper.deleteProject(queryMap);
    }

    @Override
    public List<Map<String, String>> queryPMs(Map<String, Object> queryMap) {
        return mapper.queryPMs(queryMap);
    }


}
