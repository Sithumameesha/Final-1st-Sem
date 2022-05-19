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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane Context;

    public void btnOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM User WHERE Name  =? AND Password = ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        stm.setString(1,txtUserName.getText());
        stm.setString(2, txtPassword.getText());
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM User WHERE Name =? AND Password =?",txtUserName.getText(),txtPassword.getText());
        try {
            if (resultSet.next()) {

                Parent parent= FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"));
                Stage stage=new Stage();
                stage.setScene(new Scene(parent));
                Stage stage2 = (Stage) Context.getScene().getWindow();
                stage2.close();
                stage.show();
                Not.notificationsConfirm("User Name And Password Matched","Sucessfully");



            }else{
              Not.notificationError("Please Enter UserName And Password Correctly","Fail");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    }
