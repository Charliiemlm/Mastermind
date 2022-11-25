import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBBDD {
    
    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {

        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbName = "mastermind";
        String dbUsername = "user";
        String dbPassword = "pass";
        //String dbURL = "jdbc:mysql://localhost:3306/";
        String dbURL = "jdbc:mysql://192.168.33.11:3306/" + dbName + "?"
                + "enabledTLSProtocols=TLSv1.3"
                + "&autoReconnect=true"
                + "&useSSL=false";
        

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
    
}
