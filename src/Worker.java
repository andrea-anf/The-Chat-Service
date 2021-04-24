import java.net.*;
import java.io.*;
import java.util.Locale;

public class Worker extends Thread{
    private Socket connection = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String message = null;
    private String messageFromClient = null;
    private Integer index = 0;
    private String type = null;
    Queue chat;

    public Worker(Socket newSocket, Queue que, String t){
        connection = newSocket;
        chat = que;
        type = t;
        try{
            inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outToClient = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        //if user send "exit", end thread
        if(type == "receiver"){
            while(true){
                try {
                    messageFromClient = inFromClient.readLine();
                    chat.put(messageFromClient);
                }
                catch (IOException e){
//                    e.printStackTrace();
                }
                System.out.println(messageFromClient);
            }
        }
        else if(type == "sender") {
            while(true){
                try {
                    message = chat.get(index);
                    index++;
                    outToClient.writeBytes(message + "\n");
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }


    }
}
