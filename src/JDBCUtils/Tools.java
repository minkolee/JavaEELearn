package JDBCUtils;

import javax.xml.transform.Result;
import java.sql.*;

public class Tools {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false";
            String user = "root";
            String password = "fflym0709";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public static void slientCloseAll(Connection connection, PreparedStatement preparedStatement, ResultSet rs) {
        try {
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("出错了但是不影响运行");
        }
    }
}