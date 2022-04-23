package com.example.crime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {

    @FXML
    public TextField name;
    @FXML
    public TextField age;
    @FXML
    public TextArea detail;

    @FXML
    public Label nameW;
    @FXML
    public Label ageW;
    @FXML
    public Label detailW;
    @FXML
    public Label a;

    @FXML
    protected void AddDetail(ActionEvent actionEvent) throws IOException, SQLException {
        String acc01="", acc02="", acc03="";
        boolean acb01 = false,  acb02 = false, acb03 = false;

        if (detail.getText().trim().isEmpty()){
            detailW.setText("Blank");
            acb03 = false;
        }
        else{
            acc03 = detail.getText();
            detailW.setText("");
            acb03 = true;
        }

        if (name.getText().trim().isEmpty()){
            nameW.setText("Blank");
            acb01 = false;
        }
        else{
            acc01 = name.getText();
            nameW.setText("");
            acb01 = true;
        }

        if (age.getText().trim().isEmpty()){
            ageW.setText("Blank");
            acb02 = false;
        }
        else if (!isNumeric(age.getText())){
            ageW.setText("Not Number");
            acb02 = false;
        }
        else{
            acc02 = age.getText();
            ageW.setText("");
            acb02 = true;
        }

        if (acb01 && acb02 && acb03){
            System.out.println(acc01+acc02+acc03);
            DatabaseConnection con = new DatabaseConnection();
            Connection conBD = con.getConnection();
            Statement statement = conBD.createStatement();

            String q = "INSERT INTO `crime`.`data` (`name`, `age`, `detail`, `detailU`, `delete`) VALUES ('"+acc01+"', '"+acc02+"', '"+acc03+"', '0', '0');";
            statement.executeUpdate(q);
            name.setText("");
            age.setText("");
            detail.setText("");
            a.setText("Date entry complete");
        }

    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
