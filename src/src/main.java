import java.sql.*;

public class main {
    public static void main( String args[] ) {
        Connection c = null;

        try {
            Class.forName("user");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}