package ksznldr.dbservice;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBService {
    private static DBService dbService;
    private static Connection connection;

    public static DBService instance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if(dbService == null){
            dbService = new DBService();
        }
        return dbService;
    }

    private DBService() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
    }

    public Connection getConnection() throws SQLException, IOException {
        if(connection == null) {
            Properties props = new Properties();
            props.load(new FileInputStream("conn.properties"));
//            props.setProperty("user", "postgres");
//            String url = "jdbc:postgresql://localhost/nodedb";
//            props.setProperty("password", "222");
//        props.setProperty("ssl","true");
            connection = DriverManager.getConnection(props.getProperty("url"), props);
        }
        return connection;
    }
}
