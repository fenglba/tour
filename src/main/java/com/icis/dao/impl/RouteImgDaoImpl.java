package com.icis.dao.impl;

import com.icis.dao.RouteImgDao;
import com.icis.pojo.RouteImg;
import com.icis.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<RouteImg> getImgListByRid(Integer rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);

    }
}
