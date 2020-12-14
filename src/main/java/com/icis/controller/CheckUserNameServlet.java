package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkUserName")
public class CheckUserNameServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       获取用户名
        String username = request.getParameter("username");
//       判断用户名是否在数据库中存在
        User user = userService.findByUserName(username);
//        定义封装返回数据的对象
        ResultInfo info = new ResultInfo();

        if (user!=null){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("用户名不存在");
        }
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(str);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
