package com.lee.service.user;

import com.lee.dao.BaseDao;
import com.lee.dao.user.UserDao;
import com.lee.dao.user.UserDaoImpl;
import com.lee.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    //业务层调用dao层，要引用dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    //用户登陆
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return  user;

    }

    //根据用户id修改密码
    //创建事务，可能回滚
    @Override
    public boolean updatePwd(int id, String pwd) {
        System.out.println("UserService:"+pwd);
        Connection connection = null;
        boolean flag = false;
        connection = BaseDao.getConnection();
        //change password
        try {
            if (userDao.updatePwd(connection, id, pwd) > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null, null);
        }
        return flag;
    }

    //查询用户数量
    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;

        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection,username, userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;

        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);

        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Override
    public boolean isExist(String userCode) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            flag = userDao.isExist(connection, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean add(User user) {
        boolean flag = true;
        UserDaoImpl userDao = new UserDaoImpl();
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            userDao.add(connection, user);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }

    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        //int count = userService.getUserCount(null, 3);
        //System.out.println(count);
        //getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)
        //ArrayList<User> list = userService.getUserList("test", 0, )

//        List userList = userService.getUserList(null, 0, 1, 5);
//        System.out.println("userlist'size"+(userList.size()));
//        for (Object obj : userList) {
//            System.out.println(((User)obj).getUserName());
//        }
        User user = new User();
        user.setUserCode("sss");
        user.setUserName("ddd");
        user.setUserPassword("11");
        user.setGender(1);
        user.setBirthday(Date.valueOf("3333-3-3"));
        user.setPhone("33333");
        user.setAddress("333");
        user.setUserRole(3);
        boolean flag= userService.add(user);
        System.out.println(flag);

    }

}
