package com.example.crime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public  Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("CrimeX");

        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root, 900, 700);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}