package com.lee.filter;

import com.lee.pojo.User;
import com.lee.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //get user from session
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null) {
            //user should login
            response.sendRedirect("/smbms/login.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
