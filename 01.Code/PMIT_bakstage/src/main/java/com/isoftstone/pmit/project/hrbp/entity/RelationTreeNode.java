package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class RelationTreeNode {
    private int nodeID;
    private String nodeName;
    private String nodePath;
    private String nodeType;
    private boolean isTeamNode;
    private int teamID;

    private List<RelationTreeNode> childList;
    private List<RelationTreeNode> teamList;

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

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public boolean getIsTeamNode() {
        return isTeamNode;
    }

    public void setIsTeamNode(boolean isTeamNode) {
        this.isTeamNode = isTeamNode;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public List<RelationTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<RelationTreeNode> childList) {
        this.childList = childList;
    }

    public List<RelationTreeNode> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<RelationTreeNode> teamList) {
        this.teamList = teamList;
    }
}
