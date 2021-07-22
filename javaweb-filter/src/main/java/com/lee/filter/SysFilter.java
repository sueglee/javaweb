package com.lee.filter;

import com.lee.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //ServletRequest father HttpServletRequest son
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object userSession = request.getSession().getAttribute(Constant.USER_SESSION);
        if (userSession == null) {
            System.out.println("session is null");
            request.getSession().setAttribute(Constant.USER_SESSION,request.getSession().getId());
            response.sendRedirect("/error.jsp");
        } else {
            System.out.println("session is not null");
            String username = request.getParameter("username");
            System.out.println("username is " + username);
            switch (username) {
                case "admin" :
                    response.sendRedirect("/sys/success.jsp");
                    break;
                case "vip1" :
                    response.sendRedirect("/sys/vip1/success.jsp");
                    break;
                case "vip2" :
                    response.sendRedirect("/sys/vip2/success.jsp");
                    break;
                case "vip3" :
                    response.sendRedirect("/sys/vip3/success.jsp");
                    break;
                default: response.sendRedirect("/error.jsp");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
