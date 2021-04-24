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

public class CreateAccountPane extends BorderPane {



    public CreateAccountPane(Consumer<String> backToLogin) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

        var name = new TextField();
        var username = new TextField();
        var email = new TextField();
        var address = new TextField();
        var phoneNumber = new TextField();
        var dob = new TextField();
        var password = new PasswordField();
        var repeatPassword = new PasswordField();
        var back = new Button("Back to Login");
        var clear = new Button("Clear");
        var createAccount = new Button("Create Account");
        var errorMessage = new Label();

        fields.getChildren().add(new Label("Name", name));
        fields.getChildren().add(new Label("Username", username));
        fields.getChildren().add(new Label("Email", email));
        fields.getChildren().add(new Label("Address", address));
        fields.getChildren().add(new Label("Phone Number", phoneNumber));
        fields.getChildren().add(new Label("Date of Birth", dob));
        fields.getChildren().add(new Label("Password", password));
        fields.getChildren().add(new Label("Repeat Password", repeatPassword));

        buttons.getChildren().add(back);
        buttons.getChildren().add(clear);
        buttons.getChildren().add(createAccount);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);

        clear.setOnAction(event -> {
            name.clear();
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            password.clear();
            repeatPassword.clear();
            errorMessage.setText("");

        });

        back.setOnAction(event -> {
            name.clear();
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            password.clear();
            repeatPassword.clear();

            backToLogin.accept("");
            errorMessage.setText("");

        });

        createAccount.setOnAction(event -> {
            if (name.getText().equals("") || username.getText().equals("") || email.getText().equals("") || address.getText().equals("") || phoneNumber.getText().equals("") || dob.getText().equals("") || password.getText().equals("") || repeatPassword.getText().equals("")) {
                errorMessage.setText("You must fill out the entire menu to create an account!");
            }
            else {
                try {
                    if (Main.checkDuplicateInfo(username.getText(), email.getText(), phoneNumber.getText()) == null) {
                        if (password.getText().equals(repeatPassword.getText())) {
                            try {
                                Main.addToDatabase(name.getText(), username.getText(), email.getText(), address.getText(), phoneNumber.getText(), dob.getText(), password.getText());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            name.clear();
                            username.clear();
                            email.clear();
                            address.clear();
                            phoneNumber.clear();
                            dob.clear();
                            password.clear();
                            repeatPassword.clear();
                            errorMessage.setText("");

                            backToLogin.accept("");


                        } else {
                            errorMessage.setText("The passwords do not match!");
                            password.clear();
                            repeatPassword.clear();
                        }
                    } else {
                        errorMessage.setText(Main.checkDuplicateInfo(username.getText(), email.getText(), phoneNumber.getText()));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}