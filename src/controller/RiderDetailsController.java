package controller;

import TM.OrderTableTm;
import TM.PlaceOrderTableTm;
import TM.RiderDetailsTm;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OrderDetails;
import model.RiderDetails;
import util.CrudUtil;
import util.Not;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RiderDetailsController {
    public AnchorPane context;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtItemsNum;
    public Button btnCalculate;
    public TableView <RiderDetailsTm>tblRiderDetails;
    public TableColumn colRiderName;
    public TableColumn colId;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colRiderPayment;
    public TableColumn colDate;
    public Label lblDate;
    public Label lblPayment;
    public ComboBox <String>rmdName;
    public TextField txtId;
    public TableColumn coldate;
    public Button SteItems;
    public JFXDatePicker txtDatePicker;

    public void initialize(){
        colRiderName.setCellValueFactory(new PropertyValueFactory<>("RiderName"));
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colRiderPayment.setCellValueFactory(new PropertyValueFactory<>("RiderPayment"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));


        loadDateAndTime();
        SetRiderName();
        rmdName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SetRiderDetails(newValue);
        });
    }

    private void SetRiderDetails(String SelectedName) {
        try {
            RiderDetails riderDetails = RiderCrudController.getRiderDetails(SelectedName);
            if (riderDetails != null) {
                txtId.setText(riderDetails.getId());
                txtAddress.setText(riderDetails.getAddress());
                txtContact.setText(String.valueOf(riderDetails.getContact()));


            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Set");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void SetRiderName() {
        ObservableList<String> NameList = null;
        try {
            NameList = FXCollections.observableArrayList(
                    RiderCrudController.getRiderName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        rmdName.setItems(NameList);
    }


    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }
    ObservableList<RiderDetailsTm> riderDetailsTmList = FXCollections.observableArrayList();
    public void btnCalculateOnAction(ActionEvent actionEvent) {
        double itemsNum = Double.parseDouble(txtItemsNum.getText());
        double riderPayment= itemsNum*125;
        RiderDetailsTm riderDetailsTm=new RiderDetailsTm(
                rmdName.getValue(),
                txtId.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtContact.getText()),
                riderPayment,
                lblDate.getText()

        );
       riderDetailsTmList .add(riderDetailsTm);
        tblRiderDetails.setItems(riderDetailsTmList);
        lblPayment.setText(String.valueOf(riderPayment));
        ClearText();



    }

    private void ClearText() {
        txtAddress.clear();
        txtContact.clear();
        txtId.clear();
        txtItemsNum.clear();
        rmdName.getSelectionModel().clearSelection();
    }

    public void btnNewRiderOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent= FXMLLoader.load(getClass().getResource("../view/AddRider.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void SteItemsOnAction(ActionEvent actionEvent) {
        ArrayList<RiderDetailsTm> riderDetailsTms = new ArrayList<>();
        for (RiderDetailsTm riderDetailsTm : riderDetailsTmList
        ) {
            riderDetailsTms.add(new RiderDetailsTm(
                   riderDetailsTm.getRiderName(),
                    riderDetailsTm.getId(),
                    riderDetailsTm.getAddress(),
                    riderDetailsTm.getContact(),
                    riderDetailsTm.getRiderPayment(),
                    riderDetailsTm.getDate()

            ));
        }

        try {
            Save(riderDetailsTms);
            Not.notificationsConfirm("Saved ","Sucessfully");
             riderDetailsTmList.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
    private void Save(ArrayList<RiderDetailsTm> ROT) throws SQLException, ClassNotFoundException {
        for (RiderDetailsTm  riderDetailsTm : ROT
        ) {
            CrudUtil.executeUpdate("INSERT INTO RiderTable  VALUES(?,?,?,?,?,?)",
                    riderDetailsTm.getRiderName(),riderDetailsTm.getId(),riderDetailsTm.getAddress(),
                    riderDetailsTm.getContact(),riderDetailsTm.getRiderPayment(),
                    riderDetailsTm.getDate());


        }

    }
}

