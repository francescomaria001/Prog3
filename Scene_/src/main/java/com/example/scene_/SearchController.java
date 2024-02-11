package com.example.scene_;

import controller.User;
import controller.DatabaseService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SearchController {

    @FXML
    private ListView<String> ListUser;

    @FXML
    private TextField searchid;

    @FXML
    public void initialize() {
        // Mostra la lista di utenti nel ListView all'avvio
        refreshUserList();

        // Aggiungi un gestore per il clic su un elemento della ListView
        ListUser.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {  // Se è un singolo clic
                handleUserSelection();
            }
        });
    }

    @FXML
    private void onSearchButtonClicked() {
        String usernameToSearch = searchid.getText();

        // Utilizza DatabaseService per cercare l'utente nel database
        User searchedUser = DatabaseService.getUser(usernameToSearch);

        if (searchedUser != null) {
            // Se l'utente è presente, aggiorna la ListView
            ListUser.getItems().clear();
            ListUser.getItems().add(searchedUser.getUsername());
        } else {
            // Se l'utente non è presente, mostra un messaggio di errore o fai altro
            System.out.println("Utente non trovato.");
            // Puoi mostrare un messaggio di errore nell'interfaccia utente, ad esempio, con un Label
        }
    }

    private void handleUserSelection() {
        String selectedUsername = ListUser.getSelectionModel().getSelectedItem();
        if (selectedUsername != null) {
            // Qui puoi chiamare il metodo per cambiare la scena passando il nome dell'utente selezionato
            changeScene("Chatt.fxml", selectedUsername);
        }
    }

    private void refreshUserList() {
        List<String> allUsers = DatabaseService.getAllUsers();
        ListUser.getItems().clear();
        ListUser.getItems().addAll(allUsers);
    }

    // Aggiungi il metodo per cambiare la scena
    private void changeScene(String fxmlFile, String selectedUsername) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Ottenere il controller della nuova scena (se necessario)
            ChatController chatController = loader.getController();

            // Personalizzare la nuova scena in base alle tue esigenze
            chatController.setUserName(selectedUsername);

            // Ottenere la scena corrente
            Scene currentScene = ListUser.getScene();

            // Impostare la nuova scena sulla finestra principale
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }
    }

}
