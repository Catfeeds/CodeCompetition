package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.constant.CommonConst;
import com.isoftstone.pmit.common.exception.RelationTreeNodeException;
import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.common.TreeUtil;
import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.mapper.RelationMapper;
import com.isoftstone.pmit.project.hrbp.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelationService implements IRelationService {

    @Autowired
    private RelationMapper mapper;

    private Map<String, String> tableNames = new HashMap<String, String>();

    {
        tableNames.put("team", "mms_relation_team");
        tableNames.put("po", "mms_relation_po");
    }

    @Override
    public List<Map<String, Object>> queryAllLevel(Map<String, Object> params) {
        return mapper.queryAllLevel(params);
    }

    @Override
    public List<RelationTreeNode> queryTeamInfo() {
        return mapper.queryTeamInfo();
    }

    @Override
    public List<RelationTreeNode> queryPOInfo() {
        return mapper.queryPOInfo();
    }


    @Override
    public List<RelationTreeNode> queryAllTree(Map<String, Object> params) {
        params.put("tableName", tableNames.get(params.get("type")));
        List<RelationTreeNode> levelNodes = mapper.queryTree(params);
        List<RelationTreeNode> trees = buildTree(levelNodes);
        return trees;
    }

    @Override
    public void addTeamNode(Map<String, Object> params) {
        checkAddNodeInfo(params);
        params.put("tableName", tableNames.get(params.get("type")));
        String addNodeType = (String) params.get("addNodeType");
        if (!addNodeType.equalsIgnoreCase("Team")) {
            if (params.containsKey("addTeamID")) {
                params.remove("addTeamID");
            }
        }

        Integer parentNodeID = (Integer) params.get("parentNodeID");
        String parentNodePath = (String) params.get("parentNodePath");
        String nodePath = TreeUtil.getParentPath(parentNodePath, parentNodeID);
        params.put("addNodePath", nodePath);
        mapper.addTeamNode(params);
    }

    @Override
    public void deleteNode(Map<String, Object> params) {
        checkDeleteNodeInfo(params);
        params.put("tableName", tableNames.get(params.get("type")));
        Integer nodeID = Integer.valueOf(String.valueOf(params.get("nodeID")));
        params.put("nodePath", ":" + nodeID + ":");
        mapper.deleteNode(params);
    }

    @Override
    public void deleteNodeAndChildren(Map<String, Object> params) {
        params.put("tableName", tableNames.get(params.get("type")));
        String nodePath = String.valueOf(params.get("nodePath"));
        Integer nodeID = Integer.valueOf(String.valueOf(params.get("nodeID")));
        params.put("nodePath", TreeUtil.getParentPath(nodePath, nodeID));
        mapper.deleteNodeAndChildren(params);
    }

    @Override
    public void updateTreeNode(Map<String, Object> params) {
        checkUpdateNodeInfo(params);
        params.put("tableName", tableNames.get(params.get("type")));
        mapper.updateTreeNode(params);
    }

    @Override
    public void moveTreeNode(Map<String, Object> params) {
        checkMoveNodeInfo(params);

        Integer targetNodeID = (Integer) params.get("targetNodeID");
        String targetNodePath = (String) params.get("targetNodePath");
        Integer moveNodeID = (Integer) params.get("moveNodeID");
        String moveNodePath = (String) params.get("moveNodePath");

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("tableName", tableNames.get(params.get("type")));
        queryMap.put("moveNodeID", moveNodeID);
        String replaceSourcePath = TreeUtil.getParentPath(moveNodePath, moveNodeID);
        queryMap.put("replaceSourcePath", replaceSourcePath);

        String replaceParentPath = TreeUtil.getParentPath(targetNodePath, targetNodeID);
        queryMap.put("replaceParentPath", replaceParentPath);
        String replaceTargetPath = TreeUtil.getParentPath(replaceParentPath, moveNodeID);
        queryMap.put("replaceTargetPath", replaceTargetPath);

        mapper.moveTreeNode(queryMap);
    }

    @Override
    public List<RelationTreeNode> queryParentTreesByNode(Map<String, Object> params) {
        params.put("tableName", tableNames.get(params.get("type")));
        params.put("isChild", false);

        List<RelationTreeNode> nodes = new ArrayList<RelationTreeNode>();
        nodes = mapper.queryNodes(params);

        Set<Integer> nodeIDs = new HashSet<Integer>();
        buildNodeIDs(nodes, nodeIDs);

        params.put("nodeIDs", nodeIDs);
        List<RelationTreeNode> treeNodes = mapper.queryNodes(params);

        return buildTree(treeNodes);
    }

    @Override
    public List<RelationTreeNode> queryChildTreesByNode(Map<String, Object> params) {
        params.put("tableName", tableNames.get(params.get("type")));
        params.put("isChild", true);

        List<RelationTreeNode> nodes = new ArrayList<RelationTreeNode>();
        nodes = mapper.queryNodes(params);

        Set<String> nodePaths = new HashSet<String>();
        buildNodePaths(nodes, nodePaths);

        params.put("nodePaths", nodePaths);
        List<RelationTreeNode> treeNodes = mapper.queryNodes(params);

        return buildTree(treeNodes);
    }

    @Override
    public Map<String, Object> queryCascadeInfo(Map<String, Object> params) {
        params.put("tableName", tableNames.get(params.get("type")));

        Map<String, Object> result = new HashMap<String, Object>();
        PostOptionInfo onePost = mapper.queryPostOption(params);

        List<RelationTreeNode> treeNodes = getAuthTree(params, onePost);
        if (treeNodes == null) {
            return null;
        }
        List<RelationTreeNode> trees = buildTree(treeNodes);

        List<Map<String, Object>> levelInfo = mapper.queryAllLevel(params);
        result.put("levelInfo", levelInfo);

        List<String> levels = new ArrayList<String>();
        for (Map<String, Object> temp : levelInfo) {
            levels.add(String.valueOf(temp.get("levelIndexID")));
        }

        RelationTreeNode root = new RelationTreeNode();
        root.setChildList(new ArrayList<RelationTreeNode>());
        root.setTeamList(new ArrayList<RelationTreeNode>());
        root.setNodeID(-1);
        root.setNodeName("root");

        for (RelationTreeNode temp : trees) {
           // getCascadeTree(root, temp, levels);
        }


        return result;
    }

    private void getCascadeTree(RelationTreeNode parentNode, RelationTreeNode currentNode, List<String> levels,
                                List<RelationTreeNode> tempList) {
        if (currentNode.getChildList() != null) {
            List<RelationTreeNode> tempCurrentList = new ArrayList<RelationTreeNode>();
            for (RelationTreeNode temp : currentNode.getChildList()) {
                getCascadeTree(currentNode, temp, levels,tempCurrentList);
            }
            parentNode.setChildList(tempCurrentList);
        }

        if (!levels.contains(currentNode.getNodeType())) {
            //parentNode.getChildList().remove(currentNode);
            if (currentNode.getChildList() != null) {
                tempList.addAll(currentNode.getChildList());
                //parentNode.getChildList().addAll(currentNode.getChildList());
            }

            if (currentNode.getTeamList() != null) {
                if (parentNode.getTeamList() == null) {
                    parentNode.setTeamList(new ArrayList<RelationTreeNode>());
                }
                parentNode.getTeamList().addAll(currentNode.getTeamList());
            }
        }else {
            tempList.add(currentNode);
        }
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
        while (!treeNodeList.isEmpty()) {
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

    private void checkAddNodeInfo(Map<String, Object> queryMap) {
        Map<String, Map<String, Object>> levelInfo = new HashMap<String, Map<String, Object>>();
        List<String> fixedLevel = new ArrayList<String>();
        buildLevelInfo(levelInfo, fixedLevel);

        String parentNodeType = (String) queryMap.get("parentNodeType");
        if (parentNodeType.equalsIgnoreCase("Team")) {
            throw new RelationTreeNodeException(parentNodeType + "不支持插入子节点");
        }
        if (parentNodeType.equalsIgnoreCase("BG")) {
            throw new RelationTreeNodeException(parentNodeType + "不支持插入子节点,请从BD节点开始建立树结构");
        }

        String addNodeType = (String) queryMap.get("addNodeType");

        if (addNodeType.equalsIgnoreCase("Team") && fixedLevel.contains(parentNodeType)) {
            throw new RelationTreeNodeException(String.valueOf(fixedLevel) + "不支持插入项目组子节点");
        }

        Integer parentTypeIndex = (Integer) (levelInfo.get(parentNodeType).get("levelIndex"));
        Integer addNodeTypeIndex = (Integer) (levelInfo.get(addNodeType).get("levelIndex"));

        if (parentTypeIndex >= addNodeTypeIndex) {
            throw new RelationTreeNodeException(addNodeType + "级别" +
                    (parentTypeIndex == addNodeTypeIndex ? "等于" : "大于")
                    + parentNodeType + ",不允许插入");
        }
    }

    private void checkMoveNodeInfo(Map<String, Object> queryMap) {
        Map<String, Map<String, Object>> levelInfo = new HashMap<String, Map<String, Object>>();
        List<String> fixedLevel = new ArrayList<String>();
        buildLevelInfo(levelInfo, fixedLevel);

        String targetNodeType = (String) queryMap.get("targetNodeType");
        if (targetNodeType.equalsIgnoreCase("BG")) {
            throw new RelationTreeNodeException(targetNodeType + "不支持插入子节点");
        }

        String moveNodeType = (String) queryMap.get("moveNodeType");
        if (fixedLevel.contains(moveNodeType)) {
            throw new RelationTreeNodeException(String.valueOf(fixedLevel) + "不支持移动");
        }

        Integer targetNodeTypeIndex = (Integer) (levelInfo.get(targetNodeType).get("levelIndex"));
        Integer moveNodeTypeIndex = (Integer) (levelInfo.get(moveNodeType).get("levelIndex"));

        if (targetNodeTypeIndex >= moveNodeTypeIndex) {
            throw new RelationTreeNodeException(moveNodeType + "级别" +
                    (targetNodeTypeIndex == moveNodeTypeIndex ? "等于" : "大于")
                    + targetNodeType + ",不允许插入");
        }
    }

    private void checkDeleteNodeInfo(Map<String, Object> queryMap) {
        List<String> fixedLevel = new ArrayList<String>();
        buildLevelInfo(null, fixedLevel);

        String nodeType = (String) queryMap.get("nodeType");
        if (fixedLevel.contains(nodeType)) {
            throw new RelationTreeNodeException(String.valueOf(fixedLevel) + "不支持删除");
        }
    }

    private void checkUpdateNodeInfo(Map<String, Object> queryMap) {
        List<String> fixedLevel = new ArrayList<String>();
        buildLevelInfo(null, fixedLevel);

        String nodeType = (String) queryMap.get("nodeType");
        if (fixedLevel.contains(nodeType)) {
            throw new RelationTreeNodeException(String.valueOf(fixedLevel) + "不支持修改");
        }
    }

    private void buildLevelInfo(Map<String, Map<String, Object>> levelInfo, List<String> fixedLevel) {
        Map<String, Object> levelQueryMap = new HashMap<String, Object>();
        levelQueryMap.put("relationID", 1);
        List<Map<String, Object>> levelInfoList = queryAllLevel(levelQueryMap);
        if (null != levelInfoList) {
            for (Map<String, Object> temp : levelInfoList) {
                if (levelInfo != null) {
                    levelInfo.put(String.valueOf(temp.get("levelIndexID")), temp);
                }
                if (fixedLevel != null && !Boolean.valueOf(String.valueOf(temp.get("operationAllow")))) {
                    fixedLevel.add(String.valueOf(temp.get("levelIndexID")));
                }
            }
        }
    }

    private void buildNodeIDs(List<RelationTreeNode> nodes, Set<Integer> nodeIDs) {
        if (nodes != null) {
            for (RelationTreeNode temp : nodes) {
                nodeIDs.add(temp.getNodeID());
                String path = temp.getNodePath();
                if (!StringUtilsMethod.isEmpty(path)) {
                    String[] pathNodes = path.split(":");
                    if (pathNodes.length > 1) {
                        for (int index = 1; index < pathNodes.length; index++) {
                            nodeIDs.add(Integer.valueOf(pathNodes[index]));
                        }

                    }
                }
            }
        }
    }

    private void buildNodeIDs(String[] paths, Set<Integer> nodeIDs) {
        if (paths != null) {
            for (String path : paths) {
                if (!StringUtilsMethod.isEmpty(path)) {
                    String[] pathNodes = path.split(":");
                    if (pathNodes.length > 2) {
                        for (int index = 1; index < pathNodes.length - 1; index++) {
                            nodeIDs.add(Integer.valueOf(pathNodes[index]));
                        }

                    }
                }
            }
        }
    }

    private void buildNodePaths(List<RelationTreeNode> nodes, Set<String> nodePaths) {
        if (nodes != null) {
            for (RelationTreeNode temp : nodes) {
                Integer nodeID = temp.getNodeID();
                String nodePath = temp.getNodePath();
                String path = TreeUtil.getParentPath(nodePath, nodeID);
                nodePaths.add(path);
            }
        }
    }

    private List<RelationTreeNode> getAuthTree(Map<String, Object> params, PostOptionInfo onePost) {
        String authCont = onePost.getAuthContent();
        if (StringUtilsMethod.isEmpty(authCont)) {
            return null;
        }
        String[] postInfo = authCont.split(CommonConst.DATA_SEPARATOR);
        List<RelationTreeNode> treeNodes = new ArrayList<RelationTreeNode>();
        treeNodes.addAll(queryParentTreesByPath(postInfo, params));
        treeNodes.addAll(queryChildTreesByPath(postInfo, params));
        return treeNodes;
    }

    private List<RelationTreeNode> queryParentTreesByPath(String[] paths, Map<String, Object> params) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("tableName", params.get("tableName"));
        queryMap.put("isChild", false);

        Set<Integer> nodeIDs = new HashSet<Integer>();
        buildNodeIDs(paths, nodeIDs);
        queryMap.put("nodeIDs", nodeIDs);
        return mapper.queryNodes(queryMap);
    }

    public List<RelationTreeNode> queryChildTreesByPath(String[] paths, Map<String, Object> params) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("tableName", params.get("tableName"));
        queryMap.put("isChild", true);

        List<String> nodePaths = new ArrayList<String>();
        nodePaths.addAll(Arrays.asList(paths));
        queryMap.put("nodePaths", nodePaths);

        buildCurrentNodeIDs(queryMap, nodePaths);

        return mapper.queryNodes(queryMap);
    }

    private void buildCurrentNodeIDs(Map<String, Object> queryMap, List<String> nodePaths) {
        Set<Integer> nodeIDs = new HashSet<Integer>();
        for (String temp : nodePaths) {
            String[] pathNodeID = temp.split(":");
            nodeIDs.add(Integer.valueOf(pathNodeID[pathNodeID.length - 1]));
        }
        queryMap.put("nodeIDs", nodeIDs);
    }
}
