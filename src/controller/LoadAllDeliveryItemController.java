package controller;

import TM.DeliveryListTm;
import TM.PendingOrderTm;
import TM.PlaceOrderTableTm;
import com.sun.jmx.remote.security.NotificationAccessController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Delivery;
import model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;
import util.CrudUtil;
import util.Not;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllDeliveryItemController {
    public Label lbldate;
    public TableView<DeliveryListTm> tblDeliveryTable;
    public TableColumn colCode;
    public TableColumn colRiderName;
    public TableColumn colAddress;
    public TableColumn colPrice;
    public TableColumn colProfit;
    public TableColumn colDate;
    public TableColumn colButton;
    public TextField txtSearch;
    public Button btnPrint;
    public Button BtnDelete;
    public Label lblTotal;
    public TextField txtCode;
    public TextField txtProfit;
    public TextField txtDate;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPrice;
    public Button btnUpdate;


    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colRiderName.setCellValueFactory(new PropertyValueFactory("riderName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colProfit.setCellValueFactory(new PropertyValueFactory("profit"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));



        tblDeliveryTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });





        try {
            LoadAllDeliveryItems();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(DeliveryListTm tmm) {
        txtCode.setText(tmm.getCode());
        txtName.setText(tmm.getRiderName());
        txtAddress.setText(tmm.getAddress());
        txtProfit.setText(String.valueOf(tmm.getProfit()));
        txtPrice.setText(String.valueOf(tmm.getPrice()));
        txtDate.setText(tmm.getDate());






    }

    private void LoadAllDeliveryItems() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM DeliveryTable");

        ObservableList<DeliveryListTm> DLlist = FXCollections.observableArrayList();
        while (resultSet.next()) {

            DLlist.add(new DeliveryListTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)



            ));
        }
        tblDeliveryTable.setItems(DLlist);
        tblDeliveryTable.refresh();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        ObservableList<DeliveryListTm> DeliveryRecords = tblDeliveryTable.getItems();


        try {

            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/Reports/DeliveryList.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, new JRBeanCollectionDataSource(DeliveryRecords));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void BtnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if (CrudUtil.executeUpdate("DELETE FROM DeliveryTable WHERE Dcode =?",txtCode.getText())) {
                tblDeliveryTable.refresh();

                Not.notificationsConfirm("Raw Deleted","Sucessfully");


            } else {
               Not.notificationError("Please Select Raw","Fail");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();


        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtSearch.getText().isEmpty()) {
            LoadAllDeliveryItems();

        } else {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM DeliveryTable WHERE date=?  ", txtSearch.getText());
            ObservableList<DeliveryListTm> PTLists = FXCollections.observableArrayList();
            while (resultSet.next()) {
                PTLists.add(
                        new DeliveryListTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getDouble(5),
                                resultSet.getString(6)


                        )
                );
            }
            tblDeliveryTable.setItems(PTLists);


        }
    }


    }




