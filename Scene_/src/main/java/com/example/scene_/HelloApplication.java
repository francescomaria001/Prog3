package com.example.scene_;
import com.example.scene_.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*
        // Carica la schermata di login come schermata iniziale
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 320.0, 240.0);
        Parent loginRoot = fxmlLoader.load();
        Scene loginScene = new Scene(loginRoot);

        // Imposta il controller per la schermata di login (sostituisci con il tuo controller effettivo)
        LoginController loginController = fxmlLoader.getController();

        // Passa la scena di ricerca alla schermata di login (da utilizzare nel cambio scena)


        stage.setTitle("login.fxml");
       stage.setScene(loginScene);
        stage.show();
        */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Scene loginScene = new Scene(loginRoot);

// Imposta il controller per la schermata di login (sostituisci con il tuo controller effettivo)
        LoginController loginController = fxmlLoader.getController();

        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();

    }
}