import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/swing_app";
        String user = "root"; // Your DB username
        String password = ""; // Your DB password
        return DriverManager.getConnection(url, user, password);
    }
}
