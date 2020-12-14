package com.icis.routetest;

import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.Route;
import com.icis.pojo.Seller;
import org.junit.Test;

import java.util.List;

public class RouteTest {
    private RouteDao routeDao = new RouteDaoImpl();
//    测试 根据旅游线路id  获得旅游线路
    @Test
    public void getRouteByCidTest(){
        List<Route> routes = routeDao.getRouteByCid(8);
        for (Route route : routes) {
            System.out.println(route.getRid()+"------"+route.getRname());
        }
    }
//    测试根据旅游线路的id获得总记录数
    @Test
    public void findTotalCountForRouteByCidTest(){
        int totalCount = routeDao.findTotalCountForRouteByCid(5);
        System.out.println(totalCount);
    }
//    测试  根据旅游线路的id  当前页码  和每页显示的记录数  获得分页的数据
    @Test
    public void findRouteByPageTest(){
        List<Route> routeList = routeDao.findRouteByPage(5, 2, 4);
        for (Route route : routeList) {
            System.out.println(route.getRid()+"------"+route.getRname());
        }
    }
    @Test
    public void getRouteByLowPrice(){
        List<Route> routeList = routeDao.getRouteByLowPrice();
        for (Route route : routeList) {
            System.out.println(route.getRid()+"------"+route.getRname());
        }
    }
    @Test
    public void getRouteByQuery(){
        String rname = null;
        Integer cid = 1 ;
        List<Route> routeList = routeDao.getRouteByQuery(rname,cid,1,4);
        for (Route route : routeList) {
            System.out.println(route);
        }
    }
    @Test
    public void getTotalCountByQuery(){
        String rname = null;
        Integer cid = 1;
        int count = routeDao.getTotalCountByQuery(rname, cid);
        System.out.println(count);
    }

    @Test
    public void getRouteByRouteId(){
        Route route = routeDao.getRouteByRouteId(20);
        System.out.println(route);
    }
    @Test
    public void getSellerByRouteId(){
        Seller seller = routeDao.getSellerByRouteId(20);
        System.out.println(seller);
    }
}
