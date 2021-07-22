package com.lee.service.user;

import com.lee.pojo.User;

import java.util.List;

public interface UserService {
    //用户登陆
    public User login(String userCode, String pwd);

    //修改用户密码
    public boolean updatePwd(int id, String pwd);

    //查询用户数量
    public int getUserCount(String username, int userRole);

    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    //isExist
    public boolean isExist(String userCode);

    public boolean add(User user);

}
