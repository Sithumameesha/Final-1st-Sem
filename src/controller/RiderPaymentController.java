package controller;

import TM.DeliveryListTm;
import TM.RiderDetailsTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RiderPaymentController {
    public TableView<RiderDetailsTm> tblRiderPaymentTable;
    public TableColumn colRid;
    public TableColumn colRiderName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colPayment;
    public TableColumn colDate;
    public Button btnPrint;
    public TextField txtSerach;

    public void initialize() {
        colRid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colRiderName.setCellValueFactory(new PropertyValueFactory<>("RiderName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("RiderPayment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            LoadAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void LoadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM RiderTable");

        ObservableList<RiderDetailsTm> RList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            RList.add(new RiderDetailsTm(
                    resultSet.getString("Name"),
                    resultSet.getString("Rid"),
                    resultSet.getString("Address"),
                    resultSet.getInt("Contact"),
                    resultSet.getDouble("Payment"),
                    resultSet.getString("Date")


            ));
        }
        tblRiderPaymentTable.setItems(RList);

    }

    public void btnPrintOnAction(ActionEvent actionEvent) {

        ObservableList<RiderDetailsTm> riderDetailsTms = tblRiderPaymentTable.getItems();


        try {

            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/Reports/RiderDetails.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, new JRBeanCollectionDataSource(riderDetailsTms));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }


    }

    public void txtSerachOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (txtSerach.getText().trim().isEmpty()) {
            LoadAll();
        } else {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM RiderTable WHERE Date=?  ", txtSerach.getText());
            ObservableList<RiderDetailsTm> RD = FXCollections.observableArrayList();
            while (resultSet.next()) {
                RD.add(
                        new RiderDetailsTm(
                                resultSet.getString("Name"),
                                resultSet.getString("Rid"),
                                resultSet.getString("Address"),
                                resultSet.getInt("Contact"),
                                resultSet.getDouble("Payment"),
                                resultSet.getString("Date")


                        )
                );
            }
            tblRiderPaymentTable.setItems(RD);
        }
    }
}



