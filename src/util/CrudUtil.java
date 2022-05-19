package util;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement execute(String sql, Object...params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i< params.length;i++){
            statement.setObject((i+1),params[i]);
        }
        return statement;
    }
    public static ResultSet executeQuery(String sql, Object...params) throws SQLException, ClassNotFoundException {
        return execute(sql,params).executeQuery();
    }
    public static boolean  executeUpdate(String sql,Object...params) throws SQLException, ClassNotFoundException {
        return execute(sql,params).executeUpdate()>0;
    }


}