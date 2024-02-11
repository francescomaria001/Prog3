package controller;

import java.util.Date;
import java.util.List;

public abstract class Message {

        protected String sender;
        protected String text;
        protected Date timestamp;
        protected List<String> hashtags;  // Aggiunto campo per gli hashtag

        public Message(String sender,  Date timestamp, String text, List<String> hashtags) {
            this.sender = sender;
            this.text = text;
            this.timestamp = timestamp;
            this.hashtags = hashtags;
        }



    public abstract void sendMessage();

    public String getFormattedText() {
        // Restituisci il testo formattato, ad esempio "sender: text"
        return sender + ": " + text;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
    public String getContent() {
        return text;
    }
}
