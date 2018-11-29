package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class BaseTreeNode {
    private int nodeID;
    private String nodePath;
    private String nodeName;
    private List<BaseTreeNode> childList;

    private boolean isLeafNode;

    private List<Integer> childLeafIDList;

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setLeafNode(boolean leafNode) {
        isLeafNode = leafNode;
    }

    public List<BaseTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<BaseTreeNode> childList) {
        this.childList = childList;
    }

    public List<Integer> getChildLeafIDList() {
        return childLeafIDList;
    }

    public void setChildLeafIDList(List<Integer> childLeafIDList) {
        this.childLeafIDList = childLeafIDList;
    }

}
