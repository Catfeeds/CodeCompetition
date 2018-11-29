package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProjectManageMapper {
    public List<ProjectTreeNode> queryNodes(Map<String, Object> queryMap);

	public void addProjectNode(Map<String, Object> queryMap);
}
