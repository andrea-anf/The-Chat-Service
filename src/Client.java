import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String messageToSent = null;
        String messageToRead = null;

        //Create connection
        System.out.println("[+] Connecting to server...");
        Socket connection = new Socket("localhost", 6789);
        System.out.println("[+] Successfull connected to port " + connection.getLocalPort() + "\n");

        //crete "file descriptor" for i/o
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        DataOutputStream outFromClient = new DataOutputStream(connection.getOutputStream());

        while(messageToSent != "exit"){
            messageToSent = scanner.nextLine();
            outFromClient.writeBytes(messageToSent+"\n");

            //read data from server
            messageToRead = inFromServer.readLine();
            System.out.println(messageToRead);
        }

        connection.close();
    }
}
