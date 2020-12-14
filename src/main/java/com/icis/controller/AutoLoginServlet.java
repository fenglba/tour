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
 * 第二种思路  不需要填验证码 根据cookie存储的用户信息  吻合直接跳转到首页
 */
@WebServlet("/AutoLogin")
public class AutoLoginServlet extends HttpServlet {
    private ObjectMapper mapper =new ObjectMapper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        //        响应数据返回前端
//        设置编码
        response.setContentType("application/json;charset=utf-8");
        String str = mapper.writeValueAsString(info);
        response.getWriter().write(str);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
