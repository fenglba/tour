package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Category;
import com.icis.pojo.ResultInfo;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetCategory")
public class GetCategoryServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Category> categoryList = userService.getCategoryList();
////        将数据返回到客户端
//        ResultInfo info = new ResultInfo();
//        info.setData(categoryList);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);

//        使用redis缓存
        String categoryList = userService.getCategoryList();
//        System.out.println(categoryList);
//        ResultInfo info = new ResultInfo();
//        info.setData(categoryList);
        response.setContentType("application/json;charset=utf-8");
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
        response.getWriter().write(categoryList);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
