package JDBCUtils;

import java.sql.*;

/**
 * 1 注册驱动
 * 2 建立连接
 * 3 编译语句对象
 * 4 执行语句
 * 5 操作结果集
 */


public class JDBCRefresh {

    public static void main(String[] args) {
        // 注册驱动
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("驱动注册失败");
        }
        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false";
            String user = "root";
            String password = "fflym0709";

            connection = DriverManager.getConnection(url, user, password);
            ps = connection.prepareStatement("SELECT * FROM goods");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id")+'|'+rs.getString("name"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("数据库连接失败");
        }
    }
}
