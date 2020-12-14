package com.icis.dao;

import com.icis.pojo.Route;
import com.icis.pojo.Seller;

import java.util.List;

public interface RouteDao {
//根据旅游线路分类id  查询旅游线路
    public List<Route> getRouteByCid(Integer cid);

//    查询旅游线路的总记录数
    public int findTotalCountForRouteByCid(Integer cid);
//    根据当前页和每页显示的记录数返回相应的数据
    public List<Route> findRouteByPage(Integer cid,Integer currentPage,Integer rows);
//    获得价格最低的五个旅游线路
    public List<Route> getRouteByLowPrice();


//    根据rid查询具体的旅游线路
    public Route getRouteByRouteId(Integer rid);
//    根据rid找到指定代理商家
    public Seller getSellerByRouteId(Integer rid);

    //   模糊查询+ 根据旅游线路名称去查询
    public List<Route> getRouteByQuery(String rname,Integer cid,Integer currentPage,Integer rows);

    //    模糊+查询总记录数
    public int getTotalCountByQuery(String rname,Integer cid);

    public List<Route> queryByName(String rname);
}
