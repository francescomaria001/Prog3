module com.example.scene_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.scene_ to javafx.fxml;
    exports com.example.scene_;
    exports controller;
    opens controller to javafx.fxml;
}