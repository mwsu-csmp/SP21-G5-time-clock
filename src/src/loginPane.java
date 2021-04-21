import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

    public class loginPane extends BorderPane {


        public loginPane(Consumer<String> postLoginAction, Consumer<String> newAccountAction) {
            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
            buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

            var username = new TextField();
            var password = new PasswordField();
            var login = new Button("Login");
            var clear = new Button("Clear");
            var createAccount = new Button("Create an Account");
            var errorMessage = new Label();

            fields.getChildren().add(new Label("Username", username));
            fields.getChildren().add(new Label("Password", password));
            buttons.getChildren().add(login);
            buttons.getChildren().add(clear);
            buttons.getChildren().add(createAccount);
            setTop(fields);
            setCenter(buttons);
            setBottom(errorMessage);

            clear.setOnAction(event -> {
                username.clear();
                password.clear();
            });

            createAccount.setOnAction(event -> {
                newAccountAction.accept(username.getText());


            });

            login.setOnAction(event -> {
                if (main.getUser(username.getText()) != null && main.getUser(username.getText()).getPassword().equals(password.getText()))  {
                    errorMessage.setText("You have logged in!");

                }


            });
        }
    }


