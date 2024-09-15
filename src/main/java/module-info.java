module com.sap.superchargersrl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.j;
    requires org.slf4j;


    opens com.sap.superchargersrl to javafx.fxml;
    exports com.sap.superchargersrl;
    exports com.sap.superchargersrl.controller;
    opens com.sap.superchargersrl.controller to javafx.fxml;
}