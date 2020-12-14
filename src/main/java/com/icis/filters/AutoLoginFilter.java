package com.icis.filters;

import com.icis.pojo.Login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
//        String url = req.getRequestURI();
        //        获取cookie
        Cookie[] cookies = req.getCookies();
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
//        这里设置了username的值  是cookie赋予的
// 下面session存储username的值  所以session的存活时间是根据cookie的存活时间来定 不受其它影响

        Login login = new Login(username,password);
        if (login!=null){
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            chain.doFilter(req,resp);
        }else {
        //        放行资源
            chain.doFilter(req, resp);
        }
//        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
