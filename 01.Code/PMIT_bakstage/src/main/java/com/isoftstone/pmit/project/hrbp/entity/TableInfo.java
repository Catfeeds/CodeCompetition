package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class TableInfo {
    private List<TableHead> tableHeads;
    private List<TableRow> tableRows;
    private long totleSize;

    public List<TableHead> getTableHeads() {
        return tableHeads;
    }

    public void setTableHeads(List<TableHead> tableHeads) {
        this.tableHeads = tableHeads;
    }

    public List<TableRow> getTableRows() {
        return tableRows;
    }

    public void setTableRows(List<TableRow> tableRows) {
        this.tableRows = tableRows;
    }

    public long getTotleSize() {
        return totleSize;
    }

    public void setTotleSize(long totleSize) {
        this.totleSize = totleSize;
    }
}
