package controller;

import model.RiderDetails;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RiderCrudController {
     public static ArrayList<String> getRiderName() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT Name  FROM RiderDetails");
        ArrayList<String> name =new ArrayList<>();
        while (result.next()){
            name.add(result.getString(1));
        }
        return name;
    }
    public static RiderDetails getRiderDetails(String RiderName) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT * FROM RiderDetails WHERE  Name = ?",RiderName );
        if (result.next()){
            return new RiderDetails(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)




            );
        }
        return null;


    }
}
