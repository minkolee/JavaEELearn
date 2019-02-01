package EnhancedMySource.MySource;

/**
 * 增强connection的方法其实很简单,原来的对象实现了什么接口,就新建一个对象实现那个接口,然后包装那个类,等于用一个包装类替代原来的类的接口
 *
 */


import JDBCUtils.Tools;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {

    // 原来的问题出在使用了同一个连接对象,应该从工具类每次返回不同的链接对象

    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    static {
        for (int i = 0; i <3; i++) {
            Connection conn = Tools.getConnection();
            // 新版就不能使用直接的连接了,而要使用包装对象
            MyConnection connection = new MyConnection(pool, conn);
            pool.add(connection);
        }
    }

    public static void main(String[] args) {
        for (Connection c : pool) {
            System.out.println(c);
        }
    }

    public void release(Connection connection) {
        pool.add(connection);
    }


    @Override
    public Connection getConnection() throws SQLException {
        if (!pool.isEmpty()) {
            return pool.removeFirst();
        } else {
            try {
                Thread.sleep(100);
                return getConnection();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
