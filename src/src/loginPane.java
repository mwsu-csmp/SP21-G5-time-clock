import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

    public class loginPane extends BorderPane {
        private Consumer<String> postLoginAction = null;


        public loginPane(Consumer<String> postLoginAction) {
            this.postLoginAction = postLoginAction;
            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
            buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

            var username = new TextField();
            var password = new PasswordField();
            var login = new Button("login");
            var clear = new Button("clear");
            var newUser = new Button("new user");
            var errorMessage = new Label();

            fields.getChildren().add(new Label("Username", username));
            fields.getChildren().add(new Label("Password", password));
            buttons.getChildren().add(login);
            buttons.getChildren().add(clear);
            buttons.getChildren().add(newUser);
            setTop(fields);
            setCenter(buttons);
            setBottom(errorMessage);

            clear.setOnAction(event -> {
                username.clear();
                password.clear();
            });

            newUser.setOnAction(event -> {


            });
        }
    }


