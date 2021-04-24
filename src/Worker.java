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
    Queue chat;

    public Worker(Socket newSocket, Queue que){
        connection = newSocket;
        chat = que;
        try{
            inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outToClient = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        while(messageFromClient != "exit"){
            try {
                messageFromClient = inFromClient.readLine();
                chat.put(messageFromClient);
//                outToClient.writeBytes(messageFromClient.toUpperCase()+"\n");
            }
            catch (IOException e){
                e.printStackTrace();
            }

            message = chat.get(index);
            index++;
            try {
                outToClient.writeBytes(message+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
