package com.isoftstone.pmit.project.hrbp.entity;

public class TupleData {
    private String name;
    private String value;

    public TupleData() {
    }

    public TupleData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
