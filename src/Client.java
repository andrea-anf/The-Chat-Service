import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String data;

        //Create connection
        System.out.println("[+] Connecting to server...");
        Socket connection = new Socket("localhost", 6789);
        System.out.println("["+connection.getLocalPort()+"] Successfull connected\n");

        //crete "file descriptor" for i/o
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        //read data from server
        data = inFromServer.readLine();

        connection.close();
    }
}
