package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        判断验证码
        String check = request.getParameter("check");
//        获取后端生成的验证码
        HttpSession session =request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        做完验证后，销毁session
        request.getSession().invalidate();
        if (!checkcode_server.equalsIgnoreCase(check)||checkcode_server==null){
            //        定义一个ResultInfo对象  用于封装返回页面的数据
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //        响应数据返回前端
            ObjectMapper mapper = new ObjectMapper();
//        设置编码
            response.setContentType("application/json;charset=utf-8");
            String str = mapper.writeValueAsString(info);
            response.getWriter().write(str);
            return;
        }


//        从前端页面获取数据
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
//        定义实体类
        User user = new User();
//        使用工具类封装数据
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("前端数据user:"+user);
//        注册一个用户服务类
        UserService userService = new UserServiceImpl();
//        调用接口的注册方法
        boolean flag = userService.registerUser(user);
//        定义一个ResultInfo对象  用于封装返回页面的数据
        ResultInfo info = new ResultInfo();
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("用户名已经存在");
        }

//        响应数据返回前端
        ObjectMapper mapper = new ObjectMapper();
//        设置编码
        response.setContentType("application/json;charset=utf-8");
        String str = mapper.writeValueAsString(info);
        response.getWriter().write(str);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
