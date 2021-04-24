import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    private static final ArrayList<User> database = new ArrayList<User>();
    private Scene clockInScene;
    private Scene loginScene;
    private Scene createAccountScene;
    private Scene userInformationScene;


    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        database.add(new User("admin", "admin", "admin@admin.com", "123 Admin Street", "123-456-7890", "1-1-2000", "password"));

        UserInformationPane userInfo = new UserInformationPane(() -> {
            primaryStage.setScene(clockInScene);
            primaryStage.setTitle("Clock In");
        });

        ClockPane clockIn = new ClockPane(username -> {
            userInfo.setUsername(username);
            primaryStage.setScene(userInformationScene);
            primaryStage.setTitle(username+"'s Information");
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

        CreateAccountPane createAccount = new CreateAccountPane(username -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
        });


        userInformationScene = new Scene(userInfo);
        clockInScene = new Scene(clockIn);
        loginScene = new Scene(login);
        createAccountScene = new Scene(createAccount);


        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void addToDatabase(String name, String username, String email, String address, String phoneNumber, String dob, String password) throws SQLException {
        database.add(new User(name, username, email, address, phoneNumber, dob, password));
    }

    public static User getUser(String username) {
        for (User name : database) {
            if (name.getUsername().equals(username)) {
                return name;
            }
        }
        return null;
    }

    public static String checkDuplicateInfo(String username, String email, String phoneNumber) {
        for (User name : database) {
            if (name.getUsername().equals(username)) {
                return "This username is already taken!";
            }
            if (name.getEmail().equals(email)) {
                return "This email is already linked to an account!";
            }
            if (name.getPhoneNumber().equals(phoneNumber)) {
                return "This phone number is already linked to an account!";
            }
        }
        return null;
    }
}

