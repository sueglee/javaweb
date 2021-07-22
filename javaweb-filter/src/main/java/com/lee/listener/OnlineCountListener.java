package com.lee.listener;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网址在线人数,统计session
public class OnlineCountListener implements HttpSessionListener {
    //监听session创建
    //创建session就会触发一次这个事件
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        System.out.println(se.getSession().getId());
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");

        if (onlineCount == null) {
            onlineCount = new Integer(1);
        } else {
            int count = onlineCount.intValue();
            onlineCount = new Integer(count+1);
        }

        ctx.setAttribute("onlineCount",onlineCount);

    }

    //销毁session就会触发一次这个事件
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession();
    }
    //session销毁2种
    //1手动销毁 getSession.invalidate();
    //2自动销毁 web.xml配置session过期时间

}
