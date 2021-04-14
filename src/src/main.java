import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class main extends Application {
    private loginPane login;
    private Scene loginScene;
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        login = new loginPane(username -> {
        });

        loginScene = new Scene(login);
        primaryStage.setScene(loginScene);

        primaryStage.setTitle("Time Clock");
        primaryStage.show();
    }
}

