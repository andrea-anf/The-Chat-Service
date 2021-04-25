package Client;

import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        //Create connection
        System.out.println("[+] Connecting to server...");
        Socket connection = new Socket("localhost", 6789);
        System.out.println("[+] Successfull connected to port " + connection.getLocalPort() + "\n");

        WorkerClient receiver = new WorkerClient(connection, "receiver");
        WorkerClient sender = new WorkerClient(connection, "sender");

        receiver.start();
        sender.start();
    }
}
