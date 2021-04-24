import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Dispatcher {
    public static void main(String[] args) throws Exception {
        Integer port = 6789;
        ServerSocket welcomeSocket = new ServerSocket(port);
        Queue chat = new Queue();

        while(true){
            System.out.println("\n[+] Waiting for connection");
            Socket connection = welcomeSocket.accept();
            System.out.println("[+] Connected with: " + connection.getInetAddress() + ":" + connection.getPort());

            //passing the connection to a new worker
            Worker receiverThread = new Worker(connection, chat, "receiver");
            Worker senderThread = new Worker(connection, chat, "sender");
            receiverThread.start();
            senderThread.start();

        }
    }
}
