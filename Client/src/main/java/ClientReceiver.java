import java.io.*;
import java.net.Socket;

public class ClientReceiver extends Thread {
    Socket socket;

    public ClientReceiver(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        String message;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {

        }
        while (true) {
            try {
                message = (String) in.readObject();
                if(message.startsWith("1")) {
                    message = message.substring(1);
                    System.out.println("Me llegó: " + message);
                }
            } catch (Exception e) {
                System.out.println("No pude recibir " + e);
            }
        }
    }
}

