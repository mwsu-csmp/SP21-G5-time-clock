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

    public class LoginPane extends BorderPane {


        public LoginPane(Consumer<String> postLoginAction, Runnable newAccountAction) {
            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_LEFT);
            buttons.alignmentProperty().setValue(Pos.TOP_LEFT);

            var username = new TextField();
            var password = new PasswordField();
            var createAccount = new Button("Create an Account");
            var clear = new Button("Clear");
            var login = new Button("Login");
            var errorMessage = new Label();

            fields.getChildren().add(new Label("Username", username));
            fields.getChildren().add(new Label("Password", password));
            buttons.getChildren().add(createAccount);
            buttons.getChildren().add(clear);
            buttons.getChildren().add(login);
            setTop(fields);
            setCenter(buttons);
            setBottom(errorMessage);


            clear.setOnAction(event -> {
                username.clear();
                password.clear();
                errorMessage.setText("");
            });

            createAccount.setOnAction(event -> {
                        newAccountAction.run();
                        errorMessage.setText("");
                        username.clear();
                        password.clear();
            });

            login.setOnAction(event -> {
                try {
                    if (BackEnd.isLogin(username.getText(), password.getText())) {
                        errorMessage.setText("");
                        postLoginAction.accept(BackEnd.getID(username.getText()));
                        username.clear();
                        password.clear();
                    }
                    else {
                        errorMessage.setText("The username or password is incorrect!");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
        }
    }


