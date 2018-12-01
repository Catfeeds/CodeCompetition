package com.isoftstone.pmit.project.hrbp.entity;


import java.util.List;

public class ProjectTreeNode{
    private int nodeID;
    private String nodePath;
    private String nodeName;
    private List<ProjectTreeNode> childList;
    private boolean isLeafNode;
    private List<Integer> childLeafIDList;
    private boolean isProject;
    private String projcetID;
    private String projectName;
    private String pmID;
    private String pmName;

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

    public List<ProjectTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<ProjectTreeNode> childList) {
        this.childList = childList;
    }

    public boolean getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public List<Integer> getChildLeafIDList() {
        return childLeafIDList;
    }

    public void setChildLeafIDList(List<Integer> childLeafIDList) {
        this.childLeafIDList = childLeafIDList;
    }

    public boolean getIsProject() {
        return isProject;
    }

    public void setIsProject(boolean isProject) {
        this.isProject = isProject;
    }

    public String getProjcetID() {
        return projcetID;
    }

    public void setProjcetID(String projcetID) {
        this.projcetID = projcetID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPmID() {
        return pmID;
    }

    public void setPmID(String pmID) {
        this.pmID = pmID;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }
}
