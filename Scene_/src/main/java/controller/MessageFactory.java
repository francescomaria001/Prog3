package controller;
import controller.Message;

import java.util.Date;
import java.util.List;

public interface MessageFactory {
    Message createMessage( String sender, Date timestamp, String content ,List<String> hashtags);
}
