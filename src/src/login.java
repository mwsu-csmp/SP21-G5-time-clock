import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

    public class login extends BorderPane {
        private Consumer<String> postLoginAction = null;


        public login(Consumer<String> postLoginAction) {
            this.postLoginAction = postLoginAction;
            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
            buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

            final var username = new TextField();
            final var password = new PasswordField();
            final var login = new Button("login");
            final var clear = new Button("clear");

            fields.getChildren().add(new Label("Username", username));
            fields.getChildren().add(new Label("Password", password));
            buttons.getChildren().add(login);
            buttons.getChildren().add(clear);
            setTop(fields);
            setCenter(buttons);
            Label errorMessage = new Label();
            setBottom(errorMessage);
        }
    }


