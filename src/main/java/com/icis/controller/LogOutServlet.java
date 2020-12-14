package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 退出登录  ==  注销当前用户名
 */
@WebServlet("/LogOut")
public class LogOutServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        //        创建数据返回集对象
        ResultInfo info = new ResultInfo();
        //        响应数据返回前端
//        设置编码
        response.setContentType("application/json;charset=utf-8");
        String str = mapper.writeValueAsString(info);
//        System.out.println("返回给客户端的json数据:"+str);
        response.getWriter().write(str);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
