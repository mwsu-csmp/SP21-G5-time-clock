
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AdminSearchPane extends BorderPane {
    private String admin;



    public AdminSearchPane(Consumer<String> searchUser, Runnable backToClockIn) {
        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.TOP_LEFT);
        buttons.alignmentProperty().setValue(Pos.TOP_LEFT);

        var username = new TextField();
        var back = new Button("Back");
        var search = new Button("Search");
        var errorMessage = new Label();

        fields.getChildren().add(new Label("Search by Username", username));



        buttons.getChildren().add(back);

        buttons.getChildren().add(search);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);



        back.setOnAction(event -> {
            username.clear();
            backToClockIn.run();
            errorMessage.setText("");

        });

        search.setOnAction(event -> {
            searchUser.accept(username.getText());



        });
    }
}
