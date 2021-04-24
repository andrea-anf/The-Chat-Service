import java.net.*;
import java.io.*;
public class Worker extends Thread{
    private Socket connection = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;


    public Worker(Socket newSocket, Queue chat){
        connection = newSocket;
        try{
            inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outToClient = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){

            //thread stuff

    }
}
