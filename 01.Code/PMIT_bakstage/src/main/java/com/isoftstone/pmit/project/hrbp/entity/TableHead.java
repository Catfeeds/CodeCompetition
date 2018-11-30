package com.isoftstone.pmit.project.hrbp.entity;

public class TableHead {
    private String colID;
    private String name;
    private int type;
    private boolean needHide;

    public TableHead() {
    }

    public TableHead(String colID, String name, int type, boolean needHide) {
        this.colID = colID;
        this.name = name;
        this.type = type;
        this.needHide = needHide;
    }

    public String getColID() {
        return colID;
    }

    public void setColID(String colID) {
        this.colID = colID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isNeedHide() {
        return needHide;
    }

    public void setNeedHide(boolean needHide) {
        this.needHide = needHide;
    }
}
