import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class BackEnd {
   public static void Connection(String args[]) {
        Connection c = null;

        try {
            Class.forName("user");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }
}
