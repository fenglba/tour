package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
//    查询用户名是否存在
    public User findUserByUserName(String Username);

//    注册用户
    public void register(User user);

//    根据激活码查询用户
    public User findUserByCode(String code);
//    修改用户状态
    public void updateUserStutus(User user);
//      登录
    public User loginUser(String username,String password);
//根据用户名查询激活状态
    public User findStatusByUserName(String username);
//获取分类数据
    public List<Category> getCategoryList();
}
