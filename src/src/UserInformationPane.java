import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInformationPane extends BorderPane {

    private String username = "admin";

    public void setUsername(String username) {
        this.username = username;
    }


    public UserInformationPane(Runnable backToClockIn) {

            VBox fields = new VBox();
            HBox buttons = new HBox();
            fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
            buttons.alignmentProperty().setValue(Pos.TOP_CENTER);

            var name_box = new TextField();
            var username_box = new TextField();
            var email_box = new TextField();
            var address_box = new TextField();
            var phoneNumber_box = new TextField();
            var dob_box = new TextField();
            var password_box = new PasswordField();
            var back = new Button("Back to Clock In");
            var clear = new Button("Clear");
            var createAccount = new Button("Create Account");
            var errorMessage = new Label();

            String name = Main.getUser(username).getName();
            String email = Main.getUser(username).getEmail();
            String address = Main.getUser(username).getAddress();
            String phoneNumber = Main.getUser(username).getPhoneNumber();
            String dob = Main.getUser(username).getDob();
            String password = Main.getUser(username).getPassword();


            fields.getChildren().add(new Label(name, name_box));
            fields.getChildren().add(new Label(username, username_box));
            fields.getChildren().add(new Label(email, email_box));
            fields.getChildren().add(new Label(address, address_box));
            fields.getChildren().add(new Label(phoneNumber, phoneNumber_box));
            fields.getChildren().add(new Label(dob, dob_box));
            fields.getChildren().add(new Label(password, password_box));


            buttons.getChildren().add(back);
            buttons.getChildren().add(clear);
            buttons.getChildren().add(createAccount);
            setTop(fields);
            setCenter(buttons);
            setBottom(errorMessage);

            clear.setOnAction(event -> {
                name_box.clear();
                email_box.clear();
                address_box.clear();
                phoneNumber_box.clear();
                dob_box.clear();
                password_box.clear();

            });

            back.setOnAction(event -> {
                name_box.clear();
                email_box.clear();
                address_box.clear();
                phoneNumber_box.clear();
                dob_box.clear();
                password_box.clear();

                backToClockIn.run();
                errorMessage.setText("");

            });

            createAccount.setOnAction(event -> {
                errorMessage.setText(username);


            });

    }
}