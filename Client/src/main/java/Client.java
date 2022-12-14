import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    Socket socket;

    public Client(String ip, Integer port) throws IOException {
        this.socket = new Socket("localhost",port);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("localhost",8000);
        ObjectOutputStream output = new ObjectOutputStream(client.socket.getOutputStream());
        Thread threadReceiver = new ClientReceiver(client.socket);
        threadReceiver.start();
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            output.writeObject(s);
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