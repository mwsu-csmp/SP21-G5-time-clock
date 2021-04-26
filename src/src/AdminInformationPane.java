import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AdminInformationPane extends BorderPane {

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


    public AdminInformationPane(Consumer<String> editUser, Runnable backToAdminSearch) {

        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);


        var back = new Button("Back");
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
            backToAdminSearch.run();


        });

        edit.setOnAction(event -> {
            editUser.accept(username);


        });

    }

    public void update() {
        nameLabel.setText("Name: "+BackEnd.userInfo(username).get(1));
        usernameLabel.setText("Username: "+BackEnd.userInfo(username).get(2));
        emailLabel.setText("Email: "+BackEnd.userInfo(username).get(3));
        addressLabel.setText("Address: "+BackEnd.userInfo(username).get(4));
        phoneNumberLabel.setText("Phone Number: "+BackEnd.userInfo(username).get(5));
        dobLabel.setText("Date of Birth: "+BackEnd.userInfo(username).get(6));
        preferredPaymentLabel.setText("Preferred Payment Method: "+BackEnd.userInfo(username).get(8));
        dollarsAnHourLabel.setText("Dollars Per Hour: $"+BackEnd.userInfo(username).get(9));
        hoursWorkedLabel.setText("Hours Worked: "+BackEnd.userInfo(username).get(10));
        dollarsEarnedLabel.setText("Amount Earned: $"+(Integer.parseInt(BackEnd.userInfo(username).get(9))*Integer.parseInt(BackEnd.userInfo(username).get(10))));
    }
}