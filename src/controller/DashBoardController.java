package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardController {
    public Label lbldate;
    public Button btnOrder;
    public AnchorPane context;
    public Label lblPending;
    public Label lblDelivery;
    public AnchorPane Context;


    public void initialize(){
        loadDateAndTime();
        try {
            SetDeliveryRaw();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            SetPendingRaw();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void SetDeliveryRaw() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM DeliveryTable");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblDelivery.setText(String.valueOf(count));


        }
        
    }

    private void SetPendingRaw() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM PendingTable");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblPending.setText(String.valueOf(count));


        }
    }

    private void loadDateAndTime() {
        lbldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {

        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/NewOrder.fxml"));
        context.getChildren().add(parent);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/PlaceOrder.fxml"));
        context.getChildren().add(parent);
    }

    public void btnRiderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/RiderDetails.fxml"));
        context.getChildren().add(parent);
    }

    public void btnDeliveryListOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/LoadAllDeliveryItem.fxml"));
        context.getChildren().add(parent);
    }

    public void btnPendingListOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/PendingItems.fxml"));
        context.getChildren().add(parent);
    }

    public void LogOutOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("../view/MainPage.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        Stage stage2 = (Stage) Context.getScene().getWindow();
        stage2.close();
        stage.show();


    }
}
