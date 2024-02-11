package controller;

import com.example.scene_.ChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private final Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showChatScene(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chatt.fxml"));
            Parent root = loader.load();

            // Ottieni il controller associato a Chatt.fxml
            ChatController chatController = loader.getController();

            // Passa il nome utente al controller della chat
            chatController.initialize(username);

            Scene scene = new Scene(root);

            // Setta la nuova scena sullo stage primario
            primaryStage.setScene(scene);
            primaryStage.setTitle("Chat - " + username);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }
    }
}
