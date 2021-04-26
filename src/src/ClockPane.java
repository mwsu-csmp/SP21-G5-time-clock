import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Consumer;


public class ClockPane extends BorderPane {

    private String ID;

    public void setID(String username) {
        this.ID = username;
    }


    public ClockPane(Consumer<String> goToMyInfo, Runnable goToAdminSearch, Runnable goBackToLogin) {
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
            errorMessage.setText("");
            goBackToLogin.run();
        });

        clockIn.setOnAction(event -> {
            List<String> userinfo =  BackEnd.userInfo(ID);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            try {
                BackEnd.Clock_in(userinfo.get(0), now.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            errorMessage.setText("");
        });

        clockOut.setOnAction(event -> {
            errorMessage.setText("");
        });

        myInfo.setOnAction(event -> {
            goToMyInfo.accept(ID);
            errorMessage.setText("");
        });

        admin.setOnAction(event ->  {
            if (ID.equals("admin")) { //change this
                goToAdminSearch.run();
                errorMessage.setText("");
            }
            else {
                errorMessage.setText("You must be an admin to click this button!");
            }


        });
    }
}
