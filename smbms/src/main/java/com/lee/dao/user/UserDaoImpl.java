package com.lee.dao.user;

import com.lee.dao.BaseDao;
import com.lee.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    //得到要登陆的用户
    @Override
    public User getLoginUser(Connection connection, String userCode) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        //连接成功传sql语句
        if (connection != null) {
            String sql = "select * from smbms_user where userCode = ?";
            Object[] params = {userCode};//只有一个元素的数组

            try {
                rs = BaseDao.execute(connection, pstm, rs, sql, params);

                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                }
                BaseDao.closeResource(null, pstm, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        System.out.println("UserDao:"+password);

        PreparedStatement pstm = null;
        int execute = 0;

        if (connection != null) {
            String sql = "update smbms_user set userPassword = ? where id = ?;";
            Object params[] = {password, id};
            execute = BaseDao.execute(connection, pstm, sql, params);
        }
        return execute;
    }

    //根据用户名或角色查询用户总数
    //最难
    @Override
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;//不用包装类，可能为null

        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");
            //存放参数
            ArrayList<Object> list = new ArrayList<Object>();


            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append(" and u.userName like ?");
                //模糊查询
                //不用再加单引号list.add("‘%"+userName+"%’")；
                list.add("%"+username+"%");//index 0

            }
            if (userRole > 0 && userRole < 4) {
                sql.append(" and u.userRole = ?");//index = 1
                list.add(userRole);
            }


            //list to array
            Object[] params = list.toArray();

            System.out.println("UserDaoImpe->getUserCount:"+sql.toString());

            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

            if (rs.next()) {
                //从resultSet中获取最终用户数量
                count = rs.getInt("count");
                //connection在Service中还要用，不close
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    //获取用户列表
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) {
        //数据库中分页 limite startIndex, pageSize
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        //连接成功传sql语句
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*, r.roleName as userRoleName" +
                    " from smbms_user u, smbms_role r" +
                    " where u.userRole = r.id");
            List<Object> list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                //不用再加单引号list.add("‘%"+userName+"%’")；
                list.add("%"+userName+"%");//index 0
            }

            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);//index 1
            }

            //分页 limite startIndex, pageSize
            //输入的当前页从1开始
            //输入的当前页 = （输入的当前页 - 1） * pageSize

            sql.append(" order by creationDate DESC limit ?, ?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object params[] = list.toArray();
            System.out.println("sql:" + sql.toString());

            try {
                rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                    user.setUserRoleName(rs.getString("userRoleName"));
                    userList.add(user);
                }
                BaseDao.closeResource(null, pstm, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("userdao's userlist' size"+userList.size());
        return userList;
    }

    @Override
    public boolean isExist(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select count(1) as count from smbms_user u where u.userCode like ?";
        Object[] params = null;
        ArrayList<Object> list = new ArrayList<>();
        list.add(userCode);
        params = list.toArray();
        pstm = connection.prepareStatement(sql);

        rs = BaseDao.execute(connection, pstm, rs, sql, params);
        //resultSet用之前一定要rs.next();
        rs.next();
        int count = rs.getInt("count");

        BaseDao.closeResource(null, pstm, rs);
        System.out.println("count"+count);
        if (count != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean add(Connection connection, User user) throws SQLException {
        String sql = "insert into smbms_user(" +
                "userCode,userName,userPassword," +
                "gender,birthday,phone,address,userRole)" +
                " values (?,?,?,?,?,?,?,?)";

        boolean flag = false;
        ArrayList<Object> list = new ArrayList<>();
        PreparedStatement pstm = null;

        if (connection != null) {
            pstm = connection.prepareStatement(sql);
            if (user != null) {
                list.add(user.getUserCode());
                list.add(user.getUserName());
                list.add(user.getUserPassword());
                list.add(user.getGender());
                list.add(user.getBirthday());
                list.add(user.getPhone());
                list.add(user.getAddress());
                list.add(user.getUserRole());
            }
            Object[] params = list.toArray();

            int count = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);

            flag = count == 1 ? true : false;
        }

        return flag;
    }


}
