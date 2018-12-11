package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.mapper.LevelMapper;
import com.isoftstone.pmit.project.hrbp.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LevelService implements ILevelService {

    @Autowired
    private LevelMapper mapper;

    @Override
    public List<TupleData> queryLevelInfo(int levelID) {
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        return getLevelNodeInfo(levelNodes);
    }

    @Override
    public List<String> queryTeamLevel(Map<String, Object> params) {
        Integer levelID = (Integer) (params.get("levelID"));
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        List<TupleData> levelNodeInfo = getLevelNodeInfo(levelNodes);
        String col = getQueryCol(params, levelNodeInfo);
        params.put("col", col);
        return mapper.queryTeamLevel(params);
    }

    private List<TupleData> getLevelNodeInfo(String levelNode) {
        List<TupleData> levelNodeInfo = new ArrayList<TupleData>();
        if (!StringUtilsMethod.isEmpty(levelNode)) {
            String[] nodes = levelNode.split("-");
            if (nodes != null) {
                for (String node : nodes) {
                    String[] nodeInfo = node.split(",");
                    if (nodeInfo != null && nodeInfo.length == 2) {
                        TupleData tupleData = new TupleData();
                        tupleData.setName(nodeInfo[0]);
                        tupleData.setValue(nodeInfo[1]);
                        levelNodeInfo.add(tupleData);
                    }
                }
            }
        }

        if (levelNodeInfo.isEmpty()) {
            levelNodeInfo = null;
        }
        return levelNodeInfo;
    }

    private String getQueryCol(Map<String, Object> params, List<TupleData> levelInfos) {
        String col = null;
        if (levelInfos != null && !levelInfos.isEmpty()) {
            col = levelInfos.get(levelInfos.size() - 1).getName();
            for (TupleData levelInfo : levelInfos) {
                String name = levelInfo.getName();
                if (params.get(name) == null) {
                    col = name;
                    break;
                }
            }
        }
        return col;
    }

    private List<String> getQueryCols(List<TupleData> levelInfos) {
        List<String> col = new ArrayList<String>();
        if (levelInfos != null && !levelInfos.isEmpty()) {
            for (TupleData levelInfo : levelInfos) {
                col.add(levelInfo.getName());
            }
        }
        return col;
    }

    @Override
    public List<LevelTreeNode> queryLevelTree(Map<String, Object> params) {
        Integer levelID = (Integer) (params.get("levelID"));
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        List<TupleData> levelNodeInfo = getLevelNodeInfo(levelNodes);
        List<String> levels = getQueryCols(levelNodeInfo);
        params.put("levels", levels);

        if (levelInfo.get("tableName").equals("hrbp_level_PO")) {
            List<Map<String, Object>> levelTreeNodes = mapper.queryLevelTeamNodes(params);
            return getLevelTeamTrees(levelTreeNodes, levels);
        } else {
            List<Map<String, Object>> levelTreeNodes = mapper.queryLevelTeamNodes(params);
            return getLevelTeamTrees(levelTreeNodes, levels);
        }
    }

    private List<LevelTreeNode> getLevelTeamTrees(List<Map<String, Object>> levelTreeNodes, List<String> levels) {
        LevelTreeNode root = new LevelTreeNode();
        root.setChildList(new ArrayList<LevelTreeNode>());
        root.setLevel(0);

        Map<String, LevelTreeNode> tempMap = new HashMap<String, LevelTreeNode>();
        if (levelTreeNodes != null && levels != null) {
            for (Map<String, Object> levelTreeNode : levelTreeNodes) {
                LevelTreeNode parentNode = root;
                for (String temp : levels) {
                    parentNode = buildLevelTreeNode(tempMap, levelTreeNode, parentNode, temp);
                    if (parentNode == null) {
                        break;
                    }
                }
                buildLeafNode(levelTreeNode, parentNode);
            }
        }

        return root.getChildList();
    }

    private void buildLeafNode(Map<String, Object> levelTreeNode, LevelTreeNode parentNode) {
        if (!Boolean.valueOf(String.valueOf(levelTreeNode.get("isTemp")))) {
            String teamID = String.valueOf(levelTreeNode.get("teamID"));
            String teamName = String.valueOf(levelTreeNode.get("teamName"));
            LevelTreeNode left = new LevelTreeNode();
            left.setNodeName(teamName);
            left.setNodeID(teamID);
            left.setIsLeafNode(true);
            left.setLevel(parentNode.getLevel() + 1);
            left.setLevelID("team");

            parentNode.getChildList().add(left);
        }
    }

    private LevelTreeNode buildLevelTreeNode(Map<String, LevelTreeNode> tempMap, Map<String, Object> levelTreeNode, LevelTreeNode parentNode, String temp) {
        if (temp != null) {
            String value = String.valueOf(levelTreeNode.get(temp));
            String key = temp + "-" + value;
            LevelTreeNode tempNode = tempMap.get(key);
            if (tempNode == null) {
                tempNode = new LevelTreeNode();
                tempNode.setChildList(new ArrayList<LevelTreeNode>());

                tempNode.setLevel(parentNode.getLevel() + 1);
                tempNode.setLevelID(temp);
                tempNode.setNodeName(value);
                parentNode.getChildList().add(tempNode);
                tempMap.put(key, tempNode);
            }
            parentNode = tempNode;
            return parentNode;
        }
        return null;
    }

}
