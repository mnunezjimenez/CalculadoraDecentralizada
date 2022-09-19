import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Node {
    ServerSocket nodeSocket;
    List<User> usersList;

    public Node(int port) throws IOException {
        this.nodeSocket = new ServerSocket(port);
        this.usersList = new ArrayList<User>();

    }

    public static void main(String[] args) throws IOException {
        Node node = new Node(8000);
        while (true) {
            try {
                Socket newUserSocket = node.nodeSocket.accept();
                User newUser = new User(newUserSocket);
                node.usersList.add(newUser);
                Thread newUserManager = new UserManager(newUser, node);
                newUserManager.start();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


}

