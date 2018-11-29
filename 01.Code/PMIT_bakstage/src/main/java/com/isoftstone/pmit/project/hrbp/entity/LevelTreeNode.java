package com.isoftstone.pmit.project.hrbp.entity;


public class LevelTreeNode extends BaseTreeNode{
    private boolean isProjectNode;
    private Integer ProjectTeamID;


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
