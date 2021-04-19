import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class main extends Application {
    private loginPane login;
    private Scene loginScene;
    private Scene createAccountScene;
    private createAccountPane createAccount;
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        login = new loginPane(username -> {
            //set scene to clock pane
            },username -> {
            primaryStage.setScene(createAccountScene);
            primaryStage.setTitle("Create an Account");
        });

        createAccount = new createAccountPane(username -> {
            primaryStage.setScene(loginScene);
        });

        loginScene = new Scene(login);
        createAccountScene = new Scene(createAccount);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}

