package com.sap.superchargersrl.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleLoginButtonAction(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username);
            redirectToMainView();
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password");
        }
    }

    private boolean validateLogin(String username, String password) {
        String url = "jdbc:mysql://mysql-sapsuperchargersrldb.alwaysdata.net:3306/sapsuperchargersrldb_final?useSSL=true&serverTimezone=UTC";
        String dbUser = "yourDbUsername";
        String dbPassword = "yourDbPassword";

        String query = "SELECT * FROM Usuarios WHERE username = ? AND password_hash = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectToMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/sap/superchargersrl/view/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}