package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


@Service
public class ProjectManageService implements IProjectManageService, Serializable {

    private Map<String, List<TableHead>> tableHeadMap;

    {
        tableHeadMap = new HashMap<String, List<TableHead>>();
        List<TableHead> tableHeadList = new ArrayList<TableHead>();

        tableHeadList.add(new TableHead("porduceLine", "产品线", 1, false));
        tableHeadList.add(new TableHead("BU", "BU", 1, false));
        tableHeadList.add(new TableHead("BD", "BD", 1, false));

        tableHeadMap.put("2012", tableHeadList);
    }

    @Autowired
    private ProjectManageMapper mapper;

    @Override
    public TableInfo queryProjects(ProjectTreeNode parameter) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("nodeIDs", TreeUtil.getLeafByNode(parameter));
        List<ProjectTreeNode> projects = mapper.queryNodes(queryMap);
        TableInfo tableInfo = new TableInfo();

        if (projects != null) {
            Map<Integer, ProjectTreeNode> levelNodeInfo = getLevelNodesMap(parameter, queryMap);

            List<TableHead> generalCol = tableHeadMap.get("2012");
            List<TableHead> tableHeadList = getTableHeads(generalCol);
            tableInfo.setTableHeads(tableHeadList);

            List<TableRow> tableRows = new ArrayList<TableRow>();
            tableInfo.setTableRows(tableRows);


            for (ProjectTreeNode projectTreeNode : projects) {
                TableRow tableRow = getTableRow(generalCol, levelNodeInfo, projectTreeNode);
                tableRows.add(tableRow);
            }
        }

        return tableInfo;
    }

    private Map<Integer, ProjectTreeNode> getLevelNodesMap(ProjectTreeNode parameter, Map<String, Object> queryMap) {
        Map<Integer, ProjectTreeNode> levelNodeInfo = new HashMap<Integer, ProjectTreeNode>();
        queryMap.put("nodeIDs", getLevelNodeIDs(parameter));
        List<ProjectTreeNode> levelNodes = mapper.queryNodes(queryMap);
        if(levelNodes != null){
            for(ProjectTreeNode levelNode:levelNodes){
                levelNodeInfo.put(levelNode.getNodeID(),levelNode);
            }
        }
        return levelNodeInfo;
    }

    private TableRow getTableRow(List<TableHead> generalCol, Map<Integer, ProjectTreeNode> levelNodeInfo, ProjectTreeNode projectTreeNode) {
        TableRow tableRow = new TableRow();

        Map<String, String> dataMap = new HashMap<String, String>();
        tableRow.setValues(dataMap);

        List<Integer> pathNodes = TreeUtil.getPathNodes(projectTreeNode.getNodePath());
        Collections.reverse(pathNodes);
        for (int index = 0; index < generalCol.size(); index++) {
            ProjectTreeNode levelNode = levelNodeInfo.get(pathNodes.get(index));
            String value = levelNode == null ? null:levelNode.getNodeName();
            dataMap.put(generalCol.get(index).getColID(),value);
        }
        dataMap.put("projectID",projectTreeNode.getProjcetID());
        dataMap.put("projectName",projectTreeNode.getProjectName());
        dataMap.put("PMID",projectTreeNode.getPmID());
        dataMap.put("PMName",projectTreeNode.getPmName());
        dataMap.put("nodeID",String.valueOf(projectTreeNode.getNodeID()));
        dataMap.put("nodePath",projectTreeNode.getNodePath());
        return tableRow;
    }

    private List<TableHead> getTableHeads(List<TableHead> generalCol) {
        List<TableHead> tableHeadList = new ArrayList<TableHead>();
        tableHeadList.addAll(generalCol);
        tableHeadList.add(new TableHead("projectName","projectName",1,false));
        tableHeadList.add(new TableHead("PMName","PMName",1,false));
        tableHeadList.add(new TableHead("projectID","projectID",1,true));
        tableHeadList.add(new TableHead("PMID","PMID",1,true));
        tableHeadList.add(new TableHead("nodeID","nodeID",1,true));
        tableHeadList.add(new TableHead("nodePath","nodePath",1,true));
        return tableHeadList;
    }

    private Set<Integer>  getLevelNodeIDs(BaseTreeNode root) {
        Set<Integer> levelNodeIDs = new HashSet<Integer>();
        if (null != root) {
            if (!root.isLeafNode()) {
                levelNodeIDs.add(root.getNodeID());
                List<BaseTreeNode> childList = root.getChildList();
                if (childList != null) {
                    for (BaseTreeNode temp : childList) {
                        levelNodeIDs.addAll(getLevelNodeIDs(temp));
                    }
                }
            }
        }
        return levelNodeIDs;
    }

    @Override
    public void addProjectNode(Map<String, Object> queryMap) {
        mapper.addProjectNode(queryMap);
    }
}
