package com.lee.test;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123";

        //1加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2连接数据库,connection代表数据库,statement为向数据库发送静态sql语句的对象
        Connection connection = DriverManager.getConnection(url, username, password);

        //3用statement来CRUD
        Statement statement = connection.createStatement();

        //4编写sql
        String sql = "select * from users where id = 1";

        //executeUpdate返回受影响行数
        //int i = statement.executeUpdate(sql);

        //5执行查询sql语句，返回ResultSet
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            System.out.println("id="+rs.getObject("id"));
            System.out.println("name="+rs.getObject("name"));
            System.out.println("password="+rs.getObject("password"));
            System.out.println("email="+rs.getObject("email"));
            System.out.println("birthday="+rs.getObject("birthday"));

        }
        //6关闭连接，释放资源（先开的后关）
        if (rs != null) {
            rs.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
