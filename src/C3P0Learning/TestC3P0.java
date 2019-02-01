package C3P0Learning;

import JDBCUtils.Tools;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * C3P0使用XML配置文件 或者 直接配置
 * 一般都是使用XML配置文件 c3p0-config.xml
 * 配置文件的名称是固定的,放在src目录之下
 */

public class TestC3P0 {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DataSource dataSource = new ComboPooledDataSource();


        connection = dataSource.getConnection();

        try {
            ps = connection.prepareStatement("SELECT * FROM goods");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name")+" | "+ rs.getInt("quantity"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("数据库连接失败");

        } finally {
            connection.close();
            Tools.slientCloseAll(connection, ps, rs);
        }

    }





}
