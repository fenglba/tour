package com.icis.controller;

import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/emailActive")
public class EmailActiveServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
//        查询该用户下的激活码
        User user = userService.findUserCode(code);
        System.out.println(user);
        if (user!=null){
//            如果存在此用户 激活
            userService.setUserStatus(user);
            response.getWriter().write("激活成功!");
        }else {
//            如果不存在  不激活
            response.getWriter().write("激活失败");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
