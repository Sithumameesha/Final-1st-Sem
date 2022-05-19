package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.Rider;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddRiderController {
    public TextField txtName;
    public TextField txtId;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtContact;
    public Button btnAddRider;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();


    public void initialize() {
        btnAddRider.setDisable(true);


        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern idPattern = Pattern.compile("^(R)[0-9]{2,5}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern emailPattern = Pattern.compile("^[A-z0-9]{3,20}(@gmail.com)$");
        Pattern conPattern = Pattern.compile("^^(07)[0-9]{7,10}$");

        map.put(txtName, namePattern);
        map.put(txtId, idPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtEmail, emailPattern);
        map.put(txtContact, conPattern);
    }

    public void btnAddRiderOnAction(ActionEvent actionEvent) {
        SaveRider();
    }

    private void SaveRider() {
        Rider rider = new Rider(
                txtName.getText(),
                txtId.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtContact.getText()),
                txtEmail.getText()

        );
        try {
            if (CrudUtil.executeUpdate("INSERT INTO RiderDetails VALUES (?,?,?,?,?)", rider.getName(), rider.getId(), rider.getAddress(), rider.getContact(), rider.getEmail())) {
                Clear();
                Not.notificationsConfirm("Sucessfully", "Saved");
            }

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            Not.notificationError("UnSucessfully", "Try again");


        }
    }

    private void Clear() {
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        txtId.clear();
        txtName.clear();
        setBorders(txtAddress, txtId, txtEmail, txtContact, txtName);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnAddRider);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnAddRider);
            ;
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                SaveRider();
            }
        }
    }

    public void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }
}

