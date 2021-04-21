import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class ClockPane extends BorderPane {


    public ClockPane(Runnable goBackToLogin) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

        var change1 = new TextField();
        var change2 = new PasswordField();
        var createAccount = new Button("Change");
        var clear = new Button("Change");
        var login = new Button("Change");
        var errorMessage = new Label();

        fields.getChildren().add(new Label("Change", change1));
        fields.getChildren().add(new Label("Change", change2));
        buttons.getChildren().add(createAccount);
        buttons.getChildren().add(clear);
        buttons.getChildren().add(login);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);


        clear.setOnAction(event -> {
            change1.clear();
            change2.clear();
        });

        createAccount.setOnAction(event -> {
            goBackToLogin.run();

        });

        login.setOnAction(event -> {

        });
    }
}
