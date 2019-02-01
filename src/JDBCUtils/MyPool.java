package JDBCUtils;

/**
 * 测试还是有些问题,出现空指针,不知道什么意思,可能是由于得不到对象
 */

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyPool {

    public static LinkedList<Connection> list = new LinkedList<Connection>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 3; i++) {
                String url = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false";
                String user = "root";
                String password = "fflym0709";
                Connection connection = DriverManager.getConnection(url, user, password);
                MyConnection myConnection = new MyConnection(connection, list);
                list.add(myConnection);
            }
        } catch (SQLException ex) {
            System.out.println("获取连接失败");
        } catch (ClassNotFoundException ex) {
            System.out.println("加载驱动失败");
        }
    }


    public Connection getConnection() throws SQLException {

        // list 多线程不安全吧,所以加上锁
        synchronized (list) {
            if (!list.isEmpty()) {
                return list.removeFirst();
            } else {
                try {
                    Thread.sleep(100);
                    return getConnection();
                } catch (InterruptedException ex) {
                    return getConnection();
                }
            }

        }

    }

}
