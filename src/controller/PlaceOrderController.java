package controller;


import TM.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Order;
import model.OrderDetails;
import model.RiderDetails;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderController {

    public ComboBox <String>ombCode;
    public ComboBox <String>rmbName;
    public TextField txtAddress;
    public TextField txtPrice;
    public Button AddTable;
    public TableView <PlaceOrderTableTm>PlaceOrderTable;
    public TableColumn colCode;
    public TableColumn colRiderName;
    public TableColumn colAddress;
    public TableColumn colPrice;
    public TableColumn colCompanyProfit;
    public TableColumn ColDate;
    public Label lblTotal;
    public Label lblCompanyProfit;
    public Label lblDate;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
       colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
       colRiderName.setCellValueFactory(new PropertyValueFactory<>("RiderName"));
        colCompanyProfit.setCellValueFactory(new PropertyValueFactory<>("CompanyProfit"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("Date"));



        loadDateAndTime();
        SetOrderId();
        SetRiderName();
        ombCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SetOrderDetails(newValue);
        });

    }

    private void SetRiderName() {
        ObservableList<String> NameList = null;
        try {
            NameList = FXCollections.observableArrayList(
                    OrderController.GetRiderName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        rmbName.setItems(NameList);

    }

    private void SetOrderDetails(String SelectedOrderCode) {
        try {
            OrderDetails orderDetail = OrderController.GetOrderDetail(SelectedOrderCode);
            if (orderDetail != null) {
                txtAddress.setText(orderDetail.getAddress());
                txtPrice.setText(String.valueOf(orderDetail.getPrice()));


            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Set");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void SetOrderId() {
        ObservableList<String> codeList = null;
        try {
            codeList = FXCollections.observableArrayList(
                    OrderController.getOrderCode()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ombCode.setItems(codeList);
    }

    ObservableList<PlaceOrderTableTm> placeOrderTableTmList = FXCollections.observableArrayList();
    public void AddTableOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double Price = Double.parseDouble(txtPrice.getText());
        double CompanyProfit= Price * 10/100;
        PlaceOrderTableTm placeOrderTableTm = new PlaceOrderTableTm(
                ombCode.getValue(),
                rmbName.getValue(),
                txtAddress.getText(),
                Price,
                CompanyProfit,
                lblDate.getText()

        );
        placeOrderTableTmList.add(placeOrderTableTm);
        PlaceOrderTable.setItems(placeOrderTableTmList);
        Delete();
        totalCost();
        CompanyProfitCalculate();
        ClearText();

    }

    private void Delete() throws SQLException, ClassNotFoundException {

        try {
            if (CrudUtil.executeUpdate("DELETE FROM PendingTable WHERE code =?",ombCode.getValue())) {
            } else {
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();


        }
    }


    private void ClearText() {
        txtAddress.clear();
        txtPrice.clear();
        ombCode.getSelectionModel().clearSelection();
        rmbName.getSelectionModel().clearSelection();


    }

    private void CompanyProfitCalculate() {
        double profit = 0;
        for (PlaceOrderTableTm tm:placeOrderTableTmList
        ) {
            profit+=tm.getCompanyProfit();
        }
        lblCompanyProfit.setText(String.valueOf(profit));
    }
    public void btnSetItemsOnAction(ActionEvent actionEvent) {
        ArrayList<PlaceOrderTableTm> placeOrderTableTm = new ArrayList<>();
        for (PlaceOrderTableTm placeOrderTabletm : placeOrderTableTmList
        ) {
            placeOrderTableTm.add(new PlaceOrderTableTm(
                    placeOrderTabletm.getCode(),
                    placeOrderTabletm.getRiderName(),
                    placeOrderTabletm.getAddress(),
                    placeOrderTabletm.getPrice(),
                    placeOrderTabletm.getCompanyProfit(),
                    placeOrderTabletm.getDate()
            ));
        }
        try {
            SaveItems(placeOrderTableTm);
            Not.notificationsConfirm("Saved Order","Sucessfully");
            placeOrderTableTmList.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
           Not.notificationError("Some Thing Went Wrong","Fail");
        }
    }

    
    

    private void SaveItems(ArrayList<PlaceOrderTableTm> POT) throws SQLException, ClassNotFoundException {
        for (PlaceOrderTableTm  placeOrderTableTm : POT
        ) {
            CrudUtil.executeUpdate("INSERT INTO DeliveryTable VALUES(?,?,?,?,?,?)",
                    placeOrderTableTm.getCode(),placeOrderTableTm.getRiderName(),placeOrderTableTm.getAddress(),placeOrderTableTm.getPrice(),
                    placeOrderTableTm.getCompanyProfit(),placeOrderTableTm.getDate() );

        }
    }
    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }
    private void totalCost(){
        double total = 0;
        for (PlaceOrderTableTm tm:placeOrderTableTmList
        ) {
            total+=tm.getPrice();
        }
        lblTotal.setText(String.valueOf(total));
    }

}
