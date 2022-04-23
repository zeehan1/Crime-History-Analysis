package com.example.crime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.crime.DashboardUser.id;

public class PopDelete {
    @FXML
    public Label lb;

    @FXML
    protected void DeleteReq(ActionEvent actionEvent) throws IOException, SQLException {
        lb.setText("");
        int b = id;

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `crime`.`data` SET `delete` = '1' WHERE (`id` = '"+id+"');";

        statement.executeUpdate(q);

        lb.setText("Requested");

    }
}
