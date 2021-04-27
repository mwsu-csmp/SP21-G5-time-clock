import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class EditAccountPane extends BorderPane {
    TextField name = new TextField();
    TextField username = new TextField();
    TextField email = new TextField();
    TextField address = new TextField();
    TextField phoneNumber = new TextField();
    TextField dob = new TextField();
    TextField preferredPayment = new TextField();
    PasswordField  password = new PasswordField();
    PasswordField repeatPassword = new PasswordField();
    PasswordField currentPassword = new PasswordField();

    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }


    public EditAccountPane(Runnable backToClockIn,Consumer<String> backToInfo) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.TOP_LEFT);
        buttons.alignmentProperty().setValue(Pos.TOP_LEFT);

        var back = new Button("Back");
        var clear = new Button("Clear");
        var editAccountInfo = new Button("Edit Account Information");
        var errorMessage = new Label();

        fields.getChildren().add(new Label("Edit Name", name));
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
            name.clear();
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            preferredPayment.clear();
            password.clear();
            repeatPassword.clear();
            currentPassword.clear();
            errorMessage.setText("");
        });

        back.setOnAction(event -> {
            name.clear();
            username.clear();
            email.clear();
            address.clear();
            phoneNumber.clear();
            dob.clear();
            preferredPayment.clear();
            repeatPassword.clear();
            password.clear();
            currentPassword.clear();

            backToInfo.accept(ID);
            errorMessage.setText("");

        });

        editAccountInfo.setOnAction(event -> {
            if (name.getText().equals("") || username.getText().equals("") || email.getText().equals("") || address.getText().equals("") || phoneNumber.getText().equals("") || dob.getText().equals("") || preferredPayment.getText().equals("")) {
                errorMessage.setText("You must fill out all non-password fields to edit your account!");
            }
            else {
                if (password.getText().equals(repeatPassword.getText())) {
                    if (currentPassword.getText().equals(BackEnd.userInfo(ID).get(7))) {


                        ArrayList<String> newInfo = new ArrayList<>();
                        newInfo.add(BackEnd.userInfo(ID).get(0));
                        newInfo.add(name.getText());
                        newInfo.add(username.getText());
                        newInfo.add(email.getText());
                        newInfo.add(address.getText());
                        newInfo.add(phoneNumber.getText());
                        newInfo.add(dob.getText());
                        if (password.getText().equals("")) {
                            newInfo.add(BackEnd.userInfo(ID).get(7));
                        } else {
                            newInfo.add(password.getText());
                        }
                        newInfo.add(preferredPayment.getText());

                        BackEnd.editInfoUser(newInfo);

                        backToClockIn.run();

                        errorMessage.setText("");
                        name.clear();
                        username.clear();
                        email.clear();
                        address.clear();
                        phoneNumber.clear();
                        dob.clear();
                        preferredPayment.clear();
                        password.clear();
                        repeatPassword.clear();
                        currentPassword.clear();
                        errorMessage.setText("");
                    }
                    else { errorMessage.setText("Your current password is incorrect!");

                    }
                } else {
                    errorMessage.setText("Your new passwords must match in order to edit your account!");

                }
            }
        });
        }
    public void update() {
        name.setText(BackEnd.userInfo(ID).get(1));
        username.setText(BackEnd.userInfo(ID).get(2));
        email.setText(BackEnd.userInfo(ID).get(3));
        address.setText(BackEnd.userInfo(ID).get(4));
        phoneNumber.setText(BackEnd.userInfo(ID).get(5));
        dob.setText(BackEnd.userInfo(ID).get(6));
        preferredPayment.setText(BackEnd.userInfo(ID).get(8));



    }
}