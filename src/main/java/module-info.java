module com.dynamic.dev.manifestgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dynamic.dev.manifestgenerator to javafx.fxml;
    exports com.dynamic.dev.manifestgenerator;
}