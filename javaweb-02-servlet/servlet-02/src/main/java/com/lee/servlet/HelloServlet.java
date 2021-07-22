package com.lee.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.getInitParameter();
//        this.getServletConfig();
//        this.getServletInfo();
        ServletContext context = this.getServletContext();//获得上下文

        String username = "lee";//data
        context.setAttribute("username",username);//save data in ServletContext,name is username,value is username

        System.out.println("hello module2");
    }
}
