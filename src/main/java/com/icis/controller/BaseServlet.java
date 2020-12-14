package com.icis.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 基础servelt  理解为一个调用器
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            1.获得请求的路径
//        1.1根据请求的路径 获得具体的方法名
        String uri = request.getRequestURI();
//        System.out.println("请求的路径是："+uri);
//        1.2获得对应的servelt的字节码
        Class cis = this.getClass();
//
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
//        System.out.println(methodName);
//        3.获得类对应的成员方法
        try {
            Method method = cis.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
