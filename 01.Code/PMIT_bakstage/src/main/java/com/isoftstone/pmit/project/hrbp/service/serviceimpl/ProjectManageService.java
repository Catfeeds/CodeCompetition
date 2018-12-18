package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.mapper.RelationTreeMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProjectManageService implements IProjectManageService {

    @Autowired
    private ProjectManageMapper projectManageMapper;

    @Autowired
    private RelationTreeMapper relationTreeMapper;

    @Override
    public Map<String, Object> queryProjects(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<String, Object>();

        // TODO: 2018/12/18 调用剑哥接口，获取项目组ID

        List<Integer> teamIDs = (List<Integer>) params.get("teamIDs");
        Integer totleSize = teamIDs != null ? teamIDs.size() : 0;
        result.put("totleSize", totleSize);

        if (totleSize != 0) {
            Integer pagenNo = Integer.valueOf(String.valueOf(params.get("pageNo")));
            Integer pageSize = Integer.valueOf(String.valueOf(params.get("pageSize")));
            params.put("rowStart", (pagenNo - 1) * pageSize);
            params.put("rowEnd", (pagenNo) * pageSize - 1);
            List<Map<String, String>> datas = projectManageMapper.queryProjects(params);
            result.put("datas", datas);
        }

        return result;
    }

    @Override
    public void deleteProject(Map<String, Object> queryMap) {
        projectManageMapper.deleteProject(queryMap);
    }

    @Override
    public List<Map<String, String>> queryPMs(Map<String, Object> queryMap) {
        return projectManageMapper.queryPMs(queryMap);
    }

//    private void buildTeamQueryMap(Map<String, Object> params, Map<String, Object> queryMap) {
//        Set<String> teamPaths = new HashSet<String>();
//
//        List<String> teams = (List<String>) params.get("teams");
//        if (null != teams) {
//            for (String path : teams) {
//                teamPaths.add(path);
//            }
//        }
//
//        queryMap.put("teamPaths", teamPaths);
//    }
}
