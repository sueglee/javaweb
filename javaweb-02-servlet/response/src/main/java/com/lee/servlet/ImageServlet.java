package com.lee.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //后天实现浏览器自动刷新
        resp.setHeader("refresh","3");
        //创建图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //get image
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0,0,80,20);
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.BOLD,20));
        g.drawString(makeNum(),0,20);

        //告诉浏览器这个请求用图片方式打开
        resp.setContentType("image/png");
        //网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        ImageIO.write(image,"png",resp.getOutputStream());

    }

    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(99999)+"";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
