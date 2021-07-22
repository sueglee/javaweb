package com.lee.servlet;

import com.lee.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端请求参数
        String username = req.getParameter("username");

        req.getSession().setAttribute(Constant.USER_SESSION,req.getSession().getId());
        Object userSession = req.getSession().getAttribute(Constant.USER_SESSION);

        resp.sendRedirect("/sys/success.jsp");
        /*if (username.equals("admin")) {
            //login success
            req.getSession().setAttribute(Constant.USER_SESSION,req.getSession().getId());
            resp.sendRedirect("/sys/success.jsp");
        } else {
            //login failed
            resp.sendRedirect("/error.jsp");
        }*/

    }
}
