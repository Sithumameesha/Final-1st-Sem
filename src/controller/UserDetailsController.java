package controller;

import TM.DeliveryListTm;
import TM.UsersListTm;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.Not;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsController {
    public TableView<UsersListTm> tblUser;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtSearch;
    public Button btnDelete;
    public Button btnUpdate;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Userid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));

        tblUser.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });


        try {
            LoadAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(UsersListTm tm) {
        txtName.setText(tm.getName());
        txtId.setText(tm.getUserid());
        txtAddress.setText(tm.getAddress());
        txtEmail.setText(tm.getEmail());
        txtPassword.setText(tm.getPassword());

    }

    private void LoadAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM User");

        ObservableList<UsersListTm> Ulist = FXCollections.observableArrayList();
        while (resultSet.next()) {

            Ulist.add(new UsersListTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)


            ));
        }
        tblUser.setItems(Ulist);
        tblUser.refresh();
    }



    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (txtSearch.getText().isEmpty()) {
            LoadAllUsers();

        } else {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM User WHERE UserId=? ", txtSearch.getText());
            ObservableList<UsersListTm> Ulist = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Ulist.add(
                        new UsersListTm(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)

                        )
                );
            }
            tblUser.setItems(Ulist);


        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {


        try {
            if (CrudUtil.executeUpdate("DELETE FROM User WHERE UserId =?", txtId.getText())) {
                tblUser.refresh();
                //new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
                Not.notificationsConfirm("User Removed", "Deleted");
                Clear();
                tblUser.refresh();

            } else {
                //new Alert(Alert.AlertType.ERROR, "Wrong").show();
                Not.notificationError("Unsucessfully", "Please Select Raw ");
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();


        }
    }

    private void Clear() {

        txtId.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtName.clear();
    }
}

