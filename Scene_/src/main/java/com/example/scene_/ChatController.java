package com.example.scene_;

import controller.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.Date;

public class ChatController {

    @FXML
    private TextArea chatBox;

    @FXML
    private TextField messageInputTextField;

    @FXML
    private Button sendButton;
    @FXML
    private Button ReturnButtom;
    @FXML
    private Label UsernameLabel1;

    @FXML
    private Button followButtom;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private MessageObservable messageObservable = new MessageObservable();
    private ChatBoxObserver chatObserver;

    // Factory Method Pattern: Usa un MessageFactory per creare diversi tipi di messaggi
    private MessageFactory messageFactory = new TextMessageFactory();

    // Observer Pattern: Usa un Observer per notificare la chatBox dei nuovi messaggi
    private ChatBoxObserver chatBoxObserver;

    // Utente corrente
    private String loggedInUser;

    public void initialize(String username) {
        this.loggedInUser = username;
        connectSocket();
        startListening();

        // Observer Pattern: Registra la chatBox come observer
        chatBoxObserver = new ChatBoxObserver(chatBox);
        messageObservable.addObserver(chatBoxObserver);
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 8889);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startListening() {
        Thread listenerThread = new Thread(() -> {
            try {
                while (true) {
                    String receivedMessage = reader.readLine();
                    if (receivedMessage != null) {
                        // Notifica gli observer (chatBox) del nuovo messaggio
                        messageObservable.notifyObservers(receivedMessage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listenerThread.start();
    }

    @FXML
    private void handleSendButton() {
        // Ottieni il testo del messaggio dalla casella di input
        String messageText = messageInputTextField.getText();

        // Factory Method Pattern: Crea il messaggio utilizzando il MessageFactory
        Message message = messageFactory.createMessage(loggedInUser, new Date(), messageText,Collections.singletonList("#"));


        // Invia il messaggio al server
        sendMessage(message);

        // Observer Pattern: Notifica gli observer (chatBox) del nuovo messaggio inviato
        messageObservable.notifyObservers(message.getFormattedText());

        // Pulisci la casella di input
        messageInputTextField.clear();
    }

    private void sendMessage(Message message) {
        // Invia il messaggio al server
        writer.println(message.getFormattedText());
    }


    public void setUserName(String username) {
        this.loggedInUser = username;
        // Aggiungi logica aggiuntiva se necessario
    }
    private void saveMessageToDatabase(Message message) {
        // Chiamata al metodo della classe DatabaseService per salvare il messaggio e l'utente nel database
        DatabaseService.saveMessage(message, loggedInUser);
    }
}
