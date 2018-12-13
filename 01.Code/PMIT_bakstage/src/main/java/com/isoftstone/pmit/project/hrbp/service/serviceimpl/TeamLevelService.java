package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.mapper.TeamLevelMapper;
import com.isoftstone.pmit.project.hrbp.service.ITeamLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeamLevelService implements ITeamLevelService {

    DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmSSsss");

    @Autowired
    private TeamLevelMapper mapper;

    @Override
    public List<LevelTreeNode> queryLevelTree(Map<String, Object> params) {
        Integer levelID = (Integer) (params.get("levelID"));
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        List<TupleData> levelNodeInfo = getLevelNodeInfo(levelNodes);
        List<String> levels = getQueryCols(levelNodeInfo);
        params.put("levels", levels);
        List<Map<String, Object>> levelTreeNodes = mapper.queryLevelNodes(params);
        return getLevelTeamTrees(levelTreeNodes, levels);
    }

    private List<LevelTreeNode> getLevelTeamTrees(List<Map<String, Object>> levelTreeNodes, List<String> levels) {
        LevelTreeNode root = new LevelTreeNode();
        root.setChildList(new ArrayList<LevelTreeNode>());
        root.setLevel(0);

        Map<String, LevelTreeNode> tempMap = new HashMap<String, LevelTreeNode>();
        if (levelTreeNodes != null && levels != null) {
            for (Map<String, Object> levelTreeNode : levelTreeNodes) {
                LevelTreeNode parentNode = root;
                StringBuilder key = new StringBuilder();

                for (String temp : levels) {
                    parentNode = buildLevelTreeNode(tempMap, levelTreeNode, parentNode, temp, key);
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

    private LevelTreeNode buildLevelTreeNode(Map<String, LevelTreeNode> tempMap, Map<String, Object> levelTreeNode,
                                             LevelTreeNode parentNode, String temp, StringBuilder key) {
        if (temp != null) {
            String value = String.valueOf(levelTreeNode.get(temp));
            key.append("-").append(value);
            LevelTreeNode tempNode = tempMap.get(key.toString());
            if (tempNode == null) {
                tempNode = new LevelTreeNode();
                tempNode.setChildList(new ArrayList<LevelTreeNode>());

                tempNode.setLevel(parentNode.getLevel() + 1);
                tempNode.setLevelID(temp);
                tempNode.setNodeName(value);
                parentNode.getChildList().add(tempNode);
                tempMap.put(key.toString(), tempNode);
            }
            parentNode = tempNode;
            return parentNode;
        }
        return null;
    }

    @Override
    @Transactional
    public Long addLevelTreeNode(Map<String, Object> params) {
        params.put("key", -Long.valueOf(dateFormat.format(new Date())));
        Long authorityID = mapper.addLevelTreeNode(params);
        String addLevel = String.valueOf(params.get("addLevel"));
        params.remove(addLevel);
        mapper.deleteTempNode(params);
        return authorityID;
    }

    @Override
    public void deleteLevelTreeNode(Map<String, Object> params) {
        Integer levelID = (Integer) (params.get("levelID"));
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);

        if (levelInfo.get("tableName").equals("hrbp_level_PO")) {

        } else {
            mapper.deleteLevelTreeNode(params);
        }
    }

    //↓↓↓↓↓↓↓↓↓↓内部使用接口↓↓↓↓↓↓↓↓↓↓
    @Override
    public List<TupleData> queryLevelInfo(int levelID) {
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        return getLevelNodeInfo(levelNodes);
    }

    @Override
    public List<String> queryLevel(Map<String, Object> params) {
        Integer levelID = (Integer) (params.get("levelID"));
        Map<String, Object> levelInfo = mapper.queryLevels(levelID);
        String levelNodes = String.valueOf(levelInfo.get("levelNode"));
        List<TupleData> levelNodeInfo = getLevelNodeInfo(levelNodes);
        String col = getQueryCol(params, levelNodeInfo);
        params.put("col", col);
        return mapper.queryLevel(params);
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
    public List<Long> queryProjects(Map<String, Object> params) {
        return mapper.queryProjects(params);
    }

    @Override
    public void addTeamNode(Map<String, Object> params){
        mapper.addTeamNode(params);
    }
}
