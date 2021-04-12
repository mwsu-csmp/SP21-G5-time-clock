import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class main {
    public static void main( String args[] ) {
        Connection c = null;

        try {
            Class.forName("user");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public byte[] makebyte(user modeldata) {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(modeldata);
            byte[] userAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(userAsBytes);
            return userAsBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    user st = new user("name", "username", "email", "address", "phoneNumber", "dob", "password");
    byte[] x = makebyte(st);
    }