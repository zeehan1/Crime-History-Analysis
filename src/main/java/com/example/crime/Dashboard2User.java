package com.example.crime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public class Dashboard2User implements Initializable {

    @FXML
    private TextField t;

    @FXML
    private TableView<Data> dataTableView;
    @FXML
    private TableColumn<Data,String> nameTableColumn;
    @FXML
    private TableColumn<Data,Integer> ageTableColumn;
    @FXML
    private TableColumn<Data,String> detailTableColumn;

    ObservableList<Data> userObservableList = FXCollections.observableArrayList();


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
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        detailTableColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        dataTableView.setItems(userObservableList);

        FilteredList<Data> filteredList = new FilteredList<>(userObservableList,b -> true);

        t.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(p->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue==null)
                    return true;

                String s = newValue.toLowerCase();

                if (p.getName().toLowerCase().indexOf(s)> -1)
                    return true;
                else return false;
            });
        });
        SortedList<Data> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(dataTableView.comparatorProperty());

        dataTableView.setItems(sortedList);
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
