package com.example.crime;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.crime.DashboardUser.id;

public class PopUpdate{
        @FXML
        public Label lb;

        @FXML
        public TextArea text;

        @FXML
        protected void UpdateReq(ActionEvent actionEvent) throws IOException, SQLException {
                if (text.getText().trim().isEmpty()){
                        lb.setText("Blank");
                }
                else{

                        String a = text.getText();
                        lb.setText("");
                        int b = id;

                        DatabaseConnection con = new DatabaseConnection();
                        Connection conBD = con.getConnection();
                        Statement statement = conBD.createStatement();

                        String q = "UPDATE `crime`.`data` SET `detailU` = '"+a+"' WHERE (`id` = '"+b+"');";

                        statement.executeUpdate(q);

                        lb.setText("Requested");
                }

        }
}
