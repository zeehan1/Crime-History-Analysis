module com.example.crime {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.crime to javafx.fxml;
    exports com.example.crime;
}