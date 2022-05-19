package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.User;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class   AddUserController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtPassword;
    public Button BtnAdd;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();


    public void initialize() {
        BtnAdd.setDisable(true);

        Pattern idPattern = Pattern.compile("^(U)[0-9]{2,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern emailPattern = Pattern.compile("^[A-z0-9]{3,20}(@gmail.com)$");
        Pattern passwordPattern = Pattern.compile("^[A-z0-9]{8,10}$");

        map.put(txtId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtEmail, emailPattern);
        map.put(txtPassword, passwordPattern);


    }


    public void AddUserOnAction(ActionEvent actionEvent) {
        SaveUser();
        Clear();

    }

    private void SaveUser() {
        User user = new User(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtPassword.getText()

        );
        try {
            if (CrudUtil.executeUpdate("INSERT INTO User VALUES (?,?,?,?,?)", user.getUserId(), user.getName(), user.getAddress(), user.getEmail()
                    , user.getPassword())) {

                Not.notificationsConfirm("Added New User", "Saved");
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            Not.notificationError("Can not Add User", "Something Went Wrong");

        }
    }

    private void Clear() {
        txtAddress.clear();
        txtEmail.clear();
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        setBorders(txtAddress,txtEmail,txtId,txtName,txtPassword);
    }


    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map, BtnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, BtnAdd);
            ;
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                SaveUser();
            }
        }
    }

    public void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }
}



