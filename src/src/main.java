import java.sql.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class main extends Application {
    private login login;
    private Scene loginScene;
    public static void main(String args[]) {
        launch(args);
        Connection c = null;

        try {
            Class.forName("user");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    @Override
    public void start(Stage primaryStage) {
        login = new login(username -> {
        });

        loginScene = new Scene(login);
        primaryStage.setScene(loginScene);


        primaryStage.setTitle("Time Clock");
        primaryStage.show();
    }
}

