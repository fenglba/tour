package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Login;
import com.icis.pojo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetCookie")
public class GetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取cookie
        Cookie[] cookies = request.getCookies();
        String username=null;
        String password=null;
//        判断
        if (cookies!=null){
            for (Cookie cookie : cookies) {
               if ("username".equals(cookie.getName())){
                   username = cookie.getValue();

               }
               if ("password".equals(cookie.getName())){
                   password = cookie.getValue();

               }
            }
        }
        Login login = new Login(username,password);
//        将数据写回页面
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo info =new ResultInfo();
        info.setData(login);
        response.setContentType("application/json;charset=utf-8");
        String str = mapper.writeValueAsString(info);
        response.getWriter().write(str);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
