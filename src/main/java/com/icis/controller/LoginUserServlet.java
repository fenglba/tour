package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginUser")
public class LoginUserServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        判断验证码
        String check = request.getParameter("check");
//        获取后端生成的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        做完验证后，销毁session
        session.invalidate();
        if (!checkcode_server.equalsIgnoreCase(check) || checkcode_server == null) {
            //        定义一个ResultInfo对象  用于封装返回页面的数据
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //        响应数据返回前端
//        设置编码
            response.setContentType("application/json;charset=utf-8");
            String str = mapper.writeValueAsString(info);
            response.getWriter().write(str);
            return;
        }
//       获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        根据用户名查询用户的激活状态
        User u = userService.findStatusByUserName(username);
        if (u==null){
            //        定义一个ResultInfo对象  用于封装返回页面的数据
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("用户未被激活");
            //        响应数据返回前端
//        设置编码
            response.setContentType("application/json;charset=utf-8");
            String str = mapper.writeValueAsString(info);
            response.getWriter().write(str);
            return;
        }


        User user = userService.login(username,password);
        ResultInfo info = new ResultInfo();
        if (user!=null){
            info.setFlag(true);
        }else {
            info.setErrorMsg("登录失败，密码错误");
            info.setFlag(false);
        }
//        session保存用户名
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username",username);
//        使用cookie保存用户名和密码
        Cookie c1 = new Cookie("username",username);
        Cookie c2 = new Cookie("password",password);
//        设置cookie有效期  30秒
        c1.setMaxAge(30);
        c2.setMaxAge(30);
//        设置响应消息
        response.addCookie(c1);
        response.addCookie(c2);
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
