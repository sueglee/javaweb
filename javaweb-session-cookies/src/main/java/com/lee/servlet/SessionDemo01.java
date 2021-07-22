package com.lee.servlet;

import com.lee.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //get session
        HttpSession session = req.getSession();
        //给session存东西
        session.setAttribute("name",new Person("lee",3));
        //获得session的id
        String id = session.getId();
        //判断session是否为新创建
        if (session.isNew()) {
            resp.getWriter().write("session created, id is"+id);
        } else {
            resp.getWriter().write("session id existed, id is"+id);
        }
        //session创建时做的事
//        Cookie cookie = new Cookie("JSESSIONID",id);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
