package com.isoftstone.pmit.project.hrbp.common;

import com.isoftstone.pmit.project.hrbp.entity.ProjectTreeNode;

import java.util.*;

public class TreeUtil {


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

    public static String getParentPath(String nodePath, Integer nodeID) {
        if (nodePath == null || nodePath.isEmpty()) {
            nodePath = ":" + nodeID + ":";
        } else {
            nodePath = nodePath + nodeID + ":";
        }
        return nodePath;
    }
    
//    public static String getParentPath(BaseTreeNode parentNode){
//        return getParentPath(parentNode.getNodePath(),parentNode.getNodeID());
//    }
}
