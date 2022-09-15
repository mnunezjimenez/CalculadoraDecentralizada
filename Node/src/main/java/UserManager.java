import java.io.*;
import java.net.Socket;


public class UserManager extends Thread{
    private User user;
    private Node node;
    public UserManager(User user, Node node){
        this.user = user;
        this.node = node;
    }

    @Override
    public void run() {
        String message;
        try {
            ObjectInputStream input = new ObjectInputStream(this.user.in);
            message = (String) input.readObject();
            send(message);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String message) throws IOException {
        System.out.println(message);
        for (Socket user : node.usersList) {
            ObjectOutputStream out = new ObjectOutputStream(user.getOutputStream());
            out.writeObject(message + "\r\n");
            out.flush();
        }
    }
}
