package com.icis.service.impl;

import com.icis.dao.RouteDao;
import com.icis.dao.RouteImgDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.dao.impl.RouteImgDaoImpl;
import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;
import com.icis.service.RouteService;
import com.icis.service.UserService;

import java.util.List;

public class RouteServiceImpl implements RouteService{
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        return routeDao.getRouteByCid(cid);
    }

    @Override
    public Integer findTotalCountForRouteByCid(Integer cid) {
        return routeDao.findTotalCountForRouteByCid(cid);
    }

    @Override
    public List<Route> findRouteByPage(Integer cid, Integer currentPage, Integer rows) {
        return routeDao.findRouteByPage(cid,currentPage,rows);
    }

    @Override
    public List<Route> getRouteByLowPrice() {
        return routeDao.getRouteByLowPrice();
    }

    @Override
    public Route getRouteByRouteId(Integer rid) {
//        查询代理商家
        Seller seller = routeDao.getSellerByRouteId(rid);
//        将详情图片加进去
        List<RouteImg> routeImgList = routeImgDao.getImgListByRid(rid);
        Route route = routeDao.getRouteByRouteId(rid);
//        将代理商家加进去
        route.setSeller(seller);
        route.setRouteImgList(routeImgList);
        return route;
    }

    @Override
    public List<Route> getRouteByQuery(String rname,Integer cid,Integer currentPage,Integer rows) {
        return routeDao.getRouteByQuery(rname,cid,currentPage,rows);
    }

    @Override
    public int getTotalCountByQuery(String rname, Integer cid) {
        return routeDao.getTotalCountByQuery(rname,cid);
    }

    @Override
    public List<Route> queryByName(String rname) {
        return routeDao.queryByName(rname);
    }
}
