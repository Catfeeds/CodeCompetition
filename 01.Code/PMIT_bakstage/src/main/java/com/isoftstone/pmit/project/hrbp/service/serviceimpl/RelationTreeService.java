package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.exception.RelationTreeNodeAddException;
import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.mapper.RelationTreeMapper;
import com.isoftstone.pmit.project.hrbp.service.IRelationTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelationTreeService implements IRelationTreeService {

    @Autowired
    private RelationTreeMapper mapper;

    private Map<String, Integer> map = new HashMap<String, Integer>();
    private List<String> list = new ArrayList<String>();

    {
        list.add("BG");
        list.add("BD");

        map.put("BG", 1);
        map.put("BD", 2);
        map.put("BU", 3);
        map.put("CU", 4);
        map.put("SPM", 5);
        map.put("Team", 6);
    }

    @Override
    public List<RelationTreeNode> queryTeamLevelTree() {
        List<RelationTreeNode> levelNodes = mapper.queryLevelTree();
        List<RelationTreeNode> trees = buildTree(levelNodes);
        return trees;
    }

    @Override
    @Transactional
    public void addTeamLevelNode(Map<String, Object> queryMap) {
        checkAddNodeInfo(queryMap);
        Integer parentNodeID = (Integer) queryMap.get("parentNodeID");
        String parentNodePath = (String) queryMap.get("parentNodePath");
        String nodePath = TreeUtil.getParentPath(parentNodePath, parentNodeID);
        queryMap.put("addNodePath", nodePath);
        mapper.addTeamLevelNode(queryMap);
    }

    @Override
    public void deleteNode(String nodePath, Integer nodeID) {

    }

    @Override
    public void deleteNodeAndChildren(Map<String,Object> params) {
        Map<String, Object> deleteParamMap = new HashMap<String, Object>();
//        deleteParamMap.put("nodeID", nodeID);
//        deleteParamMap.put("nodePath", TreeUtil.getParentPath(nodePath, nodeID));
        mapper.deleteLevelRlaNode(deleteParamMap);
    }

    @Override
    public void updateLevelRlaNode(Map<String, Object> queryMap, String tableName) {
        queryMap.put("tableName", tableName);
        mapper.updateLevelRlaNode(queryMap);
    }

    @Override
    @Transactional
    public void moveLevelRlaNode(Map<String, Object> paramMap, String tableName) {
        Integer targetNodeID = (Integer) paramMap.get("targetNodeID");
        String targetNodePath = (String) paramMap.get("targetNodePath");
        Integer moveNodeID = (Integer) paramMap.get("moveNodeID");
        String moveNodePath = (String) paramMap.get("moveNodePath");

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("tableName", tableName);

        queryMap.put("moveNodeID", moveNodeID);
        String replaceSourcePath = TreeUtil.getParentPath(moveNodePath, moveNodeID);
        queryMap.put("replaceSourcePath", replaceSourcePath);

        String replaceParentPath = TreeUtil.getParentPath(targetNodePath, targetNodeID);
        queryMap.put("replaceParentPath", replaceParentPath);
        String replaceTargetPath = TreeUtil.getParentPath(replaceParentPath, moveNodeID);
        queryMap.put("replaceTargetPath", replaceTargetPath);

        mapper.moveLevelRlaNode(queryMap);
    }

    @Override
    public List<RelationTreeNode> queryLevelRlaLeafNode(List<Map<String, Object>> nodeList, String tableName) {
        Map<String, Object> queryMap = buildQueryParamMap(nodeList);
        queryMap.put("tableName", tableName);
        return mapper.queryRelationNode(queryMap);
    }


    private List<RelationTreeNode> buildTree(List<RelationTreeNode> treeNodeList) {
        List<RelationTreeNode> result = new ArrayList<RelationTreeNode>();
        Map<Integer, RelationTreeNode> tempMap = new HashMap<Integer, RelationTreeNode>();
        buildTreeNode(treeNodeList, result, tempMap);
        return result;
    }

    private void buildTreeNode(List<RelationTreeNode> treeNodeList, List<RelationTreeNode> result,
                               Map<Integer, RelationTreeNode> tempMap) {
        //获取一个根节点
        RelationTreeNode rootNode = new RelationTreeNode();
        if (!treeNodeList.isEmpty()) {
            rootNode = treeNodeList.get(0);
            tempMap.put(rootNode.getNodeID(), rootNode);
            treeNodeList.remove(0);

            //构建这个根节点的树
            RelationTreeNode tempNode = new RelationTreeNode();
            while (!treeNodeList.isEmpty() && tempNode != null) {
                tempNode = treeNodeList.get(0);
                String nodePath = tempNode.getNodePath();
                String[] pathNodeIDs = nodePath.split(":");
                if (pathNodeIDs.length > 1) {
                    tempNode = addTreeNode(treeNodeList, tempMap, tempNode, pathNodeIDs[pathNodeIDs.length - 1]);
                } else {
                    tempNode = null;
                }
            }

            result.add(rootNode);
        }
    }

    private RelationTreeNode addTreeNode(List<RelationTreeNode> treeNodeList, Map<Integer, RelationTreeNode> tempMap,
                                         RelationTreeNode tempNood, String pathNodeID) {
        Integer parentNodeID = Integer.parseInt(pathNodeID);
        RelationTreeNode parentNode = tempMap.get(parentNodeID);
        if (parentNode != null) {
            if (tempNood.getIsTeamNode()) {
                List<RelationTreeNode> teamList = parentNode.getTeamList();
                if (teamList == null) {
                    teamList = new ArrayList<RelationTreeNode>();
                    parentNode.setTeamList(teamList);
                }
                teamList.add(tempNood);
            } else {
                List<RelationTreeNode> childList = parentNode.getChildList();
                if (childList == null) {
                    childList = new ArrayList<RelationTreeNode>();
                    parentNode.setChildList(childList);
                }
                childList.add(tempNood);
            }
            tempMap.put(tempNood.getNodeID(), tempNood);
            treeNodeList.remove(0);
        }

        tempNood = parentNode;
        return tempNood;
    }

    private Map<String, Object> buildQueryParamMap(List<Map<String, Object>> nodeList) {
        StringBuilder nodes = new StringBuilder();
        Map<String, Object> result = new HashMap<String, Object>();

        if (nodeList != null) {
            nodes.append("(");
            List<String> paths = new ArrayList<String>();

            for (Map<String, Object> temp : nodeList) {
                Integer nodeID = (Integer) temp.get("nodeID");
                nodes.append(nodeID).append(",");

                String nodePath = (String) temp.get("nodePath");
                paths.add(TreeUtil.getParentPath(nodePath, nodeID));
            }

            if (nodes.toString().endsWith(",")) {
                nodes.deleteCharAt(nodes.length() - 1);
            }
            nodes.append(")");

            result.put("paths", paths);
            result.put("nodes", nodes.toString());
        }
        return result;
    }

    private void checkAddNodeInfo(Map<String, Object> queryMap) {
        // TODO: 2018/12/12 查询层级关系，判断插入级别是否合理


        String parentNodeType = (String) queryMap.get("parentNodeType");
        if (parentNodeType.equalsIgnoreCase("Team")) {
            throw new RelationTreeNodeAddException(parentNodeType + "不支持插入子节点");
        }

        String addNodeType = (String) queryMap.get("addNodeType");

        if (addNodeType.equalsIgnoreCase("Team") && list.contains(parentNodeType)) {
            throw new RelationTreeNodeAddException(String.valueOf(list) + "不支持插入项目组子节点");
        }

        Integer parentTypeIndex = map.get(parentNodeType);
        Integer addNodeTypeIndex = map.get(addNodeType);

        if (parentTypeIndex >= addNodeTypeIndex) {
            throw new RelationTreeNodeAddException(addNodeType + "级别" +
                    (parentTypeIndex == addNodeTypeIndex ? "等于" : "大于")
                    + parentNodeType + ",不允许插入");
        }
    }
}
