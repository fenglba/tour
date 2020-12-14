package com.icis.dao.impl;

import com.icis.dao.RouteDao;
import com.icis.pojo.Route;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
    private String sql = null;

    //    根据cid的获取旅游路线数据
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        sql = "SELECT * FROM tab_route WHERE cid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid);

    }

    //     查询相应cid的总记录数
    @Override
    public int findTotalCountForRouteByCid(Integer cid) {
        sql = "select count(*) from tab_route where cid = ? ";
        return template.queryForObject(sql, Integer.class, cid);
    }

    //    根据当前页和每页显示的记录数返回相应的数据
    @Override
    public List<Route> findRouteByPage(Integer cid, Integer currentPage, Integer rows) {
        sql = "select * from tab_route where cid = ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, (currentPage - 1) * rows, rows);
    }

    @Override
    public List<Route> getRouteByLowPrice() {
        sql = "SELECT * FROM tab_route ORDER BY price LIMIT 0,5";
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> getRouteByQuery(String rname, Integer cid, Integer currentPage, Integer rows) {
        List<Route> routeList =  new ArrayList<>();
        if (cid == null) {
            String strName = "%" + rname + "%";
            String sql = "SELECT * FROM tab_route WHERE rname like ? limit ?,?";
            routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), strName, (currentPage - 1) * rows, rows);
        }else if (rname.equals("null")||rname==null||rname==""){
            String sql = "SELECT * FROM  tab_route where cid = ? limit ?,?";
            routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid,(currentPage - 1) * rows, rows);
        }
        else {
            String strName = "%" + rname + "%";
            String sql = "SELECT * FROM tab_route WHERE cid = ? and rname like ? limit ?,?";
            routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, strName, (currentPage - 1) * rows, rows);
        }
//        System.out.println(routeList);
        return routeList;
    }

    @Override
    public int getTotalCountByQuery(String rname, Integer cid) {
        if (cid==null){
            String strName = "%" + rname + "%";
            String sql = "select count(*) from tab_route where rname like ?";
            return template.queryForObject(sql, Integer.class, strName);
        }else if (rname.equals("null")||rname==null||rname==""){
            String sql = "select count(*) from tab_route where cid = ?";
            return template.queryForObject(sql, Integer.class, cid);
        }
        else {
            String strName = "%" + rname + "%";
            String sql = "select count(*) from tab_route where cid = ? and rname like ?";
            return template.queryForObject(sql, Integer.class, cid, strName);
        }


    }

    @Override
    public List<Route> queryByName(String rname) {
        String strName = "%" + rname + "%";
        sql = "select * from tab_route where rname like ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), strName);

    }

    @Override
    public Route getRouteByRouteId(Integer rid) {
        String sql = "select * FROM  tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }

    @Override
    public Seller getSellerByRouteId(Integer rid) {
        String sql = "SELECT * FROM tab_seller WHERE sid = (SELECT sid FROM tab_route WHERE rid = ?)";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), rid);

    }

}
