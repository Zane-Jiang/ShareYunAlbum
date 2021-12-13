package com.ServiceUtils;

import java.sql.*;
import java.util.Properties;


public class DBConnection {


    /**
     * 获取数据库连接
     * */
    public static Connection getConnection() throws Exception {
//        这里使用配置文件的方式好像打包的时候会出错，所以就直接加载在文件里了吧
//        // 1.读取配置文件中的4个基本信息
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
//
//        Properties pros = new Properties();
//        pros.load(is);
//
//        String user = pros.getProperty("user");
//        String password = pros.getProperty("password");
//        String url = pros.getProperty("url");
//        String driverClass = pros.getProperty("driverClass");
//
//        // 2.加载驱动
//        Class.forName(driverClass);
//
//        // 3.获取连接
//        Connection conn = DriverManager.getConnection(url, user, password);
//
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/ShareYunAlbum";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection conn = driver.connect(url, info);
        return conn;
    }

    /**
     * 关闭数据库资源
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库资源
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
