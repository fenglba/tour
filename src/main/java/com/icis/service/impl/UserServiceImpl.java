package com.icis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.utils.JedisUtil;
import com.icis.utils.MailUtils;
import com.icis.utils.UuidUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(User user) {
        User u = userDao.findUserByUserName(user.getUsername());
        if (u != null) {
            return false;
        }
//        注册用户
//        设置激活码
        user.setCode(UuidUtil.getUuid());
//        设置用户状态 此时还不能登录  为"N"
        user.setStatus("N");
//          保存用户信息
        userDao.register(user);
//        获取邮箱地址
        String email = user.getEmail();
//        发送邮件
        MailUtils.sendMail(email, "请点击一下链接激活:http://localhost:8039/travel/emailActive?code=" + user.getCode(), "激活账号");

        return true;
    }

    //根据激活码查找用户查询
    @Override
    public User findUserCode(String code) {
        return userDao.findUserByCode(code);
    }

    //激活成功  设置“Y”  保存数据
    @Override
    public void setUserStatus(User user) {
//        设置状态
        user.setStatus("Y");
//        在数据库中修改状态
        userDao.updateUserStutus(user);
    }

    //通过用户名查找用户
    @Override
    public User findByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        try {
            user = userDao.findUserByUserName(username);
            if (user.getStatus().equals("Y")) {
                User u = null;
                try {
                    u = userDao.loginUser(username, password);
                } catch (Exception e) {
                    return null;
                }
                return u;

            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }


    }
//根据用户名查询激活状态
    @Override
    public User findStatusByUserName(String username) {
        return userDao.findStatusByUserName(username);
    }
//获得分类列表数据
    @Override
    public String getCategoryList() {
//        使用redis缓存技术
//        获取redis客户端连接
        Jedis jedis = JedisUtil.getJedis();
        String categoryList = jedis.get("categoryList");
//        判断categoryList是否为空
        if (categoryList==null || categoryList.length()==0){
//            redis中没有数据
            System.out.println("redis中没有该数据，正在查询数据库。。。");
//            从数据库中查找
            List<Category> List = userDao.getCategoryList();
//            将数据序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                categoryList = mapper.writeValueAsString(List);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("categoryList",categoryList);
            jedis.close();
        }else {
            System.out.println("redis有缓存，缓存数据中。。。。");
        }

        return categoryList;

    }


}
