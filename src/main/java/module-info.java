module com.example.geodesy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.geodesy to javafx.fxml;
    exports com.example.geodesy;
}