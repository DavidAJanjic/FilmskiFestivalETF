package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String user = "root";
    public static String pass = "123456";

    public static String protocol = "jdbc:mysql:";
    public static String ip = "localhost";
    public static String port = "3306";
    public static String dbName = "filmfestival";

    public static String connectionString = protocol + "//" + ip + ":"
            + port + "/" + dbName + "?useSSL=false";
    
    public static String fullConnection = connectionString + user + pass;

    public static Connection otvoriKonekciju() throws SQLException {
        return DriverManager.getConnection(connectionString, user, pass);
    }
}
