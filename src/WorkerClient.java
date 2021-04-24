import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class WorkerClient extends Thread {

    private Socket connection = null;
        Scanner scanner = new Scanner(System.in);
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
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }
                    System.out.println(messageFromServer);
                }
            }
            else if(type == "sender") {
                while(true){
                    message = scanner.nextLine();
                    try {
                        outToServer.writeBytes(message + "\n");
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }
                }
            }


        }
    }

