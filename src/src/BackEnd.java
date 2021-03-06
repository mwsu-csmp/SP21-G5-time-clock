import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BackEnd {

    private static Connection c;

    public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return c;
    }


    public static boolean insertUser(String name, String Username, String email, String address, String phoneNumber, String dob, String password) throws SQLException {

        PreparedStatement pstmt = null;

        String sql = "INSERT INTO EMPLOYEE ( Name , Username , email , Address , phoneNumber , DOB , password , Payment , dollarsAnHour , hoursWorked ) VALUES (?,?,?,?,?,?,?,'Check',0.00,0.00)";


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
    
    public static String getID(String Username) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String id = null;

        String sql = "SELECT EMP_ID FROM EMPLOYEE WHERE Username = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Username);
            
            resultSet = pstmt.executeQuery();
            
            id = resultSet.getString("EMP_ID");
            

            
        } catch (SQLException e) {
            System.out.println(e.toString());


        } finally {
            try {
                pstmt.close();
                resultSet.close();
                connect().close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }

        }

        return id;
    }

    public static List<String> userInfo(String id) {

        List<String> UserInfo = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
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

            //System.out.println(UserInfo);





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

        String sql = "UPDATE EMPLOYEE SET NAME = ?, Username = ?, Email = ?, Address = ?, PhoneNumber = ?, DOB = ?, password = ? , Payment = ? , dollarsAnHour = ? " +
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
            pstmt.setString(10, UserEdit.get(0));

            pstmt.execute();


        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;

        }

        return true;





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

            pstmt.execute();


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

           pstmt.execute();



        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;

        }

        return true;
    }

    public static boolean Clock_Out(String id, String datetime) throws SQLException {

        PreparedStatement pstmt = null;

        String sql = "UPDATE CLOCK SET CLOCK_OUT =?, STATUS = 'F' WHERE EMP_ID = ? AND STATUS = 'T' ";

        try {
            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, datetime);
            pstmt.setString(2, id);

            pstmt.execute();



        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;

        }

        return true;
    }

    public static boolean checkClockStatus(String id) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int result;

        String sql = "SELECT COUNT(STATUS) FROM CLOCK WHERE EMP_ID = ? AND STATUS = 'T'";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            resultSet = pstmt.executeQuery();

            result = resultSet.getInt(1);


            if (result == 0) {
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

    public static String getClockIn(String id) throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String result = null;

        String sql = "SELECT STRFTIME('%m/%d/%Y %H:%M:%S',CLOCK_IN) AS CLOCK_IN FROM CLOCK WHERE EMP_ID = ? AND STATUS = 'T'";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();

            result = resultSet.getString("CLOCK_IN");


            return result;






        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            pstmt.close();
            resultSet.close();
            connect().close();
        }
        return result;


    }

    public static boolean updateHoursWorked(String Hours,String id) throws SQLException{

        PreparedStatement pstmt = null;

        String sql = "UPDATE EMPLOYEE SET HoursWorked = ? " +
                " WHERE EMP_ID = ?";




        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);


            pstmt.setString(1,Hours);
            pstmt.setString(2,id);


            pstmt.execute();


        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;

        }

        return true;

    }

    public static String getHoursWorked(String id) throws SQLException
    {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String result = null;

        String sql = "SELECT HoursWorked FROM EMPLOYEE WHERE EMP_ID = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();

            result = resultSet.getString("HoursWorked");


            return result;






        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            pstmt.close();
            resultSet.close();
            connect().close();
        }
        return result;


    }

    public static void HoursWorked(String datetime, String id) throws ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String Snow = now.format(dtf);


        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        try {

            Date dateStart = format.parse(datetime);
            Date dateEnd = format.parse(Snow);

            long diff = dateEnd.getTime() - dateStart.getTime();


            double diffHours = ((diff / (60.00 * 1000.00) % 60.00)/60);

            DecimalFormat df =new DecimalFormat("###.##");

            double OriginalHours = Double.parseDouble(getHoursWorked(id));

            String NewHours = String.valueOf(OriginalHours + Double.parseDouble(df.format(diffHours)));



            updateHoursWorked(NewHours,id);






        }catch (Exception e)
        {
            System.out.println(e.toString());
        }


    }

    public static List<String> UserClockInfo(String id) {

        List<String> UserInfoClock = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM CLOCK WHERE EMP_ID = ?";

        try {

            Connection conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {

                UserInfoClock.add(resultSet.getString("ID"));
                UserInfoClock.add(resultSet.getString("EMP_ID"));
                UserInfoClock.add(resultSet.getString("CLOCK_IN"));
                UserInfoClock.add(resultSet.getString("CLOCK_OUT"));
                UserInfoClock.add(resultSet.getString("STATUS"));
            }

            //System.out.println(UserInfoClock);





        } catch (SQLException e) {
            System.out.println(e.toString());
            return UserInfoClock;


        } finally {
            try {
                pstmt.close();
                resultSet.close();
                connect().close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }

        }

        return UserInfoClock;


    }






}
