import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;

public class ConnectionJDBC {

    public static Connection openConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgis_31_sample";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","123");
        props.setProperty("ssl","false");
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
