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

    private static final Logger LOGGER = Logger.getLogger(SuperchargerSRLApplication.class.getName());

    @Override
    public void start(Stage primaryStage) {
        try {
            // Tuve que usar ruta absoluta para que funcione en mi pc
            File fxmlFile = new File("C:\\Users\\j.arnaboldi.spb\\IdeaProjects\\SuperchargerSRL\\src\\main\\java\\com\\sap\\superchargersrl\\view\\MainView.fxml");
            if (fxmlFile == null) {
                LOGGER.severe("FXML file not found!");
                // Optionally show a dialog or alert to the user
                return; // Exit start method if FXML is not found
            } else {
                LOGGER.info("FXML file found at: " + fxmlFile);
            }
            Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Supercharger SRL Management System");
            primaryStage.show();
        } catch (IOException e) {
            // Log the exception
            LOGGER.log(Level.SEVERE, "Exception occurred", e);
            // Optionally show a dialog or alert to the user
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}