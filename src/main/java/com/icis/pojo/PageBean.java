package com.icis.pojo;

import java.util.List;

//分页类
public class PageBean<T> {
//    当前页
    private Integer currentPage;
//    总页数
    private Integer totalPage;
//      总记录数
    private Integer totalCount;
//    每页记录数
    private Integer rows;
//    分页查询返回的数据
    private List<T> RouteList;

    public PageBean() {
    }

    public PageBean(Integer currentPage, Integer totalPage, Integer totalCount, Integer rows, List<T> routeList) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.rows = rows;
        RouteList = routeList;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<T> getRouteList() {
        return RouteList;
    }

    public void setRouteList(List<T> routeList) {
        RouteList = routeList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", rows=" + rows +
                ", RouteList=" + RouteList +
                '}';
    }
}
