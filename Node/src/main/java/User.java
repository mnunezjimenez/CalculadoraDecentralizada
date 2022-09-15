import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class User {
    Socket socket;
    InputStream in;
    OutputStream out;

    public User(Socket socket, InputStream in, OutputStream out){
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
}
