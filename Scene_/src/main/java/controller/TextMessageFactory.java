package controller;

import java.util.Date;
import java.util.List;

public class TextMessageFactory implements MessageFactory {

    @Override
    public Message createMessage(String sender, Date timestamp, String content, List<String> hashtags) {
            return new TextMessage(sender, timestamp,  content, hashtags);


    }

}
