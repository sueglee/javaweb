package com.lee.test;

import java.sql.*;

public class TestJdbc2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123";

        //1加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //3编写sql

        String sql = "insert into users(id, name, password, email, birthday) values (?,?,?,?,?);";
        //4预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 5);//第一个占位符？赋值为1
        preparedStatement.setString(2, "lee");
        preparedStatement.setString(3, "password");
        preparedStatement.setString(4, "ee@dd.com");
        preparedStatement.setString(5, String.valueOf(new Date(new java.util.Date().getTime())));

        //5执行sql
        int i = preparedStatement.executeUpdate();

        if (i > 0) {
            System.out.println("success");
        }

        //6
        preparedStatement.close();
        connection.close();

    }
}
