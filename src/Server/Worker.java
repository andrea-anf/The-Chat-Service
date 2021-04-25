package Server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Worker extends Thread{
    private Socket connection = null;
    private BufferedReader inFromClient;
    private String messageFromClient = null;
    private String messageToClients = null;
    Queue chat;

    public Worker(Socket newSocket, Queue queue){
        this.connection = newSocket;
        this.chat = queue;
        try{
            this.inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {

        //TODO: make it call a listener and writer

        while (!this.connection.isClosed()){
            try{
                messageFromClient = inFromClient.readLine();
                if(messageFromClient != null){
                    chat.put(messageFromClient);
                    System.out.println("Received: " + messageFromClient);
                }


            }catch (IOException e){
                e.printStackTrace();
            }
        }



/*        try {
            while(true){
                messageFromClient = inFromClient.readLine();
                if (messageFromClient != null) {
                    chat.put(messageFromClient);
                    System.out.println("User " + connection.getPort() + ": " + messageFromClient);
                }

                messageToClients = chat.take();
            }

        } catch (IOException e) {
//                    e.printStackTrace();
        }*/


    }
}
