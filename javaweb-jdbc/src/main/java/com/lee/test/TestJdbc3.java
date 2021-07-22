package com.lee.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc3 {

    @Test
    public void test() {
        //System.out.println("hello");
        //System.out.println(1/0);
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123";

        Connection connection = null;

        try {
            //1加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2连接数据库
            connection = DriverManager.getConnection(url, username, password);

            //3通知数据库开启事务 false开启
            connection.setAutoCommit(false);
            String sql = "update account set money = money - 100 where name = 'a';";
            connection.prepareStatement(sql).executeUpdate();

            //int i = 1/0;

            String sql2 = "update account set money = money + 100 where name = 'b';";
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit();
            System.out.println("success");
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
