import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender extends Thread{
    String message;
    Socket socket;

    public Sender(String message, Socket socket) {
        this.message = message;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
