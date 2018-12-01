package com.isoftstone.pmit.project.hrbp.entity;


import java.util.List;

public class LevelTreeNode{
    private int nodeID;
    private String nodePath;
    private String nodeName;
    private boolean isLeafNode;
    private boolean isRelationNode;
    private Integer relationID;

    private List<LevelTreeNode> childList;

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

    public boolean getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public boolean getIsRelationNode() {
        return isRelationNode;
    }

    public void setIsRelationNode(boolean isRelationNode) {
        this.isRelationNode = isRelationNode;
    }

    public Integer getRelationID() {
        return relationID;
    }

    public void setRelationID(Integer relationID) {
        this.relationID = relationID;
    }

    public List<LevelTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<LevelTreeNode> childList) {
        this.childList = childList;
    }
}
