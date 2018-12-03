package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.mapper.LevelRlaMapper;
import com.isoftstone.pmit.project.hrbp.service.ILevelRlaService;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LevelRlaService implements ILevelRlaService {
    @Autowired
    private LevelRlaMapper mapper;

    @Override
    public List<LevelTreeNode> queryLevelRlaNode(List<Map<String, Object>> rootNodeList) {
        Map<String, Object> queryMap = buildQueryParamMap(rootNodeList);
        List<LevelTreeNode> levelNodes = mapper.queryLevelRlaNode(queryMap);
        List<LevelTreeNode> trees = buildTree(levelNodes, rootNodeList);
        return trees;
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

    private List<LevelTreeNode> buildTree(List<LevelTreeNode> treeNodeList,
                                          List<Map<String, Object>> rootNodeList) {
        List<LevelTreeNode> result = new ArrayList<LevelTreeNode>();
        if (rootNodeList != null) {
            Map<Integer, LevelTreeNode> tempMap = new HashMap<Integer, LevelTreeNode>();
            for (Map<String, Object> temp : rootNodeList) {
                buildTreeNode(treeNodeList, result, tempMap, temp);
            }
        }
        return result;
    }

    private void buildTreeNode(List<LevelTreeNode> treeNodeList, List<LevelTreeNode> result,
                               Map<Integer, LevelTreeNode> tempMap, Map<String, Object> temp) {
        //获取一个根节点
        LevelTreeNode rootNode = new LevelTreeNode();
        if (!treeNodeList.isEmpty()) {
            rootNode = treeNodeList.get(0);
            tempMap.put(rootNode.getNodeID(), rootNode);
            treeNodeList.remove(0);

            //构建这个根节点的树
            LevelTreeNode tempNode = new LevelTreeNode();
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

    private LevelTreeNode addTreeNode(List<LevelTreeNode> treeNodeList, Map<Integer, LevelTreeNode> tempMap,
                                      LevelTreeNode tempNood, String pathNodeID) {
        Integer parentNodeID = Integer.parseInt(pathNodeID);
        LevelTreeNode parentNode = tempMap.get(parentNodeID);
        if (parentNode != null) {
            List<LevelTreeNode> childList = parentNode.getChildList();
            if (childList == null) {
                childList = new ArrayList<LevelTreeNode>();
                parentNode.setChildList(childList);
            }

            childList.add(tempNood);
            tempMap.put(tempNood.getNodeID(), tempNood);
            treeNodeList.remove(0);
        }

        tempNood = parentNode;
        return tempNood;
    }

    @Override
    @Transactional
    public void addLevelRlaNode(Map<String, Object> queryMap) {
        Integer parentNodeID = (Integer) queryMap.get("parentNodeID");
        String parentNodePath = (String) queryMap.get("parentNodePath");
        String nodePath = TreeUtil.getParentPath(parentNodePath, parentNodeID);
        queryMap.put("nodePath", nodePath);
        mapper.addLevelRlaNode(queryMap);

        queryMap = new HashMap<String, Object>();
        queryMap.put("nodeID", parentNodeID);
        queryMap.put("isLeafNode", false);
        queryMap.put("isRelationNode", false);
        mapper.updateLevelRlaNode(queryMap);
    }

    @Override
    public void deleteLevelRlaNode(String nodePath, Integer nodeID) {
        Map<String, Object> deleteParamMap = new HashMap<String, Object>();
        deleteParamMap.put("nodeID", nodeID);
        deleteParamMap.put("nodePath", TreeUtil.getParentPath(nodePath, nodeID));
        mapper.deleteLevelRlaNode(deleteParamMap);
    }

    @Override
    public void updateLevelRlaNode(Map<String, Object> queryMap) {
        mapper.updateLevelRlaNode(queryMap);
    }

    @Override
    @Transactional
    public void moveLevelRlaNode(Map<String, Object> paramMap) {
        Integer targetNodeID = (Integer) paramMap.get("targetNodeID");
        String targetNodePath = (String) paramMap.get("targetNodePath");
        Integer moveNodeID = (Integer) paramMap.get("moveNodeID");
        String moveNodePath = (String) paramMap.get("moveNodePath");

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("moveNodeID", moveNodeID);
        String replaceSourcePath = TreeUtil.getParentPath(moveNodePath, moveNodeID);
        queryMap.put("replaceSourcePath", replaceSourcePath);

        String replaceParentPath = TreeUtil.getParentPath(targetNodePath, targetNodeID);
        queryMap.put("replaceParentPath", replaceParentPath);
        String replaceTargetPath = TreeUtil.getParentPath(replaceParentPath, moveNodeID);
        queryMap.put("replaceTargetPath", replaceTargetPath);

        String operaString = "REPLACE(nodePath,'" + replaceSourcePath + "','" + replaceTargetPath + "')";
        queryMap.put("operaString",operaString);
        mapper.moveLevelRlaNode(queryMap);
    }
}