package Server;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Writer extends Thread{

    private ArrayList<DataOutputStream> clients;
    Queue chat;

    private String message = null;


    public Writer(ArrayList c, Queue queue){
        this.clients = c;
        this.chat = queue;
    }


    public void run() {
        while(true){
            message = chat.take();
            //System.out.println("Writer: sending '"+message+"'");
            for(DataOutputStream out : clients){
                try {
                    out.writeBytes(message+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
