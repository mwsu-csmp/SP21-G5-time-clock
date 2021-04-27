import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Consumer;


public class ClockInPane extends BorderPane {

    private String ID;

    public void setID(String username) {
        this.ID = username;
    }


    public ClockInPane(Consumer<String> goToMyInfo, Runnable goToAdminSearch, Runnable goBackToLogin) {
        VBox picture = new VBox();
        HBox buttons = new HBox();
        picture.alignmentProperty().setValue(Pos.TOP_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);



        var image = new Image("clock.jpg");
        var backToLogin = new Button("Back to Login");
        var clockIn = new Button("Clock In");
        var clockOut = new Button("Clock Out");
        var myInfo = new Button("My Info");
        var admin = new Button("Admin");
        var errorMessage = new Label();
        var pic = new ImageView();

        pic.setFitWidth(225);
        pic.setFitHeight(225);
        pic.setImage(image);

        picture.getChildren().add(pic);
        buttons.getChildren().add(backToLogin);
        buttons.getChildren().add(clockIn);
        buttons.getChildren().add(clockOut);
        buttons.getChildren().add(myInfo);
        buttons.getChildren().add(admin);
        setTop(picture);
        setCenter(buttons);
        setBottom(errorMessage);


        backToLogin.setOnAction(event -> {
            errorMessage.setText("");
            goBackToLogin.run();
        });

        clockIn.setOnAction(event -> {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/DD/YYYY hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();


            try {
                if(BackEnd.checkClockStatus(ID)) {
                    BackEnd.Clock_in(ID, now.toString());
                }else {
                    System.out.println("Already clocked in");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            errorMessage.setText("");
        });

        clockOut.setOnAction(event -> {
            List<String> userinfo =  BackEnd.userInfo(ID);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/DD/YYYY hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            try {
                BackEnd.HoursWorked(BackEnd.getClockIn(ID),ID);
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }

            try {
                BackEnd.Clock_Out(userinfo.get(0), now.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



            errorMessage.setText("");
        });

        myInfo.setOnAction(event -> {
            goToMyInfo.accept(ID);
            errorMessage.setText("");
        });

        admin.setOnAction(event ->  {
            if (ID.equals("1")) {
                goToAdminSearch.run();
                errorMessage.setText("");
            }
            else {
                errorMessage.setText("You must be an admin to click this button!");
            }


        });
    }
}
