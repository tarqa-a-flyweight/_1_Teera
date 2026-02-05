module com.teera.startpoint.teera {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.teera.startpoint.teera to javafx.fxml;
    exports com.teera.startpoint.teera;
}