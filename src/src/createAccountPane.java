import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.util.function.Consumer;

public class createAccountPane extends BorderPane {


    public createAccountPane(Consumer<String> backToLogin) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

        var name = new TextField();
        var username = new TextField();
        var email = new TextField();
        var address = new TextField();
        var number = new TextField();
        var dob = new TextField();
        var password = new PasswordField();
        var repeatPassword = new PasswordField();
        var login = new Button("login");
        var clear = new Button("clear");
        var back = new Button("back");
        var errorMessage = new Label();

        fields.getChildren().add(new Label("Name", name));
        fields.getChildren().add(new Label("Username", username));
        fields.getChildren().add(new Label("Email", email));
        fields.getChildren().add(new Label("Address", address));
        fields.getChildren().add(new Label("Phone Number", number));
        fields.getChildren().add(new Label("Date of Birth", dob));
        fields.getChildren().add(new Label("Password", password));
        fields.getChildren().add(new Label("Repeat Password", repeatPassword));

        buttons.getChildren().add(login);
        buttons.getChildren().add(clear);
        buttons.getChildren().add(back);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);

        clear.setOnAction(event -> {

        });
        back.setOnAction(event -> {
            backToLogin.accept("");
        });
    }
}