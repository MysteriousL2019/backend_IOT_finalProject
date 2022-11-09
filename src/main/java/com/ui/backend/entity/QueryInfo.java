package com.ui.backend.entity;

import java.util.Arrays;

public class QueryInfo {


    private String[] date=new String[2];
    private int pageNum=1;
    private int pageSize=1;
    private String Id;

    public QueryInfo(String query, int pageNum, int pageSize, String[] date){
        this.date = date;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.Id=query;
    }

    @Override
    public String toString() {
        return "QueryInfo{" +
                "date=" + Arrays.toString(date) +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", Id='" + Id + '\'' +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
