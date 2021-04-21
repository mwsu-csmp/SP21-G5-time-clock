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
        buttons.alignmentProperty().setValue(Pos.BOTTOM_CENTER);

        var backToLogin = new Button("Back to Login");
        var addClocking = new Button("Clock In/Out");
        var myInfo = new Button("My Info");
        var admin = new Button("Admin Button");
        var errorMessage = new Label();

        buttons.getChildren().add(backToLogin);
        buttons.getChildren().add(addClocking);
        buttons.getChildren().add(myInfo);
        buttons.getChildren().add(admin);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);


        backToLogin.setOnAction(event -> {
            goBackToLogin.run();

        });

        addClocking.setOnAction(event -> {


        });

        myInfo.setOnAction(event -> {


        });

        admin.setOnAction(event ->  {


        });
    }
}
