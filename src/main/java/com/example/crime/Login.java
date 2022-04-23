package com.example.crime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {


    public TextField userID;
    public TextField password;
    public Label label;

    @FXML
    protected void logIn(ActionEvent e) throws IOException {
        DatabaseConnection con = new DatabaseConnection();

        Connection conBD = con.getConnection();

        String id = userID.getText();
        String pass = password.getText();

        String q = "SELECT * FROM user;";

        boolean logged = false;
        try {
            Statement s = conBD.createStatement();

            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (id.equals(rs.getString(1))&&pass.equals(rs.getString(2))) {
                    System.out.println(id+" logged in");
                    logged = true;
                    label.setText("");

                    if (rs.getInt(3)==1){
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Scene scene = new Scene(root);
                        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                        break;
                    }

                    else if (rs.getInt(3)==2){
                        Parent root = FXMLLoader.load(getClass().getResource("dashboardUser.fxml"));
                        Scene scene = new Scene(root);
                        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                        break;
                    }

                    else if (rs.getInt(3)==3){
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard2User.fxml"));
                        Scene scene = new Scene(root);
                        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                        break;
                    }
                }
            }
            if (!logged) {
                label.setText("Wrong ID or Password");
                password.setText("");
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

}