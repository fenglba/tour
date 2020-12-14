package com.icis.dao.impl;

import com.icis.dao.CategoryDao;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());


    @Override
    public List<Category> getCategoryName() {
        String sql = "SELECT * FROM tab_category ORDER BY cid LIMIT 0,3";
        return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public List<Route> getRouteListByCategoryCid(Integer cid) {
       String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT 0,4";
       return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Route> getRouteListByCategoryCid2(Integer cid) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT 0,8";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Route> getRouteListByCategoryCid3(Integer cid) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT 0,8";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public Category getCategoryNameByCid(Integer cid) {
        String sql = "select * from tab_category where cid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Category.class),cid);
    }
}
