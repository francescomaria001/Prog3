package controller;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<String> followers;

    private List<String> messagesReceived;

    public User(String username) {
        this.username = username;
        this.followers = new ArrayList<>();
        this.messagesReceived = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getMessagesReceived() {
        return messagesReceived;
    }

    public void followUser(String userToFollow) {
        // Implementa la logica per seguire un utente
        if (!followers.contains(userToFollow)) {
            followers.add(userToFollow);
        }
    }

    public void unfollowUser(String userToUnfollow) {
        // Implementa la logica per smettere di seguire un utente
        followers.remove(userToUnfollow);
    }

    public void sendMessage(String message) {
        System.out.println(username + " invia il messaggio: " + message);
    }


    public void displayUserDetails() {
        // Implementa la logica per visualizzare i dettagli dell'utente
        System.out.println("Username: " + username);
        System.out.println("Followers: " + followers);
        System.out.println("Messages Received: " + messagesReceived);
    }
    public void login() {
        System.out.println(username + " effettua il login.");
    }
}

