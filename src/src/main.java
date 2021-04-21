import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

public class main extends Application {
    private static final ArrayList<user> database = new ArrayList<user>();
    private loginPane login;
    private Scene loginScene;
    private Scene createAccountScene;
    private createAccountPane createAccount;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        database.add(new user("admin", "admin", "admin@admin.com", "123 Admin Street", "123-456-7890", "1-1-2000", "password"));

        login = new loginPane(username -> {
            //set scene to clock pane
        }, username -> {
            primaryStage.setScene(createAccountScene);
            primaryStage.setTitle("Create an Account");
        });

        createAccount = new createAccountPane(username -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
        });

        loginScene = new Scene(login);
        createAccountScene = new Scene(createAccount);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void addToDatabase(String name, String username, String email, String address, String phoneNumber, String dob, String password) {
        database.add(new user(name, username, email, address, phoneNumber, dob, password));
    }

    public static user getUser(String username) {
        for (user name : database) {
            if (name.getUsername().equals(username)) {
                return name;
            }
        }
        return null;
    }

    public static String checkDuplicateInfo(String username, String email, String phoneNumber) {
        for (user name : database) {
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


