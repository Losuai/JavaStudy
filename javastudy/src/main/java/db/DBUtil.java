package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@JDBCConfig(ip="127.0.0.1", database = "test", user="root",password = "000000",encoding = "utf-8")
public class DBUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        JDBCConfig config = DBUtil.class.getAnnotation(JDBCConfig.class);
        String ip = config.ip();
        int port = config.port();
        String database = config.database();
        String user = config.user();
        String password = config.password();
        String encoding = config.encoding();
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",ip,port, database, encoding)+"&serverTimezone=GMT%2b8";
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        System.out.println(c);
    }
}
