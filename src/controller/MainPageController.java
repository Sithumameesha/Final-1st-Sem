package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Admin;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPageController {
    public AnchorPane Context;
    public JFXPasswordField txtPassword;
    public JFXTextField txtAdminName;
    public AnchorPane Con;


    public void CreateNewAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("../view/CreateAdminAccount.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        Stage stage2 = (Stage) Context.getScene().getWindow();
        stage2.close();
        stage.show();


    }

    public void UserLoginOnAction(MouseEvent mouseEvent) throws IOException {
        Context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserLogin.fxml"));
        Context.getChildren().add(parent);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Admin WHERE Name  =? AND Password = ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        stm.setString(1,txtAdminName.getText());
        stm.setString(2, txtPassword.getText());
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Admin WHERE Name =? AND Password =?",txtAdminName.getText(),txtPassword.getText());
        try {
            if (resultSet.next()) {
                Not.notificationsConfirm("UserName And Password Matched","Successfully");
                Cller();


                Parent parent= FXMLLoader.load(getClass().getResource("../view/AdminDashBoard.fxml"));
                Stage stage=new Stage();
                stage.setScene(new Scene(parent));
                Stage stage2 = (Stage) Context.getScene().getWindow();
                stage2.close();
                stage.show();


            }else{
                Not.notificationError("Please Enter UserName And Password Correctly","Login Fail");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    private void Cller() {
        txtAdminName.clear();
        txtPassword.clear();
    }
}

