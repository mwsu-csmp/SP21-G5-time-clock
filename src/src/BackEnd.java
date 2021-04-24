import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class BackEnd {

    private static Connection c;

   public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return c;
    }


    public static boolean insertUser(String name, String Username, String email, String address, String phoneNumber,String dob,
                           String password) throws SQLException {

        PreparedStatement pstmt = null;

        String sql = "INSERT INTO EMPLOYEE ( Name , Username , email , address , phoneNumber , DOB , password , Payment , dollarsAnHour , hoursWorked ) VALUES (?,?,?,?,?,?,?,NULL,NULL,NULL)";


        try {

            Connection conn = connect();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, Username);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, dob);
            pstmt.setString(7, password);
            pstmt.executeUpdate();


        }catch(SQLException e)
        {
            System.out.println(e.toString());

        }
        return true;
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


    public static boolean isLogin (String Username, String password) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT Username , password FROM EMPLOYEE WHERE Username = ? AND password = ? ";

        try {

            Connection conn = connect();

            pstmt = conn.prepareStatement(sql);

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
