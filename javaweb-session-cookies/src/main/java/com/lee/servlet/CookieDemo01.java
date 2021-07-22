package com.lee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        //服务器从客户端获取cookie,可能有多个cookie
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            //如果存在cookie
            out.write("上次访问时间");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie名字
                if ("lastTime".equals(cookie.getName())) {
                    //获得cookie的值
                    Long lastTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastTime);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("first enter");
        }

        //服务器给客户端一个cookie
        Cookie cookie = new Cookie("lastTime",System.currentTimeMillis()+"");

        //cookie有效期为1天
        cookie.setMaxAge(24*60*60);

        resp.addCookie(cookie);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
