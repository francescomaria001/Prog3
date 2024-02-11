

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    private static ArrayList<ClientHandler> clients = new ArrayList();

    public server() {
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8889);

            while(true) {
                System.out.println("Waiting for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler clientThread = new ClientHandler(socket, clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
}

