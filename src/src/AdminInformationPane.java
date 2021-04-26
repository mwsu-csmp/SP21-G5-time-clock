import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class AdminInformationPane extends BorderPane {

    private String ID;

    Label nameLabel;
    Label usernameLabel;
    Label emailLabel;
    Label addressLabel;
    Label phoneNumberLabel;
    Label dobLabel;
    Label preferredPaymentLabel;
    Label dollarsAnHourLabel;
    Label hoursWorkedLabel;
    Label dollarsEarnedLabel;


    public void setID(String ID) {
        this.ID = ID;
    }


    public AdminInformationPane(Consumer<String> editUser, Runnable backToAdminSearch) {

        VBox fields = new VBox();
        HBox buttons = new HBox();
        fields.alignmentProperty().setValue(Pos.BOTTOM_CENTER);
        buttons.alignmentProperty().setValue(Pos.TOP_CENTER);


        var back = new Button("Back");
        var edit = new Button("Edit");
        var errorMessage = new Label();


        nameLabel = new Label();
        usernameLabel = new Label();
        emailLabel = new Label();
        addressLabel = new Label();
        phoneNumberLabel = new Label();
        dobLabel = new Label();
        preferredPaymentLabel = new Label();
        dollarsAnHourLabel = new Label();
        hoursWorkedLabel = new Label();
        dollarsEarnedLabel = new Label();

        fields.getChildren().add(nameLabel);
        fields.getChildren().add(usernameLabel);
        fields.getChildren().add(emailLabel);
        fields.getChildren().add(addressLabel);
        fields.getChildren().add(phoneNumberLabel);
        fields.getChildren().add(dobLabel);
        fields.getChildren().add(preferredPaymentLabel);
        fields.getChildren().add(dollarsAnHourLabel);
        fields.getChildren().add(hoursWorkedLabel);
        fields.getChildren().add(dollarsEarnedLabel);

        buttons.getChildren().add(back);
        buttons.getChildren().add(edit);
        setTop(fields);
        setCenter(buttons);
        setBottom(errorMessage);


        back.setOnAction(event -> {
            backToAdminSearch.run();


        });

        edit.setOnAction(event -> {
            editUser.accept(ID);


        });

    }

    public void update() {
        nameLabel.setText("Name: "+BackEnd.userInfo(ID).get(1));
        usernameLabel.setText("Username: "+BackEnd.userInfo(ID).get(2));
        emailLabel.setText("Email: "+BackEnd.userInfo(ID).get(3));
        addressLabel.setText("Address: "+BackEnd.userInfo(ID).get(4));
        phoneNumberLabel.setText("Phone Number: "+BackEnd.userInfo(ID).get(5));
        dobLabel.setText("Date of Birth: "+BackEnd.userInfo(ID).get(6));
        preferredPaymentLabel.setText("Preferred Payment Method: "+BackEnd.userInfo(ID).get(8));
        dollarsAnHourLabel.setText("Dollars Per Hour: $"+BackEnd.userInfo(ID).get(9));
        hoursWorkedLabel.setText("Hours Worked: "+BackEnd.userInfo(ID).get(10));
        dollarsEarnedLabel.setText("Amount Earned: $"+(Double.parseDouble(BackEnd.userInfo(ID).get(9))*Double.parseDouble(BackEnd.userInfo(ID).get(10))));
    }
}