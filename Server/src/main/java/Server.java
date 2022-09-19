import javax.sound.midi.Receiver;
import java.io.*;
import java.net.Socket;


public class Server {
    Socket socket;
    private String answer;
    public Server(String ip, Integer port) throws IOException {
        this.socket = new Socket("localhost",port);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server("localhost",8000);
        ObjectOutputStream output = new ObjectOutputStream(server.socket.getOutputStream());
        Thread threadReceiver = new ServerReceiver(server,output);
        threadReceiver.start();
        String tmpAnswer = "";
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