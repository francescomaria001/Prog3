package com.example.scene_;

import controller.DatabaseService;
import controller.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private Button BottonLOg;

    @FXML
    private TextField UserRegister;

    @FXML
    private PasswordField PasswordRegister;

    private DatabaseService databaseService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @FXML
    void handleRegistration() {
        String username = UserRegister.getText();
        String password = PasswordRegister.getText();

        if (isValid(username, password)) {
            User newUser = new User(username);

            if (DatabaseService.saveUser(newUser)) {
                System.out.println("Registrazione avvenuta con successo");
                // Cambio scena: implementa come desideri
            } else {
                System.out.println("Errore durante la registrazione");
                // Gestisci l'errore, ad esempio, mostrando un messaggio all'utente
            }
        } else {
            System.out.println("Username o password non validi");
            // Gestisci l'errore, ad esempio, mostrando un messaggio all'utente
        }
    }

    private boolean isValid(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }



    private void changeScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) BottonLOg.getScene().getWindow();  // Ottieni il riferimento alla finestra corrente
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


