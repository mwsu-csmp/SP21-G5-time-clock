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


    public boolean insertUser(int id, String name, String Username, String email, String address, String phoneNumber,String dob,
                           String password,paymentMethod preferredPayment,int dollarsAnHour, int hoursWorked) throws SQLException {

       PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO EMPLOYEE (name , Username , email , address , phoneNumber , DOB , password , preferredPayment , dollarsAnHour , hoursWorked )" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";


        try (Connection c = this.connect()) {

            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, Username);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, dob);
            pstmt.setString(8, password);
            pstmt.setString(9, preferredPayment.toString());
            pstmt.setInt(10, dollarsAnHour);
            pstmt.setInt(11, hoursWorked);

            resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                return true;
            }else
            {
                return false;
            }

        }catch(Exception e)
        {
            return false;

        } finally {
            pstmt.close();
            resultSet.close();
        }
    }


    public boolean TimeClock(int id, String day, String Clock_In, String Clock_Out) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO CLOCK (EMP_ID, DAY, CLOCK_IN, CLOCK_OUT)" +
                " VALUES (?,?,?,?)";

        try (Connection c = this.connect()){

            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            pstmt.setString(3, Clock_In);
            pstmt.setString(4, Clock_Out);;

            pstmt.executeUpdate();

            if(resultSet.next()){
                return true;
            }else
            {
                return false;
            }

        }catch(Exception e)
        {
            return false;

        } finally {
            pstmt.close();
            resultSet.close();
        }
    }


    public boolean isLogin (String Username, String password) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String sql = "SELECT Username , password FROM EMPLOYEE WHERE Username = ? AND password = ? ";

        try (Connection c = this.connect()){

            pstmt = c.prepareStatement(sql);

            pstmt.setString(1,Username);
            pstmt.setString(2,password);

            resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                return true;
            }else
            {
                return false;
            }


        }catch(Exception e)
        {
            return false;

        } finally {
            pstmt.close();
            resultSet.close();
        }
    }










}
