package logAnalysis_hive;

import java.sql.*;

/*
    Mysql 数据库工具类，包括连接 查询 插入 等操作方法
 */

public class DBUtil_Mysql {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static Connection getConnection() {
        String Url = "jdbc:mysql://23.83.240.234:3306/hive";
        String User = "root";
        String Passwd = "9MnKWd7X3Vexz4u7";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Url, User, Passwd);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet excuteQuery(String sql) {
        if (getConnection() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static ResultSet excuteQuery(String sql, Object[] obj) {
        if (getConnection() == null) {
            return null;
        }
        try {
            //ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);//使游标可以向前移动
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static int excuteUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConnection() == null) {
            return 0;
        }
        try {
            //ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);////使游标可以向前移动
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void DBclose() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
