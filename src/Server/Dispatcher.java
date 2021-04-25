package Server;

import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Dispatcher {
    public static void main(String[] args) throws Exception {
        Integer port = 6789;
        ServerSocket welcomeSocket = new ServerSocket(port);
        ArrayList<DataOutputStream> clients = new ArrayList<>();
        Queue chat = new Queue();

        Writer writer = new Writer(clients, chat);
        writer.start();
        System.out.println("\n[+] Waiting for connection");

        while(true){
            Socket connection = welcomeSocket.accept();
            System.out.println("[+] User " + connection.getPort() + " joined the chat");

            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
            clients.add(outToClient);

            Worker sonThread = new Worker(connection, chat, clients, outToClient);
            sonThread.start();
        }
    }
}
