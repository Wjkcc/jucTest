package company.reflect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils
{
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hello?characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    // 实例锁
    private static final Object instanceLock = new Object();
    private static DBUtils db = null;
    private DBUtils() {
        super();
    }
    public static DBUtils getInstance() {
        if (db == null) {
            synchronized (instanceLock) {
                if (db == null) {
                    db = new DBUtils();
;                }
            }
        }
        return db;
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection(
                            URL,
                            USERNAME,
                            PASSWORD);
        }catch (Exception e) {
            System.out.println("mysql connection error");
            System.out.println(e.getMessage());
            throw e;
        }

        return connection;
    }

    private static class Session {
        int save(String sql) throws SQLException, ClassNotFoundException
        {
            Connection connection = getInstance().getConnection();
            return 0;
        }
    }
}
