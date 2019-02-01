package EnhancedMySource.MySource;


import java.sql.SQLException;

public class TestMySource {

    public static void main(String[] args) throws SQLException {

        MyDataSource sources = new MyDataSource();
        for (int i = 0; i < 19; i++) {
            new Thread(new MyThread(sources)).start();
        }
    }
}


