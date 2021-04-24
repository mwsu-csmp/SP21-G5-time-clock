import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;


public class ClockPane extends BorderPane {

    private String username = "";

    public void setUsername(String username) {
        this.username = username;
    }


    public ClockPane(Consumer<String> goToMyInfo, Consumer<String> goToAdminSearch, Runnable goBackToLogin) {
        HBox buttons = new HBox();
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

        var backToLogin = new Button("Back to Login");
        var clockIn = new Button("Clock In");
        var clockOut = new Button("Clock Out");
        var myInfo = new Button("My Info");
        var admin = new Button("Admin Button");
        var errorMessage = new Label();

        buttons.getChildren().add(backToLogin);
        buttons.getChildren().add(clockIn);
        buttons.getChildren().add(clockOut);
        buttons.getChildren().add(myInfo);
        buttons.getChildren().add(admin);
        setTop(buttons);
        setBottom(errorMessage);


        backToLogin.setOnAction(event -> {
            goBackToLogin.run();

        });

        clockIn.setOnAction(event -> {
        });

        clockOut.setOnAction(event -> {
        });

        myInfo.setOnAction(event -> goToMyInfo.accept(username));

        admin.setOnAction(event ->  {
            goToAdminSearch.accept(username);


        });
    }
}
