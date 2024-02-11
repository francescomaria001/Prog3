package com.example.scene_;

import controller.DatabaseService;
import controller.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField AdminPassword;

    @FXML
    private Button ButtonAdmin;
    @FXML
    private TextField AdminName;
    @FXML
    private TextField UserName;

    @FXML
    private TextField UserPassword;

    @FXML
    private Button ButtonUser;

    @FXML
    private Button ButtonRegister;

    // Metodo chiamato quando si preme il pulsante di login
    @FXML
    void handleLogin() {
        System.out.println("Login button clicked");
        String enteredUsername = UserName.getText();
        String enteredPassword = UserPassword.getText();

        // Verifica se l'utente e la password sono validi usando il servizio di database
        if (DatabaseService.isValidUser(enteredUsername, enteredPassword)) {
            // Ottieni l'utente dal servizio di database
            User loggedInUser = DatabaseService.getUser(enteredUsername);

            // Verifica se l'oggetto utente è null
            if (loggedInUser == null) {
                // Crea un nuovo oggetto utente se l'oggetto originale è null
                loggedInUser = new User(enteredUsername);
            }

            // Esegui le azioni di login (puoi personalizzare questa parte)
            loggedInUser.login();

            // Cambia la scena a "search"
            changeScene("Search.fxml");
        } else {
            // Altrimenti, mostra un messaggio di errore
            System.out.println("Credenziali non valide. Riprova.");
        }
    }
    @FXML

    void handleAdminLogin() {
        String enteredAdminName = AdminName.getText();
        String enteredAdminPassword = AdminPassword.getText();

        // Verifica se l'amministratore è valido usando il servizio di database
        if (DatabaseService.isValidAdmin(enteredAdminName, enteredAdminPassword)) {
            // Esegui le azioni di login per l'amministratore (puoi personalizzare questa parte)
            // ...

            // Cambia la scena a "AdminPage"
            changeScene("AdminPage.fxml");
        } else {
            // Altrimenti, mostra un messaggio di errore
            System.out.println("Credenziali amministratore non valide. Riprova.");
        }
    }

    // Metodo chiamato quando si preme il pulsante "Register"
    @FXML
    void handleRegister() {
        // Cambia la scena a "register"
        changeScene("Register.fxml");
    }

    // Metodo per cambiare la scena
    private void changeScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) ButtonUser.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }
    }

}