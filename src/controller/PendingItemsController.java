package controller;

import TM.PendingOrderTm;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.media.jfxmedia.events.PlayerTimeListener;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PendingItemsController {
    public Label lbldate;
    public TableView<PendingOrderTm> tblPendingTable;
    public TableColumn colCode;
    public TableColumn colAddress;
    public TableColumn colPrice;
    public TableColumn colSenderName;
    public TableColumn ColDate;
    public TableColumn colOption;
    public JFXDatePicker txtDatePicker;
    public Button btnSearch;
    public DatePicker txtDateChose;
    public TextField txtsearch;
    public DatePicker txtDate1;
    public TextField tXtSearch1;
    public Button btnPrint;

    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory("Code"));
        colSenderName.setCellValueFactory(new PropertyValueFactory("SenderName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colPrice.setCellValueFactory(new PropertyValueFactory("Price"));
        ColDate.setCellValueFactory(new PropertyValueFactory("Date"));




        try {
            loadAllPendingOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadAllPendingOrders() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM PendingTable");

        ObservableList<PendingOrderTm> PTList = FXCollections.observableArrayList();
        while (resultSet.next()) {
           // Button btn = new Button("Delete");
            PTList.add(new PendingOrderTm(
                    resultSet.getString("Code"),
                    resultSet.getString("SenderName"),
                    resultSet.getString("Address"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("Date")
                   // btn
            ));
        }
        tblPendingTable.setItems(PTList);

    }



    public void tXtSearch1OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (tXtSearch1.getText().trim().isEmpty()) {
            loadAllPendingOrders();
        }else {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM PendingTable WHERE Date=?  ", tXtSearch1.getText());
            ObservableList<PendingOrderTm> PTLists = FXCollections.observableArrayList();
            while (resultSet.next()) {
                PTLists.add(
                        new PendingOrderTm(
                                resultSet.getString("Code"),
                                resultSet.getString("SenderName"),
                                resultSet.getString("Address"),
                                resultSet.getDouble("Price"),
                                resultSet.getString("Date")


                        )
                );
            }
            tblPendingTable.setItems(PTLists);
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
       ObservableList<PendingOrderTm> PendingRecords = tblPendingTable.getItems();


        try {

           JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/Reports/PendingList.jasper"));
           JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, new JRBeanCollectionDataSource(PendingRecords));
           JasperViewer.viewReport(jasperPrint, false);

        }catch (JRException e) {
            e.printStackTrace();
        }
    }
}