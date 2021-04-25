package Server;

import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Worker extends Thread{
    private Socket connection;
    private BufferedReader inFromClient;
    private String messageFromClient = null;
    private String messageToClients = null;
    private ArrayList<DataOutputStream> clients= new ArrayList<>();
    private DataOutputStream outClient;
    Queue chat;

    public Worker(Socket newSocket, Queue queue, ArrayList c, DataOutputStream o){
        this.connection = newSocket;
        this.chat = queue;
        this.clients = c;
        this.outClient = o;
        try{
            this.inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {

        while (!this.connection.isClosed()){
            try{
                messageFromClient = inFromClient.readLine();
                if(messageFromClient != null){
                    if(messageFromClient.compareToIgnoreCase("exit") == 0){
                        this.clients.remove(clients.indexOf(outClient));
                        this.connection.close();
                        messageFromClient = "Client "+ this.getId() +" left the chat";
                    }
                    chat.put(messageFromClient);
                    //System.out.println("Receiver: " + messageFromClient);
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
