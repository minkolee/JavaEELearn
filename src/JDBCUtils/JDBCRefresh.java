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
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;

        connection = Tools.getConnection();

        try {
            ps = connection.prepareStatement("SELECT * FROM goods");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("数据库连接失败");

        } finally {
            Tools.slientCloseAll(connection, ps, rs);
        }
    }
}
