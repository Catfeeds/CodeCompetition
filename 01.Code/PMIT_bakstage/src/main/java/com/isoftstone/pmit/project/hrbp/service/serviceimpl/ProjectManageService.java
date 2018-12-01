package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TableHead;
import com.isoftstone.pmit.project.hrbp.entity.TableInfo;
import com.isoftstone.pmit.project.hrbp.entity.TableRow;
import com.isoftstone.pmit.project.hrbp.mapper.ProjectManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProjectManageService implements IProjectManageService {

    @Autowired
    private ProjectManageMapper mapper;

    private Map<String, List<TableHead>> tableHeadMap;

    {
        tableHeadMap = new HashMap<String, List<TableHead>>();
        List<TableHead> tableHeadList = new ArrayList<TableHead>();

        tableHeadList.add(new TableHead("porduceLine", "产品线", 1, false));
        tableHeadList.add(new TableHead("BU", "BU", 1, false));
        tableHeadList.add(new TableHead("BD", "BD", 1, false));

        tableHeadMap.put("2012", tableHeadList);
    }


    @Override
    public ProjectTreeNode queryProjectLevel(Set<String> projectIDs) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("projectIDs", projectIDs);
        queryMap.put("isProject", true);
        List<ProjectTreeNode> projects = mapper.queryNodes(queryMap);

        ProjectTreeNode root = new ProjectTreeNode();
        root.setNodeID(-1);
        root.setNodeName("root");
        Set<Integer> levelNodeIDs = new HashSet<Integer>();
        buildTreesNoLeafByPath(projects, levelNodeIDs, root);

        queryMap = new HashMap<String, Object>();
        queryMap.put("levelNodeIDs", levelNodeIDs);
        queryMap.put("isProject", false);
        List<ProjectTreeNode> levelNodes = mapper.queryNodes(queryMap);

        fillRootTree(levelNodes, root);
        return root;
    }

    private void buildTreesNoLeafByPath(List<ProjectTreeNode> leafNodes, Set<Integer> nodeIDs,
                                        ProjectTreeNode rootNode) {
        Map<Integer, ProjectTreeNode> tempMap = new HashMap<Integer, ProjectTreeNode>();
        tempMap.put(rootNode.getNodeID(), rootNode);

        if (leafNodes != null) {
            for (ProjectTreeNode leafNode : leafNodes) {
                ProjectTreeNode parentNode = rootNode;

                List<Integer> pathNodeIDs = TreeUtil.getPathNodes(leafNode.getNodePath());
                for (Integer nodeID : pathNodeIDs) {
                    if (tempMap.get(nodeID) == null) {
                        ProjectTreeNode tempNode = new ProjectTreeNode();
                        tempNode.setNodeID(nodeID);
                        tempNode.setChildList(new ArrayList<ProjectTreeNode>());

                        parentNode.getChildList().add(tempNode);
                        tempMap.put(tempNode.getNodeID(), tempNode);
                        nodeIDs.add(tempNode.getNodeID());
                        parentNode = tempNode;
                    }
                }

                List<Integer> childLeafIDList = parentNode.getChildLeafIDList();
                if (childLeafIDList == null) {
                    childLeafIDList = new ArrayList<Integer>();
                }
                childLeafIDList.add(leafNode.getNodeID());
            }
        }
    }

    private void fillRootTree(List<ProjectTreeNode> levelNodes, ProjectTreeNode root) {
        if (levelNodes != null && !levelNodes.isEmpty()) {
            Map<Integer, ProjectTreeNode> levelNodeInfo = new HashMap<Integer, ProjectTreeNode>();
            for (ProjectTreeNode levelNode : levelNodes) {
                levelNodeInfo.put(levelNode.getNodeID(), levelNode);
            }
            buildNodeInfo(root, levelNodeInfo);
        }
    }

    private void buildNodeInfo(ProjectTreeNode root, Map<Integer, ProjectTreeNode> levelNodeInfo) {
        ProjectTreeNode tempNode = levelNodeInfo.get(root.getNodeID());
        if (tempNode != null) {
            fillNodeInfo(tempNode, root);
        }
        List<ProjectTreeNode> childNodes = root.getChildList();
        if (childNodes != null) {
            for (ProjectTreeNode childNode : childNodes) {
                buildNodeInfo(childNode, levelNodeInfo);
            }
        }
    }

    private void fillNodeInfo(ProjectTreeNode source, ProjectTreeNode target) {
        target.setNodeID(source.getNodeID());
        target.setNodeName(source.getNodeName());
        target.setNodePath(source.getNodePath());
        target.setIsProject(source.getIsProject());
        target.setIsLeafNode(source.getIsLeafNode());
    }

    @Override
    public TableInfo queryProjects(ProjectTreeNode parameter, String pmName, String projectName) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("nodeIDs", getLeafByNode(parameter));
        queryMap.put("isProject", true);
        queryMap.put("pmName", pmName);
        queryMap.put("projectName", projectName);
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

    private Set<Integer> getLeafByNode(ProjectTreeNode root) {
        Set<Integer> projectIDs = new HashSet<Integer>();
        if (root != null) {
            if (root.getChildLeafIDList() != null) {
                projectIDs.addAll(root.getChildLeafIDList());
            } else {
                List<ProjectTreeNode> childList = root.getChildList();
                if (childList != null) {
                    for (ProjectTreeNode treeNode : childList) {
                        projectIDs.addAll(getLeafByNode(treeNode));
                    }
                }
            }
        }
        return projectIDs;
    }

    private Map<Integer, ProjectTreeNode> getLevelNodesMap(ProjectTreeNode parameter, Map<String, Object> queryMap) {
        Map<Integer, ProjectTreeNode> levelNodeInfo = new HashMap<Integer, ProjectTreeNode>();
        queryMap.put("nodeIDs", getLevelNodeIDs(parameter));
        queryMap.put("isProject", false);
        List<ProjectTreeNode> levelNodes = mapper.queryNodes(queryMap);
        if (levelNodes != null) {
            for (ProjectTreeNode levelNode : levelNodes) {
                levelNodeInfo.put(levelNode.getNodeID(), levelNode);
            }
        }
        return levelNodeInfo;
    }

    private Set<Integer> getLevelNodeIDs(ProjectTreeNode root) {
        Set<Integer> levelNodeIDs = new HashSet<Integer>();
        if (null != root) {
            if (!root.getIsLeafNode()) {
                levelNodeIDs.add(root.getNodeID());
                List<ProjectTreeNode> childList = root.getChildList();
                if (childList != null) {
                    for (ProjectTreeNode temp : childList) {
                        levelNodeIDs.addAll(getLevelNodeIDs(temp));
                    }
                }
            }
        }
        return levelNodeIDs;
    }

    private TableRow getTableRow(List<TableHead> generalCol, Map<Integer, ProjectTreeNode> levelNodeInfo, ProjectTreeNode projectTreeNode) {
        TableRow tableRow = new TableRow();

        Map<String, String> dataMap = new HashMap<String, String>();
        tableRow.setValues(dataMap);

        List<Integer> pathNodes = TreeUtil.getPathNodes(projectTreeNode.getNodePath());
        Collections.reverse(pathNodes);
        for (int index = 0; index < generalCol.size(); index++) {
            ProjectTreeNode levelNode = levelNodeInfo.get(pathNodes.get(index));
            String value = levelNode == null ? null : levelNode.getNodeName();
            dataMap.put(generalCol.get(index).getColID(), value);
        }
        dataMap.put("projectID", projectTreeNode.getProjcetID());
        dataMap.put("projectName", projectTreeNode.getProjectName());
        dataMap.put("PMID", projectTreeNode.getPmID());
        dataMap.put("PMName", projectTreeNode.getPmName());
        dataMap.put("nodeID", String.valueOf(projectTreeNode.getNodeID()));
        dataMap.put("nodePath", projectTreeNode.getNodePath());
        return tableRow;
    }

    private List<TableHead> getTableHeads(List<TableHead> generalCol) {
        List<TableHead> tableHeadList = new ArrayList<TableHead>();
        tableHeadList.addAll(generalCol);
        tableHeadList.add(new TableHead("projectName", "projectName", 1, false));
        tableHeadList.add(new TableHead("PMName", "PMName", 1, false));
        tableHeadList.add(new TableHead("projectID", "projectID", 1, true));
        tableHeadList.add(new TableHead("PMID", "PMID", 1, true));
        tableHeadList.add(new TableHead("nodeID", "nodeID", 1, true));
        tableHeadList.add(new TableHead("nodePath", "nodePath", 1, true));
        return tableHeadList;
    }

    @Override
    public void addProjectNode(Map<String, Object> queryMap) {
        mapper.addProjectNode(queryMap);
    }
}
