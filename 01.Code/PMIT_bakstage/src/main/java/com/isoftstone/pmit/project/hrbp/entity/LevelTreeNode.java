package com.isoftstone.pmit.project.hrbp.entity;


import java.util.List;

public class LevelTreeNode{
    private int nodeID;
    private String nodePath;
    private String nodeName;
    private List<LevelTreeNode> childList;
    private boolean isLeafNode;
    private boolean isProjectNode;
    private Integer ProjectTeamID;


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

    public List<LevelTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<LevelTreeNode> childList) {
        this.childList = childList;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setLeafNode(boolean leafNode) {
        isLeafNode = leafNode;
    }

    public boolean isProjectNode() {
        return isProjectNode;
    }

    public void setProjectNode(boolean projectNode) {
        isProjectNode = projectNode;
    }

    public Integer getProjectTeamID() {
        return ProjectTeamID;
    }

    public void setProjectTeamID(Integer projectTeamID) {
        ProjectTeamID = projectTeamID;
    }
}
