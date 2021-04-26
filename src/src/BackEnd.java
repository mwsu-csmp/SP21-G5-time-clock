import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BackEnd {

    private static Connection c;

    public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("connected ");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return c;
    }


    public static boolean insertUser(String name, String Username, String email, String address, String phoneNumber, String dob,
                                     String password) throws SQLException {

        PreparedStatement pstmt = null;

        String sql = "INSERT INTO EMPLOYEE ( Name , Username , email , Address , phoneNumber , DOB , password , Payment , dollarsAnHour , hoursWorked ) VALUES (?,?,?,?,?,?,?,'Check',0.30,0)";


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


        } catch (SQLException e) {
            System.out.println(e.toString());

        }
        return true;
    }


    public static boolean isLogin(String Username, String password) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT Username , password FROM EMPLOYEE WHERE Username = ? AND password = ? ";

        try {

            Connection conn = connect();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Username);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;

        } finally {
            try {
                pstmt.close();
                resultSet.close();
                connect().close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }

        }

    }


    public static List<String> userInfo(String Username) {

        List<String> UserInfo = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM EMPLOYEE WHERE Username = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Username);
            resultSet = pstmt.executeQuery();


                UserInfo.add(resultSet.getString("EMP_ID"));
                UserInfo.add(resultSet.getString("Name"));
                UserInfo.add(resultSet.getString("Username"));
                UserInfo.add(resultSet.getString("Email"));
                UserInfo.add(resultSet.getString("Address"));
                UserInfo.add(resultSet.getString("PhoneNumber"));
                UserInfo.add(resultSet.getString("DOB"));
                UserInfo.add(resultSet.getString("password"));
                UserInfo.add(resultSet.getString("Payment"));
                UserInfo.add(resultSet.getString("dollarsAnHour"));
                UserInfo.add(resultSet.getString("HoursWorked"));

                System.out.println(UserInfo);





        } catch (SQLException e) {
            System.out.println(e.toString());
            return UserInfo;


        } finally {
            try {
                pstmt.close();
                resultSet.close();
                connect().close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }

        }

            return UserInfo;


    }

    public static boolean editInfoAdmin(List<String> UserEdit ){

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "UPDATE EMPLOYEE SET NAME = ?, Username = ?, Email = ?, Address = ?, PhoneNumber = ?, DOB = ?, password = ? , Payment = ? , dollarsAnHour = ? , HoursWorked = ? " +
                " WHERE EMP_ID = ?";


        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);


            pstmt.setString(1, UserEdit.get(1));
            pstmt.setString(2, UserEdit.get(2));
            pstmt.setString(3, UserEdit.get(3));
            pstmt.setString(4, UserEdit.get(4));
            pstmt.setString(5, UserEdit.get(5));
            pstmt.setString(6, UserEdit.get(6));
            pstmt.setString(7, UserEdit.get(7));
            pstmt.setString(8, UserEdit.get(8));
            pstmt.setString(9, UserEdit.get(9));
            pstmt.setString(10, UserEdit.get(10));
            pstmt.setString(11, UserEdit.get(0));

            resultSet = pstmt.executeQuery();


            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }




        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;


        } finally {
            try {
                pstmt.close();
                resultSet.close();
                connect().close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }

        }

    }


    public static boolean editInfoUser(List<String> UserEdit ) {

        PreparedStatement pstmt = null;

        String sql = "UPDATE EMPLOYEE SET NAME = ?, Username = ?, Email = ?, Address = ?, PhoneNumber = ?, DOB = ?, password = ? , Payment = ? " +
                " WHERE EMP_ID = ?";


        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);


            pstmt.setString(1, UserEdit.get(1));
            pstmt.setString(2, UserEdit.get(2));
            pstmt.setString(3, UserEdit.get(3));
            pstmt.setString(4, UserEdit.get(4));
            pstmt.setString(5, UserEdit.get(5));
            pstmt.setString(6, UserEdit.get(6));
            pstmt.setString(7, UserEdit.get(7));
            pstmt.setString(8, UserEdit.get(8));
            pstmt.setString(9, UserEdit.get(0));


        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;


        }

        return true;

    }


    public static boolean checkDuplicateUsername(String Username) throws SQLException {


        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        int result;


        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE Username = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Username);
            resultSet = pstmt.executeQuery();
            result = resultSet.getInt(1);


            if (result > 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            pstmt.close();
            resultSet.close();
            connect().close();
        }

        return true;

    }

    public static boolean checkDuplicateEmail(String Username) throws SQLException {


        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        int result;


        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE Email = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Username);
            resultSet = pstmt.executeQuery();
            result = resultSet.getInt(1);


            if (result > 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            pstmt.close();
            resultSet.close();
            connect().close();
        }

        return true;

    }

    public static boolean checkDuplicatePhone(String Username) throws SQLException {


        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        int result;


        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE PhoneNumber = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Username);
            resultSet = pstmt.executeQuery();
            result = resultSet.getInt(1);


            if (result > 0) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            pstmt.close();
            resultSet.close();
            connect().close();
        }

        return true;

    }

    public static boolean Clock_in(String id, String datetime) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO CLOCK (EMP_ID, CLOCK_IN)" +
                " VALUES (?,?)";

        try {
            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, datetime);

            resultSet = pstmt.executeQuery();


            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        } finally {
            pstmt.close();
            resultSet.close();
        }
    }









}
