package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class LevelTreeNode {
    private String nodeID;
    private String nodeName;
    private int level;
    private String levelID;
    private String levelName;
    private boolean isLeafNode;
    private List<LevelTreeNode>childList;

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public boolean getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public List<LevelTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<LevelTreeNode> childList) {
        this.childList = childList;
    }
}
