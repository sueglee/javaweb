package com.lee.servlet.user;

import com.lee.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //remove session
        req.getSession().removeAttribute(Constants.USER_SESSION);
//        resp.sendRedirect("/login.jsp");
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
//        resp.sendRedirect("/login.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
