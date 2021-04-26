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

        AdminEditAccountPane adminEditAccount = new AdminEditAccountPane(ID -> {

            primaryStage.setScene(adminInformationScene);
            primaryStage.setTitle(BackEnd.userInfo(ID).get(2)+"'s Information");
        });

        AdminInformationPane adminInfo = new AdminInformationPane(ID -> {
            adminEditAccount.setUsername(ID);
            adminEditAccount.update();
            primaryStage.setScene(adminEditAccountScene);
            primaryStage.setTitle("Edit "+BackEnd.userInfo(ID).get(2)+"'s Account Information");
        }, () -> {
            primaryStage.setScene(adminSearchScene);
            primaryStage.setTitle("Search for User");
        });

        AdminSearchPane adminSearch = new AdminSearchPane(ID -> {
            adminInfo.setUsername(ID);
            adminInfo.update();
            primaryStage.setScene(adminInformationScene);
            primaryStage.setTitle(BackEnd.userInfo(ID).get(2)+"'s Information");

        }, () -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In/Out Application");
        });

        EditAccountPane editAccount = new EditAccountPane(() -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In/Out Application");
        },ID -> {
            primaryStage.setScene(userInformationScene);
            primaryStage.setTitle(BackEnd.userInfo(ID).get(2)+"'s Information");
        });

        UserInformationPane userInfo = new UserInformationPane(() -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In/Out Application");
        },ID -> {
            editAccount.setID(ID);
            editAccount.update();
            primaryStage.setScene(editAccountScene);
            primaryStage.setTitle("Edit Account Information");
        });

        ClockPane clockIn = new ClockPane(ID -> {
            userInfo.setID(ID);
            userInfo.update();
            primaryStage.setScene(userInformationScene);
            primaryStage.setTitle(BackEnd.userInfo(ID).get(2));
        }, () -> {
            primaryStage.setScene(adminSearchScene);
            primaryStage.setTitle("Search for User");
        }, () -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
        });

        LoginPane login = new LoginPane(id -> {
            clockIn.setID(id);
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In/Out Application");
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


