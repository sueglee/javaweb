package com.lee;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailDemo01 {
    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        //设置邮件服务器
        prop.setProperty("mail.host","smtp.163.com");
        //邮件发送协议
        prop.setProperty("mail.transport.protocol","smtp");
        //需要验证用户名密码
        prop.setProperty("mail.smtp.auth","true");

        //QQ邮箱设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.ssl.socketFactory",sf);

        //1创建整个应用所需的环境信息的Session对象，与HttpServlet中session不同
        //QQ才有
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人用户名、授权码
                return new PasswordAuthentication("ethan_1963@163.com","授权码");
            }
        });
        session.setDebug(true);


        //2通过session得到transport对象
        Transport ts = session.getTransport();
        //3用邮箱用户名和密码连接服务器
        ts.connect("smtp.163.com","ethan_1963@163.com","OGXAMJOPBVYISGUF");
        //4创建邮件,要传递session
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ethan_1963@163.com"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("ethan_1963@163.com"));
        message.setSubject("hello");

        //message.setContent("<h1 style='color:red'>hello world!</h1></br>"+sb.toString(),"text/html;charset=UTF-8");
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("E:\\OneDrive\\all_files_here\\桌面\\1.png"));
        image.setDataHandler(dh);
        image.setContentID("a.jpg");

        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一份有图片的邮件<img src='cid:a.jpg'", "text/html;charset=UTF-8");

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();

        //5发送邮件
        ts.sendMessage(message, message.getAllRecipients());


        //6关闭连接
        ts.close();

    }
}
