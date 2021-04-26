import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.sql.SQLException;


public class Main extends Application {
    private Scene clockInScene;
    private Scene loginScene;
    private Scene createAccountScene;
    private Scene userInformationScene;
    private Scene editAccountScene;
    private Scene adminSearchScene;
    private Scene adminInformationScene;
    private Scene adminEditAccountScene;


    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        AdminEditAccountPane adminEditAccount = new AdminEditAccountPane(username -> {

            primaryStage.setScene(adminInformationScene);
            primaryStage.setTitle(username+"'s Information");
        });

        AdminInformationPane adminInfo = new AdminInformationPane(username -> {
            adminEditAccount.update();
            primaryStage.setScene(adminEditAccountScene);
            primaryStage.setTitle("Edit "+username+"'s Account Information");
        }, () -> {
            primaryStage.setScene(adminSearchScene);
            primaryStage.setTitle("Search for User");
        });

        AdminSearchPane adminSearch = new AdminSearchPane(username -> {
            adminInfo.setUsername(username);
            adminInfo.update();
            primaryStage.setScene(adminInformationScene);
            primaryStage.setTitle(username+"'s Information");

        }, () -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In");
        });

        EditAccountPane editAccount = new EditAccountPane(username -> {
            primaryStage.setScene(userInformationScene);
            primaryStage.setTitle(username+"'s Information");
        });

        UserInformationPane userInfo = new UserInformationPane(() -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In");
        },username -> {
            editAccount.setUsername(username);
            editAccount.update();
            primaryStage.setScene(editAccountScene);
            primaryStage.setTitle("Edit Account Information");
        });

        ClockPane clockIn = new ClockPane(username -> {
            userInfo.setUsername(username);
            userInfo.update();
            primaryStage.setScene(userInformationScene);
            primaryStage.setTitle(username+"'s Information");
        }, () -> {
            primaryStage.setScene(adminSearchScene);
            primaryStage.setTitle("Search for User");
        }, () -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
        });

        LoginPane login = new LoginPane(username -> {
            clockIn.setUsername(username);
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In");
        }, () -> {
            primaryStage.setScene(createAccountScene);
            primaryStage.setTitle("Create an Account");
        });

        CreateAccountPane createAccount = new CreateAccountPane(() -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
        });

        adminSearchScene = new Scene(adminSearch);
        userInformationScene = new Scene(userInfo);
        clockInScene = new Scene(clockIn);
        loginScene = new Scene(login);
        createAccountScene = new Scene(createAccount);
        editAccountScene = new Scene(editAccount);
        adminInformationScene = new Scene(adminInfo);
        adminEditAccountScene = new Scene(adminEditAccount);


        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void addToDatabase(String name, String username, String email, String address, String phoneNumber, String dob, String password) throws SQLException {
        BackEnd.insertUser(name, username, email, address, phoneNumber, dob, password);
    }
/*
    public static User getUser(String username) {
        for (User name : database) {// GET ALL INFO FROM USER FROM DATABASE
            if (name.getUsername().equals(username)) {
                return name;
            }
        }
        return null;
    }
     */

    public static String checkDuplicateInfo(String username, String email, String phoneNumber) throws SQLException {
        //for ) { //CREATE A SQL TO CHECK IF USERNAME ALREADY EXISTS
            if (BackEnd.checkDuplicateUsername(username)) {
                return "This username is already taken!";
            }
            if (BackEnd.checkDuplicateEmail(email)) {
                return "This email is already linked to an account!";
            }
            if ((BackEnd.checkDuplicatePhone(phoneNumber))) {
                return "This phone number is already linked to an account!";
            }
        return null;
    }
}


