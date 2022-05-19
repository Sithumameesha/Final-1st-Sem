package controller;

import TM.OrderTableTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Order;
import util.CrudUtil;
import util.Not;
import util.ValidationUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class NewOrderController {


    public TextField txtCode;
    public TextField txtAddress;
    public TextField txtPrice;
    public TextField txtSenderName;
    public Label lblDate;
    public TableColumn colCode;
    public TableColumn colAddress;
    public TableColumn colPrice;
    public TableColumn colSenderName;
    public TableColumn ColDate;
    public TableView <OrderTableTm>orderTable;
    public Button btnAdd;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }
    public void initialize(){
        btnAdd.setDisable(true);


        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        colSenderName.setCellValueFactory(new PropertyValueFactory<>("SenderName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("Date"));


        Pattern CodePattern = Pattern.compile("^(D)[0-9]{2,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern PricePattern = Pattern.compile("^[0-9]{2,5}$");

        map.put(txtCode,CodePattern);
        map.put(txtSenderName,namePattern);
        map.put(txtAddress,addressPattern);
        map.put(txtPrice,PricePattern);








        loadDateAndTime();
    }
    ObservableList<OrderTableTm> orderlistTm = FXCollections.observableArrayList();
    public void btnAddOrderOnAction(ActionEvent actionEvent) {
        SaveOders();

    }

    private void SaveOders() {
        OrderTableTm orderTableTm= new OrderTableTm(
                txtCode.getText(),
                txtSenderName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtPrice.getText()),
                lblDate.getText()
        );
        orderlistTm.add(orderTableTm);
        orderTable.setItems(orderlistTm);
        clearText();
    }

    private void clearText() {
        txtCode.clear();
        txtAddress.clear();
        txtPrice.clear();
        txtSenderName.clear();
        setBorders(txtAddress,txtCode,txtPrice,txtSenderName);
    }

    public void SetOrderOnAction(ActionEvent actionEvent) throws SQLException {
       ArrayList<Order>or=new ArrayList<>();
        for (OrderTableTm orderTableTm: orderlistTm
             ) {
            or.add(new Order(
                    orderTableTm.getCode(),
                    orderTableTm.getSenderName(),
                    orderTableTm.getAddress(),
                    orderTableTm.getPrice(),
                    orderTableTm.getDate()

            ));
            
        }

        try {
            SaveOrder(or);
            Not.notificationsConfirm("Saved Order","Sucessfully");
            orderlistTm.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Not.notificationError("Some thing Went Wrong","TryAgain");
        }


    }

    private void SaveOrder(ArrayList<Order>or) throws SQLException, ClassNotFoundException {
        for (Order order :or
             ) {
          CrudUtil.executeUpdate("INSERT INTO PendingTable VALUES(?,?,?,?,?)",
                    order.getCode(),order.getSenderName(),order.getAddress(),order.getPrice(),order.getDate() );

        }

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAdd);;
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                SaveOders();
            }
        }
    }
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }
}
