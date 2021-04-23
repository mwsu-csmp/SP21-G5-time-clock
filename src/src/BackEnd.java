import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class BackEnd {
    private static Connection c;

   public static Connection connect() {

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


    public static boolean insertUser(String name, String Username, String email, String address, String phoneNumber,String dob,
                           String password) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO EMPLOYEE ( Name , Username , email , address , phoneNumber , DOB , password , preferredPayment , dollarsAnHour , hoursWorked )" +
                " VALUES (?,?,?,?,?,?,?,NULL,NULL,NULL)";


        try {

            pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, Username);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, dob);
            pstmt.setString(7, password);

            pstmt.executeUpdate();

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

        try{

            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            pstmt.setString(3, Clock_In);
            pstmt.setString(4, Clock_Out);;
            pstmt.executeUpdate();

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


    public boolean isLogin (String Username, String password) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String sql = "SELECT Username , password FROM EMPLOYEE WHERE Username = ? AND password = ? ";

        try {

            pstmt = c.prepareStatement(sql);

            pstmt.setString(1,Username);
            pstmt.setString(2,password);
            pstmt.executeUpdate();

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
