package com.lee.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    文件路径
        String realPath = "E:\\OneDrive\\code\\javaweb\\javaweb-02-servlet\\response\\src\\main\\resources\\士大夫.png";
        System.out.println("download file path"+realPath);
//    文件名
        // \\为转义字符
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
//    设置浏览器支持下载
        resp.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
//    获取下载输入流
        FileInputStream in = new FileInputStream(realPath);
//    创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
//    获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
//    将FileOutputStream写入buffer
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
//    用OutputStream将缓冲区写到客户端

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }



}
