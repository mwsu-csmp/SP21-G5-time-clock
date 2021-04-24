import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.function.Consumer;

public class UserInformationPane extends BorderPane {

    private String username;

    Label nameLabel;
    Label usernameLabel;
    Label emailLabel;
    Label addressLabel;
    Label phoneNumberLabel;
    Label dobLabel;
    Label preferredPaymentLabel;
    Label dollarsAnHourLabel;
    Label hoursWorkedLabel;
    Label dollarsEarnedLabel;


    public void setUsername(String username) {
        this.username = username;
    }


    public UserInformationPane(Runnable backToClockIn, Consumer<String> goToEdit) {

            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
            buttons.alignmentProperty().setValue(Pos.TOP_CENTER);


            var back = new Button("Back to Clock In");
            var edit = new Button("Edit");
            var errorMessage = new Label();


        nameLabel = new Label();
        usernameLabel = new Label();
        emailLabel = new Label();
        addressLabel = new Label();
        phoneNumberLabel = new Label();
        dobLabel = new Label();
        preferredPaymentLabel = new Label();
        dollarsAnHourLabel = new Label();
        hoursWorkedLabel = new Label();
        dollarsEarnedLabel = new Label();

            fields.getChildren().add(nameLabel);
            fields.getChildren().add(usernameLabel);
            fields.getChildren().add(emailLabel);
            fields.getChildren().add(addressLabel);
            fields.getChildren().add(phoneNumberLabel);
            fields.getChildren().add(dobLabel);
            fields.getChildren().add(preferredPaymentLabel);
            fields.getChildren().add(dollarsAnHourLabel);
            fields.getChildren().add(hoursWorkedLabel);
            fields.getChildren().add(dollarsEarnedLabel);

            buttons.getChildren().add(back);
            buttons.getChildren().add(edit);
            setTop(fields);
            setCenter(buttons);
            setBottom(errorMessage);


            back.setOnAction(event -> {
                backToClockIn.run();


            });

            edit.setOnAction(event -> {
                goToEdit.accept(username);



            });

    }

    public void update() {

        System.out.println(BackEnd.userInfo(username));

        /*
        nameLabel.setText("Name: "+Main.getUser(username).getName());
        usernameLabel.setText("Username: "+Main.getUser(username).getUsername());
        emailLabel.setText("Email: "+Main.getUser(username).getEmail());
        addressLabel.setText("Address: "+Main.getUser(username).getAddress());
        phoneNumberLabel.setText("Phone Number: "+Main.getUser(username).getPhoneNumber());
        dobLabel.setText("Date of Birth: "+Main.getUser(username).getDob());
        preferredPaymentLabel.setText("Preferred Payment Method: "+Main.getUser(username).getPreferredPayment().toString());
        dollarsAnHourLabel.setText("Dollars Per Hour: $"+Main.getUser(username).getDollarsAnHour());
        hoursWorkedLabel.setText("Total Hours Worked: "+Main.getUser(username).getHoursWorked());
        dollarsEarnedLabel.setText("Dollars Earned: $"+Main.getUser(username).getDollarsEarned());
        */
    }
}