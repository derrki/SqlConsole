package ua.com.dagget.sqlconsole;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

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
        stmt.executeUpdate("INSERT INTO public.user (id, name, password)" + "VALUES (1100, 'Stiven', 'Pupkin')");

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

        //table name

        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' ");
        String [] tables = new String[10];
        int index = 0;
        while (rs.next()){
            tables[index++] = rs.getString("table_name");
            System.out.println("table name: " + rs.getString("table_name"));
        }
        tables = Arrays.copyOf(tables, index + 1, String[].class);
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
}
