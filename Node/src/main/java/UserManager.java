import java.io.*;

public class UserManager extends Thread{
    private User user;
    private Node node;

    private ObjectInputStream in;

    private ObjectOutputStream out;
    public UserManager(User user, Node node) throws IOException {
        this.user = user;
        this.node = node;
    }

    @Override
    public void run() {
        String message;
        while(true) {
            try {
                message = (String) user.inputStream.readObject();
                send(message);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo enviar " + e);
            }
        }
    }

    private void send(String message) throws IOException {
        System.out.println("Enviando: " + message);
        for (User user : node.usersList) {
            System.out.println(user.toString());
            user.outputStream.writeObject(message);
        }
    }
}
