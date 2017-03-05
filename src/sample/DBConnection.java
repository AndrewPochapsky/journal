package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String USERNAME ="root";
    private static final String PASSWORD = "root";
    private static final String CONN = "jdbc:mysql://localhost:3306/journal";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(CONN, USERNAME, PASSWORD);

    }

}

