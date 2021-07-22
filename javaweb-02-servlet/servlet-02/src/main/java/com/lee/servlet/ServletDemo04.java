package com.lee.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        System.out.println("enter servlerdemo04");
        //context.getRequestDispatcher("/gp").forward(req, resp);
        //转发的请求路径
        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
        //调用forword实现请求转发
        requestDispatcher.forward(req, resp);

    }
}