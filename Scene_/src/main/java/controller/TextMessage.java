package controller;

import java.util.Date;
import java.util.List;

public class TextMessage extends Message {
    private String content;

    public TextMessage(String sender, Date timestamp,String content,List <String> hashtag) {
        super(sender, timestamp,content, hashtag);
        this.content = content;
    }

    @Override
    public void sendMessage() {
        // Modifica il metodo sendMessage per adattarlo alle tue esigenze
        System.out.println("Inviato messaggio di testo: " + content);
        // Includi qui la logica per salvare il messaggio nel database
        // Ad esempio, puoi chiamare il metodo saveMessage della classe DatabaseService
        DatabaseService.saveMessage(this, sender);
    }

    @Override
    public String getFormattedText() {
        // Utilizza il metodo della classe base per ottenere il testo formattato
        return super.getFormattedText();
    }
}

