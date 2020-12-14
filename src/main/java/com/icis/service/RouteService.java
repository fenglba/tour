package com.icis.service;

import com.icis.pojo.Route;

import java.util.List;

public interface RouteService {
    public List<Route> getRouteByCid(Integer cid);

    public Integer findTotalCountForRouteByCid(Integer cid);

    public List<Route> findRouteByPage(Integer cid, Integer currentPage, Integer rows);

    //    获得价格最低的五个旅游线路
    public List<Route> getRouteByLowPrice();

//    根据rid查询具体的旅游线路
    public Route getRouteByRouteId(Integer rid);


    //   模糊查询+ 根据旅游线路名称去查询
    public List<Route> getRouteByQuery(String rname,Integer cid,Integer currentPage,Integer rows);

//    模糊+查询总记录数
    public int getTotalCountByQuery(String rname,Integer cid);

    public List<Route> queryByName(String rname);
}
