package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Twitter";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Matricola138";

    public static boolean isValidUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puoi gestire l'eccezione in base alle tue esigenze
        }
        return false;
    }

    public static User getUser(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE Username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(resultSet.getString("Username"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puoi gestire l'eccezione in base alle tue esigenze
        }
        return null;
    }

    // Aggiungi il metodo nella classe DatabaseService
    public static boolean saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO users (Username, Password) VALUES (? , ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                // Aggiungi eventuali altre informazioni dell'utente se necessario
                preparedStatement.setString(2, "FullNamePlaceholder");
                preparedStatement.setString(3, "PasswordPlaceholder");

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Restituisce true se l'inserimento è avvenuto con successo
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }
        return false;
    }

    public static List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Username FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String username = resultSet.getString("Username");
                        allUsers.add(username);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }

        return allUsers;
    }
    public static boolean saveMessage(Message message, String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Insert del messaggio
            String insertMessageQuery = "INSERT INTO messages (Content, SenderID) VALUES (?, (SELECT UserID FROM users WHERE Username = ?))";
            try (PreparedStatement insertMessageStatement = connection.prepareStatement(insertMessageQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertMessageStatement.setString(1, message.getContent());
                insertMessageStatement.setString(2, username);

                int rowsAffected = insertMessageStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Recupera l'ID del messaggio appena inserito
                    try (ResultSet generatedKeys = insertMessageStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int messageId = generatedKeys.getInt(1);

                            // Inserisci gli hashtags associati al messaggio
                            saveMessageHashtags(messageId, message.getHashtags(), connection);

                            return true; // Restituisce true se l'inserimento è avvenuto con successo
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }
        return false;
    }

    private static void saveMessageHashtags(int messageId, List<String> hashtags, Connection connection) throws SQLException {
        // Inserisci gli hashtags nel database se non esistono, poi collegali al messaggio
        String insertHashtagQuery = "INSERT INTO hashtags (TagName) VALUES (?) ON DUPLICATE KEY UPDATE TagName = VALUES(TagName)";
        String insertMessageHashtagQuery = "INSERT INTO messagehashtags (MessageID, HashtagID) VALUES (?, (SELECT HashtagID FROM hashtags WHERE TagName = ?))";

        try (PreparedStatement insertHashtagStatement = connection.prepareStatement(insertHashtagQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertMessageHashtagStatement = connection.prepareStatement(insertMessageHashtagQuery)) {

            for (String hashtag : hashtags) {
                // Inserisci l'hashtag nel database
                insertHashtagStatement.setString(1, hashtag);
                insertHashtagStatement.executeUpdate();

                // Collega l'hashtag al messaggio
                insertMessageHashtagStatement.setInt(1, messageId);
                insertMessageHashtagStatement.setString(2, hashtag);
                insertMessageHashtagStatement.executeUpdate();
            }
        }
    }
    public static boolean isValidAdmin(String adminName, String adminPassword) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM admin WHERE AdminName = ? AND AdminPassword = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, adminName);
                preparedStatement.setString(2, adminPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<String> getUserMessages(String username) {
        List<String> userMessages = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Query per ottenere i messaggi dell'utente
            String query = "SELECT Content FROM messages WHERE Username = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Aggiungi il contenuto del messaggio alla lista
                        userMessages.add(resultSet.getString("Content"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in base alle tue esigenze
        }

        return userMessages;
    }
}
