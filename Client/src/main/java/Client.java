import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    Socket socket;

    public Client(String ip, Integer port) throws IOException {
        this.socket = new Socket(InetAddress.getByName(ip),port);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("localhost",8000);
        while(true)
        {
            ObjectOutputStream out = new ObjectOutputStream(client.socket.getOutputStream());
            out.writeObject("Hi client"+ "\r\n");
            out.flush();
            Thread threadReceiver = new Receiver(client.socket.getInputStream());
            threadReceiver.start();
            Thread.sleep(5000);
        }
    }

    public String inputToString(InputStream in) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, "ASCII");
        for (int c = reader.read(); c != -1; c = reader.read()) {
            result.append((char) c);
        }
        System.out.println(result.toString());
        return result.toString();
    }
}
