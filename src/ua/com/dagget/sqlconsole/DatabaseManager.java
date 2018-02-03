package ua.com.dagget.sqlconsole;

import java.sql.*;
import java.util.Random;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class DatabaseManager {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        String database = "sqlcmd";
        String user = "postgres";
        String password = "postgres";

        Connection connection = getConnection(database, user, password);

        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO public.user (id, name, password)" + "VALUES (700, 'Stiven', 'Pupkin')");

        //select
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.user WHERE id > 200");
        while (rs.next()){
            System.out.println("id:" + rs.getString(1));
            System.out.println("name:" + rs.getString(2));
            System.out.println("password:" + rs.getString(3));
        }
        rs.close();
        stmt.close();

       //update
        PreparedStatement ps = connection.prepareStatement("UPDATE public.user SET password = ? WHERE id > 300");
        String pass = "password_" + new Random().nextInt();
        ps.setString(1, pass);
        ps.executeUpdate();
        ps.close();


        //delete
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM public.user " + "WHERE id > 100 AND id < 300");
        stmt.close();

        connection.close();
    }

    private static Connection getConnection(String database, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/" + database, user , password);
    }
}
