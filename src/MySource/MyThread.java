package MySource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyThread implements Runnable {

    private MyDataSource myDataSource;
    private Connection connection;
    MyThread(MyDataSource source) throws SQLException {
        this.myDataSource = source;
        this.connection = myDataSource.getConnection();
    }

    public void run() {
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT  * FROM  goods");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("quantity"));
            }

            //这里必须释放回去,否则后边线程在阻塞,无法获得连接对象
            myDataSource.release(connection);
            //但是这里有个问题是,还调用了自己写的release方法,如何能增强.close()方法呢
            System.out.println("-------------------------------关闭成功--------------------------------");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}