package com.example.scene_;

import controller.DatabaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class AdminController {

    @FXML
    private TextField USerResearch;

    @FXML
    private ListView<String> UserSearch;

    @FXML
    private Button buttonCerca;
    @FXML
    void handleSearch() {
        String targetUsername = USerResearch.getText();

        // Implementa la logica per recuperare e visualizzare i messaggi dell'utente
        List<String> userMessages = DatabaseService.getUserMessages(targetUsername);

        // Aggiorna la ListView con i messaggi dell'utente
        UserSearch.getItems().setAll(userMessages);
    }
}



