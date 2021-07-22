package com.lee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

//中文数据传递
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        Cookie[] cookies = req.getCookies();
        PrintWriter out = resp.getWriter();

        if (cookies != null) {
            //如果存在cookie
            out.write("上次访问时间");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie名字
                if ("lastTime".equals(cookie.getName())) {
                    out.write(URLDecoder.decode(cookie.getValue(),"utf-8"));
                }
            }
        } else {
            out.write("first enter");
        }

        //服务器给客户端一个cookie
        Cookie cookie = new Cookie("lastTime", URLEncoder.encode("看看","utf-8"));

        //cookie有效期为1天
        cookie.setMaxAge(24*60*60);

        resp.addCookie(cookie);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
