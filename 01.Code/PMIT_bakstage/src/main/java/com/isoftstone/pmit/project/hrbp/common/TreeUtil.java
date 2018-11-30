package com.isoftstone.pmit.project.hrbp.common;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class TreeUtil {
//    public static <T extends BaseTreeNode> void buildTreesNoLeafByPath(List<T> leafNodes, Class tClass,
//                                                                       Set<Integer> nodeIDs, T rootNode)
//            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Map<Integer, T> tempMap = new HashMap<Integer, T>();
//        tempMap.put(rootNode.getNodeID(), rootNode);
//
//        if (leafNodes != null) {
//            for (T leafNode : leafNodes) {
//                T parentNode = rootNode;
//
//                List<Integer> pathNodeIDs = getPathNodes(leafNode.getNodePath());
//                for (Integer nodeID : pathNodeIDs) {
//                    if (tempMap.get(nodeID) == null) {
//                        T tempNode = (T) tClass.getDeclaredConstructor().newInstance();
//                        tempNode.setNodeID(nodeID);
//                        tempNode.setChildList(new ArrayList<T>());
//
//                        parentNode.getChildList().add(tempNode);
//                        tempMap.put(tempNode.getNodeID(), tempNode);
//                        nodeIDs.add(tempNode.getNodeID());
//                        parentNode = tempNode;
//                    }
//                }
//
//                List<Integer> childLeafIDList = parentNode.getChildLeafIDList();
//                if(childLeafIDList == null){
//                    childLeafIDList = new ArrayList<Integer>();
//                }
//                childLeafIDList.add(leafNode.getNodeID());
//            }
//        }
//    }

    public static Set<Integer> getLeafByNode(ProjectTreeNode root){
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

    public static List<Integer> getPathNodes(String path) {
        List<Integer> pathNodes = new ArrayList<Integer>();
        if (path != null) {
            String[] nodes = path.split(":");
            for (String node : nodes) {
                if (node != null && !node.isEmpty()) {
                    try {
                        pathNodes.add(Integer.valueOf(node));
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        }
        return pathNodes;
    }
    
//    public static String getParentPath(BaseTreeNode parentNode){
//        return getParentPath(parentNode.getNodePath(),parentNode.getNodeID());
//    }

    public static String getParentPath(String nodePath, Integer nodeID) {
        if (nodePath == null || nodePath.isEmpty()) {
            nodePath = ":" + nodeID + ":";
        } else {
            nodePath = nodePath + nodeID + ":";
        }
        return nodePath;
    }

    public static List<LevelTreeNode> buildTree(List<LevelTreeNode> treeNodeList,
                                                List<Map<String, Object>> rootNodeList)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<LevelTreeNode> result = new ArrayList<LevelTreeNode>();
        if (rootNodeList != null) {
            Map<Integer, LevelTreeNode> tempMap = new HashMap<Integer, LevelTreeNode>();
            for (Map<String, Object> temp : rootNodeList) {
                buildTreeNode(treeNodeList, result, tempMap, temp);
            }
        }
        return result;
    }

    private static void buildTreeNode(List<LevelTreeNode> treeNodeList, List<LevelTreeNode> result,
                               Map<Integer, LevelTreeNode> tempMap, Map<String, Object> temp)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取一个根节点
        LevelTreeNode rootNode = new LevelTreeNode();
        Integer rootNodeID = (Integer) temp.get("nodeID");
        if (!treeNodeList.isEmpty()) {
            rootNode = treeNodeList.get(0);
            tempMap.put(rootNode.getNodeID(), rootNode);
            treeNodeList.remove(0);

            //构建这个根节点的树
            LevelTreeNode tempNood = new LevelTreeNode();
            while (treeNodeList != null && tempNood != null) {
                tempNood = treeNodeList.get(0);
                String nodePath = tempNood.getNodePath();
                String[] pathNodeIDs = nodePath.split(":");
                if (pathNodeIDs.length > 1) {
                    tempNood = addTreeNode(treeNodeList, tempMap, tempNood, pathNodeIDs[pathNodeIDs.length - 1]);
                } else {
                    tempNood = null;
                }
            }

            result.add(rootNode);
        }
    }

    private static LevelTreeNode addTreeNode(List<LevelTreeNode> treeNodeList, Map<Integer, LevelTreeNode> tempMap,
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

}
