package controller;

import model.OrderDetails;
import model.RiderDetails;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public static ArrayList<String> getOrderCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT Code  FROM PendingTable");
        ArrayList<String> code = new ArrayList<>();
        while (result.next()) {
            code.add(result.getString(1));
        }
        return code;
    }

    public static OrderDetails GetOrderDetail(String Code) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT * FROM PendingTable WHERE Code = ?", Code);
        if (result.next()) {
            return new OrderDetails(
                    result.getString(3),
                    result.getDouble(4)
            );
        }
        return null;


    }

    public static ArrayList<String> GetRiderName() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT Name  FROM RiderDetails");
        ArrayList<String> Name = new ArrayList<>();
        while (result.next()) {
            Name.add(result.getString(1));
        }
        return Name;
    }
}
