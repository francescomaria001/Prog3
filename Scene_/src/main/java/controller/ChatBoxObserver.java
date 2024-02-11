package controller;

import javafx.scene.control.TextArea;

public class ChatBoxObserver implements Observer {
    private TextArea chatBox;

    public ChatBoxObserver(TextArea chatBox) {
        this.chatBox = chatBox;
    }

    @Override
    public void update(Object data) {

    }

    @Override
    public void notify(String message) {
        // Aggiorna la chatBox con il nuovo messaggio
        chatBox.appendText(message + "\n");
    }
    @Override
    public void update(String message) {
        // Aggiorna la chatBox con il nuovo messaggio
        chatBox.appendText(message + "\n");
    }
}
