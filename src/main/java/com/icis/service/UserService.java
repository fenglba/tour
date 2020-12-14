package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

//用户接口  用于封装用户的功能
public interface UserService {
//    用户注册方法
    public boolean registerUser(User user);
//      通过激活码查找用户
    public User findUserCode(String code);
//设置用户的状态
    public void setUserStatus(User user);
//通过用户名查找用户
    public User findByUserName(String username);
//用户登录
    public User login(String username,String password);
//根据用户名查询激活状态
    public User findStatusByUserName(String username);
//获取分类列表数据
    public String getCategoryList();

}
