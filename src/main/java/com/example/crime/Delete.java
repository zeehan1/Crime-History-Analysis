package com.example.crime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Delete {
    @FXML
    public Label Name;
    @FXML
    public Label Detail;
    @FXML
    public Label Age;
    @FXML
    public Label lb;
    @FXML
    public Button bt;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> NameA = new ArrayList<>();
    ArrayList<Integer> AgeA = new ArrayList<>();
    ArrayList<String> DetailA = new ArrayList<>();

    public static int idU=0;

    @FXML
    public void initialize(){

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM data;";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (Objects.equals(rs.getInt(6), 1)){
                    id.add(rs.getInt(1));
                    NameA.add(rs.getString(2));
                    DetailA.add(rs.getString(4));
                    AgeA.add(rs.getInt(3));
                }
            }
            if (id.isEmpty()){
                lb.setText("No Request(s)");
                myChoiceBox.isDisable();
                bt.isDisable();
            }

            myChoiceBox.getItems().addAll(NameA);
            myChoiceBox.setOnAction(this::getFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFood(ActionEvent actionEvent) {
        String acc = myChoiceBox.getValue();
        lb.setText("");
        for (int i = 0; i<id.size(); i++){
            if (Objects.equals(NameA.get(i), acc)){
                Name.setText(String.valueOf(NameA.get(i)));
                Detail.setText(String.valueOf(DetailA.get(i)));
                Age.setText(String.valueOf(AgeA.get(i)));
                idU = id.get(i);
            }
        }
    }

    @FXML
    protected void DeleteDetail(ActionEvent actionEvent) throws IOException, SQLException {
        if (NameA.isEmpty())
            return;

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "DELETE FROM `crime`.`data` WHERE (`id` = '"+idU+"');";

        statement.executeUpdate(q);

        lb.setText("Deleted");

        myChoiceBox.getItems().clear();
        NameA.clear();
        id.clear();
        DetailA.clear();
        AgeA.clear();
        Name.setText("");
        Detail.setText("");
        Age.setText("");
        idU=0;
        initialize();
    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
