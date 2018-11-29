package com.isoftstone.pmit.project.hrbp.entity;


public class ProjectTreeNode extends BaseTreeNode {
    private boolean isProject;
    private String projcetID;
    private String projectName;
    private String pmID;
    private String pmName;


    public boolean isProject() {
        return isProject;
    }

    public void setProject(boolean project) {
        isProject = project;
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
