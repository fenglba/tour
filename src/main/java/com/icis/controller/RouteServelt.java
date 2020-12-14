package com.icis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.service.RouteService;
import com.icis.service.impl.RouteServiceImpl;
import com.icis.utils.ObjectMapperUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//接受Route模块的所有的请求
@WebServlet("/route/*")
public class RouteServelt extends BaseServlet  {
    private RouteService routeService = new RouteServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private RouteDao routeDao  = new RouteDaoImpl();
//    根据cid获得旅游线路
    public void getRouteByCid(HttpServletRequest request, HttpServletResponse response){
     try {
         String cid1 = request.getParameter("cid");
//        数据转化
         Integer cid = Integer.parseInt(cid1);
//        调用service层方法
         List<Route> routes = routeService.getRouteByCid(cid);
//        把集合转化为字符串传递到前端页面
         String routesStr = mapper.writeValueAsString(routes);
//        将数据响应到前端
         response.getWriter().write(routesStr);
     }catch (Exception e){
         e.printStackTrace();
     }
    }
//    分页查询的方法
    public void SelectRouteByPage(HttpServletRequest request, HttpServletResponse response){
//        获得旅游线路id
        String cid = request.getParameter("cid");
//        获得当前页码
        String currentPage = request.getParameter("currentPage");
//        获得每页显示的记录数
        String rows = request.getParameter("rows");
//          指定旅游线路的总记录
        Integer totalCount = routeService.findTotalCountForRouteByCid(Integer.parseInt(cid));
//        获得分页的数据
        List<Route> routePageList = routeService.findRouteByPage
                (Integer.parseInt(cid), Integer.parseInt(currentPage), Integer.parseInt(rows));
//        总页数
        int totalPage = totalCount%Integer.parseInt(rows)==0?totalCount/Integer.parseInt(rows):totalCount/Integer.parseInt(rows)+1;
//        System.out.println("输出总页数："+totalPage);
//        创建一个封装分页数据的对象
        PageBean pageBean = new PageBean(Integer.valueOf(currentPage),
                totalPage,totalCount,Integer.valueOf(rows),routePageList);
//        将数据序列化为json格式
        try {
            String value = mapper.writeValueAsString(pageBean);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRouteByLowPrice(HttpServletRequest request, HttpServletResponse response){
//        调用服务类   获得最低价格的五条记录
        List<Route> routeList = routeService.getRouteByLowPrice();
//        将数据序列化为json
        try {
            String str = mapper.writeValueAsString(routeList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//      详情页：根据rid查询对应的数据
    public void getRouteByRouteId(HttpServletRequest request, HttpServletResponse response){
//        获取rid
        String rid = request.getParameter("rid");
//        转化为Int
        int Rid = Integer.parseInt(rid);
//        调用服务类  获得指定rid的数据
        Route route = routeService.getRouteByRouteId(Rid);
        try {
            String str = mapper.writeValueAsString(route);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRouteByQuery(HttpServletRequest request, HttpServletResponse response){
        String rname = request.getParameter("rname");
        System.out.println(rname);
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        List<Route> routeList = routeDao.getRouteByQuery(rname,Integer.parseInt(cid), Integer.parseInt(currentPage), Integer.parseInt(rows));
//        System.out.println(routeList);
        //          指定旅游线路的总记录
        Integer totalCount = routeDao.getTotalCountByQuery(rname,Integer.parseInt(cid));
        System.out.println(totalCount);
        //        总页数
        int totalPage = totalCount%Integer.parseInt(rows)==0?totalCount/Integer.parseInt(rows):totalCount/Integer.parseInt(rows)+1;
//        System.out.println("输出总页数："+totalPage);
//        创建一个封装分页数据的对象
        PageBean pageBean = new PageBean(Integer.valueOf(currentPage),
                totalPage,totalCount,Integer.valueOf(rows),routeList);
//        将数据序列化为json格式
        try {
            String value = mapper.writeValueAsString(pageBean);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void queryByName(HttpServletRequest request, HttpServletResponse response){
//        String rname = request.getParameter("rname");
//        List<Route> routes = routeService.queryByName(rname);
//        try {
//            String str = mapper.writeValueAsString(routes);
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


}
