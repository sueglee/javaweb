package com.lee.servlet.user;

import com.lee.pojo.User;
import com.lee.service.user.UserService;
import com.lee.service.user.UserServiceImpl;
import com.lee.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //servlet控制层，调用业务层代码
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter LoginServlet");

        //get userCode and userPassword
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //compare with database
        UserService userService = new UserServiceImpl();
        //check the login user
        User user = userService.login(userCode, userPassword);

        if (user != null && user.getUserPassword().equals(userPassword)) {
            //user exist
            //set user information into session
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //redirect to homepage
//            resp.sendRedirect("/jsp/frame.jsp");404
            resp.sendRedirect("jsp/frame.jsp");
        } else {
            //no user exist back to login and warn error
            req.setAttribute("error", "username or password error");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
