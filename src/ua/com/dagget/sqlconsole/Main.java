package ua.com.dagget.sqlconsole;

import java.sql.*;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class Main {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {



        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres",
                "postgres");

        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO public.user (id, name, password)" + "VALUES (600, 'Stiven', 'Pupkin')");

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



        connection.close();
    }
}
