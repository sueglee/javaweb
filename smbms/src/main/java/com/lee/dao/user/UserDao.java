package com.lee.dao.user;

import com.lee.pojo.Role;
import com.lee.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    //得到要登陆的用户
    User getLoginUser(Connection connection, String userCode);

    //修改用户密码
    int updatePwd(Connection connection, int id, String password) throws SQLException;

    //查询用户总数
    int getUserCount(Connection connection, String username, int userRole) throws SQLException;

    //获取用户列表
    List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize);

    //判断是否存在
    boolean isExist(Connection connection, String userCode) throws SQLException;

    //add
    boolean add(Connection connection, User user) throws SQLException;


}
