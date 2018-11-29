package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.mapper.LevelRlaMapper;
import com.isoftstone.pmit.project.hrbp.service.ILevelRlaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class LevelRlaService implements ILevelRlaService {
    @Autowired
    private LevelRlaMapper mapper;

    @Override
    public List<LevelTreeNode> queryLevelRlaNode(List<Map<String, Object>> rootNodeList)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> queryMap = buildQueryParamMap(rootNodeList);
        List<LevelTreeNode> levelNodes = mapper.queryLevelRlaNode(queryMap);
        List<LevelTreeNode> trees = TreeUtil.buildTree(levelNodes, rootNodeList,LevelTreeNode.class);
        return trees;
    }

    @Override
    @Transactional
    public void addLevelRlaNode(Map<String, Object> queryMap) {
        Integer parentNodeID = (Integer) queryMap.get("parentNodeID");
        String parentNodePath = (String) queryMap.get("parentNodePath");
        String nodePath = TreeUtil.getParentPath(parentNodePath, parentNodeID);
        queryMap.put("nodePath", nodePath);
        mapper.addLevelRlaNode(queryMap);

        queryMap.put("isLeafNode", false);
        mapper.updateIsLeafNode(queryMap);
    }

    @Override
    public void deleteLevelRlaNode(String nodePath, Integer nodeID) {
        Map<String, Object> deleteParamMap = new HashMap<String, Object>();
        deleteParamMap.put("nodeID", nodeID);
        deleteParamMap.put("nodePath", TreeUtil.getParentPath(nodePath, nodeID));
        mapper.deleteLevelRlaNode(deleteParamMap);
    }

    @Override
    @Transactional
    public void moveLevelRlaNode(Map<String, Object> queryMap) {
        Integer targetNodeID = (Integer) queryMap.get("targetNodeID");
        String targetNodePath = (String) queryMap.get("targetNodePath");
        Integer nodeID = (Integer) queryMap.get("nodeID");
        String nodePath = (String) queryMap.get("nodePath");

        String replacePath = TreeUtil.getParentPath(targetNodePath,targetNodeID);


        // TODO: 2018/11/29 移动节点信息
    }

    @Override
    public void updateLevelRlaNode(Map<String, Object> queryMap) {
        // TODO: 2018/11/29 更新节点信息
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
            result.put("nodes", nodes);
        }
        return result;
    }


}