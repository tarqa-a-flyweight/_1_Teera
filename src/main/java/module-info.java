module com.teera.startpoint.teera {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;

    exports com.teera.startpoint;
    opens com.teera.startpoint to javafx.fxml;
}