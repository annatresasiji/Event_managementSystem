package EventManagement_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class conn {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/events";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "himanshu";
    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    Statement s;

    public conn() {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventManagement_system", "root", "EjE4l3a02023");
            s = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        conn c = new conn();
    }
}
