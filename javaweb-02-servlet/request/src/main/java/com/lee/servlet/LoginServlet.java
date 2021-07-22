package com.lee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] parameterValues = req.getParameterValues("hobby");


        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(parameterValues));

        System.out.println("====");
        //dispatcher转s发用相对路径，不要当前项目名，这里/代表当前web应用
        req.getRequestDispatcher("/"+"/success.jsp").forward(req,resp);
        System.out.println(req.getContextPath());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
