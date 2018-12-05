package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class BaseTreeNode {
    private int nodeID;
    private String nodeName;
    private List<BaseTreeNode> childList;

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<BaseTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<BaseTreeNode> childList) {
        this.childList = childList;
    }
}
