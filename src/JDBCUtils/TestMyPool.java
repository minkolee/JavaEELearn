package JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMyPool {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
//            new Thread(new MyThread()).start();
            new MyThread().run();
        }
    }
}

class MyThread implements Runnable {
    public void run() {

        Connection connection = null;
        try {
            connection = new MyPool().getConnection("1","2");
            System.out.println(connection);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goods");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id")+"||"+rs.getString("name"));
            }
            connection.close();
            System.out.println("归还成功");
            System.out.println(MyPool.list.size());
            System.out.println("--------------------------------------------------------");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }
}

