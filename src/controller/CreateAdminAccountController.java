package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Admin;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;
import util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CreateAdminAccountController {
    public TextField txtName;
    public TextField txtId;
    public TextField txtAddress;
    public PasswordField txtPassword;
    public TextField txtEmail;
    public PasswordField txtRePassword;
    public JFXButton btnCreate;
    public AnchorPane Context;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        btnCreate.setDisable(true);


        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern idPattern = Pattern.compile("^[0-9]{11,13}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern emailPattern = Pattern.compile("^[A-z0-9]{3,20}(@gmail.com)$");
        Pattern passwordPattern = Pattern.compile("^[A-z0-9]{3,10}$");
        Pattern rePasswordPattern = Pattern.compile("^[A-z0-9]{3,5}$");


        map.put(txtName, namePattern);
        map.put(txtId, idPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtEmail, emailPattern);
        map.put(txtPassword, passwordPattern);
        map.put(txtRePassword, rePasswordPattern);


    }

    public void btnCreateOnAction(ActionEvent actionEvent) {
        try {
            SaveAdmin();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void clears() {
        txtPassword.clear();
        txtRePassword.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtId.clear();
        txtName.clear();
        setBorders(txtPassword,txtRePassword,txtAddress,txtEmail,txtId,txtName);
    }




    public void textFields_Key_Released(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ValidationUtil.validate(map, btnCreate);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnCreate);
            ;
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                SaveAdmin();
            }
        }
    }

    private void SaveAdmin() throws SQLException, ClassNotFoundException {
        Admin admin = new Admin(
                txtName.getText(),
                txtId.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                txtRePassword.getText()
        );


            try {
                if (txtPassword.getText().equals(txtRePassword.getText())) {
                    CrudUtil.executeUpdate("INSERT INTO Admin VALUES (?,?,?,?,?,?)", admin.getName(), admin.getAId()
                            , admin.getAddress(), admin.getEmail(), admin.getPassword(), admin.getRePassword());

                    Not.notificationsConfirm("Admin Added System","Saved");
                     clears();
                    Parent parent= FXMLLoader.load(getClass().getResource("../view/MainPage.fxml"));
                    Stage stage=new Stage();
                    stage.setScene(new Scene(parent));
                    Stage stage2 = (Stage) Context.getScene().getWindow();
                    stage2.close();
                    stage.show();


                } else {
                    Not.notificationError("Please Insert Data Correctly","Unsucessfully");
                }


            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();

            }
        }
    public void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }


    }



