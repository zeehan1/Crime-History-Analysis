package com.example.crime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private TableView<Data> dataTableView;
    @FXML
    private TableColumn<Data,String> nameTableColumn;
    @FXML
    private TableColumn<Data,Integer> ageTableColumn;
    @FXML
    private TableColumn<Data,String> detailTableColumn;

    @FXML
    public Label u;
    @FXML
    public Label d;

    ObservableList<Data> userObservableList = FXCollections.observableArrayList();

    public int uU = 0;
    public int dD = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM data;";

        try {
            Statement statement = conBD.createStatement();
            ResultSet rs = statement.executeQuery(q);

            while (rs.next()){
                int id = rs.getInt("id");
                String qName = rs.getString("name");
                int qAge = rs.getInt("age");
                String qDetail = rs.getString("detail");
                String qDetailU = rs.getString("detailU");
                int delete = rs.getInt("delete");

                userObservableList.add(new Data(id,qName,qAge,qDetail,qDetailU,delete));
                if (!Objects.equals(qDetailU, "0"))
                    uU++;
                if (delete==1)
                    dD++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        detailTableColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        dataTableView.setItems(userObservableList);
        u.setText(String.valueOf(uU));
        d.setText(String.valueOf(dD));
    }

    @FXML
    protected void Add(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Update(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("update.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Delete(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
