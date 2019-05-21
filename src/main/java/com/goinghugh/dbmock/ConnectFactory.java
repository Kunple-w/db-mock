package com.goinghugh.dbmock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接工厂
 *
 * @author yongxu wang
 * @date 2019-05-21 14:08
 **/
public class ConnectFactory {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/blog";
    private static final String USER = "root";
    private static final String PASS = "qwe122";

    public static Connection getConnect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
