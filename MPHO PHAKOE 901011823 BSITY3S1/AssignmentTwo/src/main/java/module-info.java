module com.example.excerse1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.excerse1 to javafx.fxml;
    exports com.example.excerse1;
}