package com.lee.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.lee.pojo.Role;
import com.lee.pojo.User;
import com.lee.service.role.RoleServiceImpl;
import com.lee.service.user.UserService;
import com.lee.service.user.UserServiceImpl;
import com.lee.util.Constants;
import com.lee.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("savepwd".equals(method) && method != null) {
            this.updatePwd(req, resp);
        } else if ("pwdmodify".equals(method) && method != null) {
            this.pwdModify(req, resp);
        } else if ("query".equals(method) && method != null) {
            this.query(req, resp);
        } else if ("getrolelist".equals(method) && method != null) {
            this.getRoleList(req, resp);
        } else if ("ucexist".equals(method) && method != null) {
            this.ucExist(req, resp);
        } else if ("add".equals(method)) {
            this.add(req, resp);
        }
    }

    //important hard
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询用户列表
        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        //temp为用户提交查询的页码，要校验
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        //查询数据库使用的页码
        int queryUserRole = 0;

        //获取用户列表
        List<User> userList = null;
        List<Role> roleList = null;
        UserServiceImpl userService = new UserServiceImpl();
        RoleServiceImpl roleService = new RoleServiceImpl();

        //第一次请求时为第一页，页面大小固定，pageSize最好放到配置文件
        int pageSize = Constants.PAGESIZE;
        int currentPageNo = 1;//默认页码为1

        System.out.println("queryUserName servlet--------"+queryUserName);
        System.out.println("queryUserRole servlet--------"+queryUserRole);
        System.out.println("query pageIndex--------- > " + pageIndex);

        if (queryUserName == null) {
            queryUserName = "";
        }
        if (temp != null && !"".equals(temp)) {
            //从temp中拿数据
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (Exception e) {
                resp.sendRedirect("error.jsp");
            }
        }

        //有了用户名和用户角色后可以开始查询了，所以需要显示当前查询到的总记录条数
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);

        //总页数的util类
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        //可显示的总页数
        int totalPageCount = pageSupport.getTotalPageCount();

        //控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        //获取用户列表展示
        userList = userService.getUserList(queryUserName, queryUserRole,currentPageNo, pageSize);
        req.setAttribute("userList",userList);
        //获取角色列表
        roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);

        System.out.println("userList's size:"+userList.size());
        System.out.println("roleList's size:"+roleList.size());

        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryUserRole",queryUserRole);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);

        //返回前端
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里拿id
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        System.out.println("UserServlet:"+newpassword);

        boolean flag = false;

        if (obj != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)obj).getId(), newpassword);
            if (flag) {
                req.setAttribute("message", "modify password success");
                //remove session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "modify password failed");
            }
        } else {
            req.setAttribute("message", "password set error");
        }

        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    //验证旧密码get from session
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        //从session里拿id
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        //Ajax传过来的值method=pwdmodify
        String oldpassword = req.getParameter("oldpassword");

        //万能的map:结果集
        HashMap<String, String> resultMap = new HashMap<String, String>();

        if (obj == null) {
            //session expired
            resultMap.put("result","sessionerror");
        } else if (StringUtils.isNullOrEmpty("oldpassword")) {
            //oldpassword is null or empty
            resultMap.put("result","error");
        } else {
            //password in session
            String userPassword = ((User)obj).getUserPassword();
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

        //返回类型为json
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //阿里巴巴的json工具类，转换格式
            //resultMap = ["result","sessionerror","result","error"]
            //JSON格式 {key: value};
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //getrolelist
    public void getRoleList(HttpServletRequest req, HttpServletResponse resp) {
        RoleServiceImpl roleService = new RoleServiceImpl();
        ArrayList<Role> roleList = null;
        roleList =  roleService.getRoleList();

        //不需要data.result
        //返回
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(roleList));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ucexist
    public void ucExist(HttpServletRequest req, HttpServletResponse resp) {
        String userCode  = req.getParameter("userCode");
        UserServiceImpl userService = new UserServiceImpl();
        HashMap<String, String > resultMap = new HashMap<>();
        if (userService.isExist(userCode)) {
            resultMap.put("userCode", "exist");
        } else {
            resultMap.put("userCode", "not exist");
        }

        //back
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //add
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean flag = false;
        //password should equal
        if (req.getParameter("userPassword").equals(req.getParameter("ruserPassword"))) {
            User user = new User();
            user.setUserCode(req.getParameter("userCode"));
            user.setUserName(req.getParameter("userName"));
            user.setUserPassword(req.getParameter("userPassword"));
            user.setGender(Integer.parseInt(req.getParameter("gender")));
            user.setBirthday(Date.valueOf(req.getParameter("birthday")));
            user.setPhone(req.getParameter("phone"));
            user.setAddress(req.getParameter("address"));
            user.setUserRole(Integer.parseInt(req.getParameter("userRole")));
            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.add(user);
        }

        System.out.println("userservlet"+flag);
        if (flag) {
            req.getRequestDispatcher("userlist.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("useradd.jsp").forward(req, resp);
        }
    }


}
