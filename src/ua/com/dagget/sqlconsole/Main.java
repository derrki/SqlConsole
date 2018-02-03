package ua.com.dagget.sqlconsole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres",
                "postgres");

    }
}
