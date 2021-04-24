import java.util.ArrayList;

public class Queue {
    private ArrayList<String> buffer = new ArrayList<>();

    public synchronized void put(String message){
        buffer.add(message);
        notifyAll();
    }

    public synchronized String get(int index){
        String message = null;
        while(buffer.size() == 0){
            try {
                wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        if(buffer.size() > 0){
            message = buffer.get(index);
//            buffer.remove(0);
        }
        return message;
    }
}
