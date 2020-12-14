package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    //    创建数据源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());


    @Override
    public User findUserByUserName(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);

        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public void register(User user) {
//        定义sql语句
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) " +
                "values(?,?,?,?,?,?,?,?,?)";
//        执行
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User findUserByCode(String code) {
        try {
            String sql = "select * from tab_user where code = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateUserStutus(User user) {
        String sql = "update tab_user set status = ? where code = ?";
        template.update(sql, user.getStatus(), user.getCode());
    }

    @Override
    public User loginUser(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (Exception e) {
            return null;
        }
        return user;


    }

    @Override
    public User findStatusByUserName(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and status = 'Y'";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),username);
        }catch (Exception e){
            return null;
        }
        return user;


    }
//获得分类列表数据
    @Override
    public List<Category> getCategoryList() {
        String sql = "SELECT * FROM tab_category ORDER BY cid";
        return template.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }


}
