import java.io.*;
import java.net.Socket;

public class Receiver extends Thread {
    InputStream in;

    public Receiver(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            ObjectInputStream input = null;
            String message;
            try {
                input = new ObjectInputStream(this.in);
                message = (String) input.readObject();
                System.out.println(message);
            } catch (Exception e) {

            }
        }
    }
}

