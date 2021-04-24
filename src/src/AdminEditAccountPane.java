import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.function.Consumer;

public class AdminEditAccountPane extends BorderPane {
    TextField username = new TextField();
    TextField email = new TextField();
    TextField address = new TextField();
    TextField phoneNumber = new TextField();
    TextField dob = new TextField();
    TextField preferredPayment = new TextField();
    PasswordField  password = new PasswordField();
    PasswordField repeatPassword = new PasswordField();
    PasswordField currentPassword = new PasswordField();


    public AdminEditAccountPane(Consumer<String> backToInfo) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.TOP_LEFT);
        buttons.alignmentProperty().setValue(Pos.TOP_LEFT);


        var back = new Button("Back");
        var clear = new Button("Clear");
        var editAccountInfo = new Button("Edit Account Information");
        var errorMessage = new Label();


        fields.getChildren().add(new Label("Edit Username", username));
        fields.getChildren().add(new Label("Edit Email", email));
        fields.getChildren().add(new Label("Edit Address", address));
        fields.getChildren().add(new Label("Edit Phone Number", phoneNumber));
        fields.getChildren().add(new Label("Edit Date of Birth", dob));
        fields.getChildren().add(new Label("Edit Preferred Payment Method", preferredPayment));
        fields.getChildren().add(new Label("New Password", password));
        fields.getChildren().add(new Label("Repeat New Password", repeatPassword));
        fields.getChildren().add(new Label("Current Password", currentPassword));

        buttons.getChildren().add(back);
        buttons.getChildren().add(clear);
        buttons.getChildren().add(editAccountInfo);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);

        clear.setOnAction(event -> {
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            password.clear();
            preferredPayment.clear();
            repeatPassword.clear();
            currentPassword.clear();
            errorMessage.setText("");
        });

        back.setOnAction(event -> {
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            preferredPayment.clear();
            repeatPassword.clear();
            password.clear();
            currentPassword.clear();

            backToInfo.accept(username.getText());
            errorMessage.setText("");

        });

        editAccountInfo.setOnAction(event -> {
            if (password.getText().equals("") || currentPassword.getText().equals("")) {
                errorMessage.setText("You must fill out the entire menu to create an account!");
            }
            else {
                backToInfo.accept(username.getText());
                username.clear();
                email.clear();
                address.clear();
                phoneNumber.clear();
                dob.clear();
                password.clear();
                currentPassword.clear();
                errorMessage.setText("");
            }
        });
    }
    public void update() {
        username.setText("Username");
        email.setText("Email");
        address.setText("Address");
        phoneNumber.setText("Phone Number");
        dob.setText("Date of Birth");
        preferredPayment.setText("Preferred Payment Method");


    }
}