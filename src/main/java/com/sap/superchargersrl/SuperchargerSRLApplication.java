package com.sap.superchargersrl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuperchargerSRLApplication extends Application {

    // Puse un Logger porque no podía saber la razón por la que no encontraba el MainView.fxml
    private static final Logger LOGGER = Logger.getLogger(SuperchargerSRLApplication.class.getName());

    @Override
    public void start(Stage primaryStage) {
        try {
            // Luego de implementar el logger y probar varias opciones, la ruta absoluta es la única forma que funciona en mi pc.
            File fxmlFile = new File("C:\\proyectos\\TS-SAP\\SAP\\src\\main\\java\\com\\sap\\superchargersrl\\view\\LoginView.fxml");
            if (fxmlFile == null) {
                LOGGER.severe("FXML file not found!");
                return;
            } else {
                LOGGER.info("FXML file found at: " + fxmlFile);
            }
            Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Supercharger SRL Management System");
            primaryStage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}