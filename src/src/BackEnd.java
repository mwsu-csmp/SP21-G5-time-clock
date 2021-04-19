import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class BackEnd {



   public  Connection connect() {
        Connection c = null;

        try {
            Class.forName("user");
            c = DriverManager.getConnection("jdbc:sqlite:c://sqlite/users.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        return c;
    }


    public void insertUser(String name, String Username, String email, String address, String phoneNumber,String dob,
                           String password,paymentMethod preferredPayment,int dollarsAnHour, int hoursWorked) {

        String sql = "INSERT INTO EMPLOYEE (name , Username , email , address , phoneNumber , DOB , Password , preferredPayment , dollarsAnHour , hoursWorked )" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection c = this.connect();

             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, Username);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, dob);
            pstmt.setString(7, password);
            pstmt.setString(8, preferredPayment.toString());
            pstmt.setInt(9, dollarsAnHour);
            pstmt.setInt(10, hoursWorked);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
