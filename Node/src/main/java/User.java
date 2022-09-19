import java.io.*;
import java.net.Socket;

public class User {
    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    public User(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }
}
