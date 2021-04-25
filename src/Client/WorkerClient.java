package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class WorkerClient extends Thread {
    Scanner scanner = new Scanner(System.in);

    private Socket connection = null;
    private BufferedReader inFromServer;
    private DataOutputStream outToServer;
    private String message = null;
    private String messageFromServer = null;
    private String type = null;


    public WorkerClient(Socket newSocket, String t){
        connection = newSocket;
        type = t;
        try{
            inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outToServer = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        if(type == "receiver"){
            while(true){
                try {
                    messageFromServer = inFromServer.readLine();
                    if(messageFromServer != null){
                        if(messageFromServer == "exit"){
                            connection.close();
                            System.out.println(this.getId() + " connessione: "+ this.connection.isClosed() );
                        }
                        System.out.println("Server: " + messageFromServer);
                    }
                } catch (IOException e) {
//                        e.printStackTrace();
                }

            }
        }
        else if(type == "sender") {
            while(true){
                try {
                    message = scanner.nextLine();
                    if(message != null){
                        outToServer.writeBytes(message + "\n");
                    }
                } catch (IOException e) {
//                        e.printStackTrace();
                }
            }
        }


    }
}

