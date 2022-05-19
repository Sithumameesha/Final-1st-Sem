package controller;

import TM.DeliveryListTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDashBoardController {
    public Label lblUser;
    public Label lblDelivery;
    public Label lblPending;
    public AnchorPane context;
    public Label lblRider;

    public void initialize() {
        try {
            SetRiderCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            SetPending();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            SetDetivery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            SetUserRawCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void SetRiderCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM RiderDetails");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblRider.setText(String.valueOf(count));


        }

    }

    private void SetPending() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM PendingTable");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblPending.setText(String.valueOf(count));


        }
    }

    private void SetDetivery() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM DeliveryTable");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblDelivery.setText(String.valueOf(count));


        }
    }

    private void SetUserRawCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(*)FROM User");
        while (resultSet.next())
        {
            int count = resultSet.getInt(1);
            lblUser.setText(String.valueOf(count));


        }
    }


    public void btnPlaceOrderOnAction(MouseEvent mouseEvent) {
    }

    public void btnAddRiderOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent= FXMLLoader.load(getClass().getResource("../view/AddRider.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void AddUserOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent= FXMLLoader.load(getClass().getResource("../view/AddUser.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void btnDeliveryListOnAction(ActionEvent actionEvent)throws IOException  {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/LoadAllDeliveryItem.fxml"));
        context.getChildren().add(parent);

    }

    public void btnPendingListOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/PendingItems.fxml"));
        context.getChildren().add(parent);
    }

    public void btnRiderPaymentOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/RiderPayment.fxml"));
        context.getChildren().add(parent);

    }

    public void btnUserDetails(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/UserDetails.fxml"));
        context.getChildren().add(parent);
    }
}
